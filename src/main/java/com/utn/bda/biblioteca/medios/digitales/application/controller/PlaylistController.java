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
@RequestMapping("/api/v1/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<PlaylistDto> playlistDtos = this.playlistService.getAll();
        return ResponseHandler.success(playlistDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id){
        PlaylistDto playlistDto;
        try {
            playlistDto = this.playlistService.getById(id);
        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseHandler.badRequest("No se pudo encontrar el recurso");
        }
        return ResponseHandler.success(playlistDto);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreatePlaylistRequest playlistRequest){
        PlaylistDto playlistDto = PlaylistDto.from(playlistRequest);
        try {
            PlaylistDto result = this.playlistService.save(playlistDto);
            return ResponseHandler.created(result);
        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseHandler.badRequest("No se pudo crear el recurso");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @RequestBody PlaylistDto playlistRequest ){
        if (id != playlistRequest.getId()){
            return  ResponseHandler.badRequest("Peticion incorrecta");
        }
        try {
            this.playlistService.save(playlistRequest);
        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseHandler.badRequest("No se pudo modificar");
        }
        return ResponseHandler.noContent();
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
