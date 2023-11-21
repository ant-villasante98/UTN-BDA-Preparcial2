package com.utn.bda.biblioteca.medios.digitales.application.controller;

import com.utn.bda.biblioteca.medios.digitales.application.ResponseHandler;
import com.utn.bda.biblioteca.medios.digitales.application.request.CreateTrackRequest;
import com.utn.bda.biblioteca.medios.digitales.model.dto.TrackByAGDto;
import com.utn.bda.biblioteca.medios.digitales.model.dto.TrackDto;
import com.utn.bda.biblioteca.medios.digitales.service.ArtistService;
import com.utn.bda.biblioteca.medios.digitales.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {

    private final TrackService trackService;
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        try {
            List<TrackDto> trackDtos = this.trackService.getAll();
            return new ResponseEntity<>(trackDtos, HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id){
        try{
            TrackDto trackDto = this.trackService.getById(id);
            return new ResponseEntity<>(trackDto, HttpStatus.OK);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateTrackRequest trackRequest){
        try{
            TrackDto trackDto = this.trackService.add(TrackDto.from(trackRequest));
            return new ResponseEntity<>(trackDto, HttpStatus.CREATED);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id ,@RequestBody TrackDto trackDto){
        try{
            this.trackService.update(id, trackDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id){
        try{
            this.trackService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    /*
    // Consigna 1
    @GetMapping("/consigna1")
    public ResponseEntity<Object> getByArtistAndGenre(@RequestParam("artistid") long artistId , @RequestParam("genreid") long genreId){

        try {

            List<TrackByAGDto> trackByAGDtos = this.trackService.getByArtistAndGenre(artistId,genreId);
            if(trackByAGDtos.isEmpty()){
                return new ResponseEntity<>( HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(trackByAGDtos, HttpStatus.OK);
        }catch (NoSuchElementException noElemet){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch ( NullPointerException listaVacia){
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
    }

     */




}
