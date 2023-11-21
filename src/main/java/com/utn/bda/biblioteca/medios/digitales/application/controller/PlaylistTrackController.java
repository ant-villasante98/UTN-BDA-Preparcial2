package com.utn.bda.biblioteca.medios.digitales.application.controller;

import com.utn.bda.biblioteca.medios.digitales.model.dto.PlaylistTrackDto;
import com.utn.bda.biblioteca.medios.digitales.service.PlaylistTrackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/playlisttracks")
public class PlaylistTrackController {

    private final PlaylistTrackService playlistTrackService;

    public PlaylistTrackController(PlaylistTrackService playlistTrackService) {
        this.playlistTrackService = playlistTrackService;
    }

    @PostMapping
    public ResponseEntity<Object> addTrackToPlaylist(@RequestBody PlaylistTrackDto playlistTrackDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            this.playlistTrackService.addTrackToPlaylist(playlistTrackDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoSuchElementException notFound){
            notFound.printStackTrace();
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteTrackFromPlaylist(@RequestBody PlaylistTrackDto playlistTrackDto){
        try{
            this.playlistTrackService.deleteTrackFromPlaylist(playlistTrackDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    /*
    @GetMapping("/{id}")
    public ResponseEntity<Object> tracksFromPlaylist(@PathVariable(name = "id") Long idPlaylist){
        try {
            List<TrackDto> trackDtoList = this.playlistService.trackListByPlaylist(idPlaylist);
            return ResponseHandler.success(trackDtoList);
        }catch (Exception exception){
            return ResponseHandler.badRequest("No se pudo encotrar el recurso");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> addAssociationTrackToPlaylist(@PathVariable(name = "id") long playlistId, @RequestBody PlaylistTrackRequest addTrackToPlaylist){
        if (playlistId!=addTrackToPlaylist.getPlaylistId()){
            return ResponseHandler.badRequest("Bad Request");
        }
        try {

            PlaylistDto playlistDto = PlaylistDto.from(addTrackToPlaylist);
            //this.playlistService.associationTrackToPlaylist(playlistDto);
            return ResponseHandler.noContent();
        }catch (Exception exception){
            return ResponseHandler.badRequest("Bad Request");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAssociationTrackToPlaylist(@PathVariable(name = "id") long playlistId, @RequestBody PlaylistTrackRequest addTrackToPlaylist){
        if (playlistId!=addTrackToPlaylist.getPlaylistId()){
            return ResponseHandler.badRequest("Bad Request");
        }
        try {

            PlaylistDto playlistDto = PlaylistDto.from(addTrackToPlaylist);
            //this.playlistService.deleteTrackFromPlaylist(playlistDto);
            return ResponseHandler.noContent();
        }catch (Exception exception){
            return ResponseHandler.badRequest("Bad Request");
        }
    }

     */
}
