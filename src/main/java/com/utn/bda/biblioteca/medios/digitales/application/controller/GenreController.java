package com.utn.bda.biblioteca.medios.digitales.application.controller;

import com.utn.bda.biblioteca.medios.digitales.application.request.CreateGenreRequest;
import com.utn.bda.biblioteca.medios.digitales.model.dto.GenreDto;
import com.utn.bda.biblioteca.medios.digitales.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<GenreDto> genreDtos = this.genreService.getAll();
        return new ResponseEntity<>(genreDtos , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id){
        try{
            GenreDto genreDto = this.genreService.getById(id);
            return new ResponseEntity<>(genreDto , HttpStatus.OK);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() , HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateGenreRequest genreRequest){
        try{
            GenreDto genreDto = this.genreService.add(GenreDto.from(genreRequest));
            return new ResponseEntity<>(genreDto, HttpStatus.CREATED);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id ,@RequestBody GenreDto genreDto){
        try{
            this.genreService.update(id, genreDto);
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
            this.genreService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
