package com.utn.bda.biblioteca.medios.digitales.application.controller;

import com.utn.bda.biblioteca.medios.digitales.application.request.CreateMediaTypeRequest;
import com.utn.bda.biblioteca.medios.digitales.model.dto.MediaTypeDto;
import com.utn.bda.biblioteca.medios.digitales.service.MediaTypeService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/mediatypes")
public class MediaTypeController {
    private final MediaTypeService mediaTypeService;

    public MediaTypeController(MediaTypeService mediaTypeService) {
        this.mediaTypeService = mediaTypeService;
    }
    @GetMapping
    public ResponseEntity<Object> getAll(){
        try {
            List<MediaTypeDto> mediaTypeDtos = this.mediaTypeService.getAll();
            return new ResponseEntity<>(mediaTypeDtos, HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id){
        try{
            MediaTypeDto mediaTypeDto = this.mediaTypeService.getById(id);
            return new ResponseEntity<>(mediaTypeDto, HttpStatus.OK);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateMediaTypeRequest mediaTypeRequest){
        try{
            MediaTypeDto mediaTypeDto = this.mediaTypeService.add(MediaTypeDto.from(mediaTypeRequest));
            return new ResponseEntity<>(mediaTypeDto, HttpStatus.CREATED);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id ,@RequestBody MediaTypeDto mediaTypeDto){
        try{
            this.mediaTypeService.update(id, mediaTypeDto);
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
            this.mediaTypeService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NoSuchElementException notFound){
            return new ResponseEntity<>(notFound.getMessage() ,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
