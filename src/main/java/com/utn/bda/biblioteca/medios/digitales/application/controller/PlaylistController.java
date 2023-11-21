package com.utn.bda.biblioteca.medios.digitales.application.controller;

import com.utn.bda.biblioteca.medios.digitales.application.ResponseHandler;
import com.utn.bda.biblioteca.medios.digitales.application.request.CreatePlaylistByArtistAndGenreRequest;
import com.utn.bda.biblioteca.medios.digitales.application.request.CreatePlaylistRequest;
import com.utn.bda.biblioteca.medios.digitales.model.dto.PlaylistDto;
import com.utn.bda.biblioteca.medios.digitales.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        try {

            List<PlaylistDto> playlistDtos = this.playlistService.getAll();
            return new ResponseEntity<>(playlistDtos, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id){

        try {
            PlaylistDto playlistDto = this.playlistService.getById(id);
            return new ResponseEntity<>(playlistDto, HttpStatus.OK);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception exception){
            exception.printStackTrace();
            return ResponseHandler.badRequest("No se pudo encontrar el recurso");
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreatePlaylistRequest playlistRequest){
        try {
            PlaylistDto playlistDto = PlaylistDto.from(playlistRequest);
            PlaylistDto result = this.playlistService.add(playlistDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseHandler.badRequest("No se pudo crear el recurso");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @RequestBody PlaylistDto playlistRequest ){
        try {
            this.playlistService.update(id , playlistRequest);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseHandler.badRequest("No se pudo modificar");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id){
        try{
            this.playlistService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Consigna 2
    @PostMapping("/consigna2")
    public ResponseEntity<Object> createPlaylistByArtistAndGenre(@RequestBody CreatePlaylistByArtistAndGenreRequest playlistCreate){
        try {
            PlaylistDto playlistDto = this.playlistService.createPlaylistByArtistAndGenre(playlistCreate.getArtistId(), playlistCreate.getPlaylistName(), playlistCreate.getGenreId(), playlistCreate.getMinutes());
            return new ResponseEntity<>(playlistDto, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
