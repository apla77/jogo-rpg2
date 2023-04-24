package com.apirest.jogorpg.controller;

import com.apirest.jogorpg.model.Personagem;
import com.apirest.jogorpg.service.PersonagemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/personagem")
@Api(value = "PERSONAGEM API REST")
@CrossOrigin(origins = "*")
public class PersonagemController {

    @Autowired
    private PersonagemService service;

    @GetMapping("")
    @ApiOperation("find a personagens in TODO list")
    public ResponseEntity<List<Personagem>> getAll(){

        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find a personagens by it's id in the TODO list")
    public ResponseEntity<Personagem> getById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation("Create a new personagem in TODO list")
    public ResponseEntity<Personagem> create(@RequestBody Personagem personagem){
        return new ResponseEntity<>(service.create(personagem), HttpStatus.CREATED);
    }

    @PutMapping("")
    @ApiOperation("Update a task on TODO list")
    public ResponseEntity<Personagem> update(@RequestBody Personagem personagem){
        return new ResponseEntity<>(service.update(personagem), HttpStatus.OK);
    }

    @DeleteMapping("")
    @ApiOperation("Delete a personagem on TODO list")
    public ResponseEntity<HttpStatus> delete(@RequestHeader Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/randpm")
    @ApiOperation("Create a random personagem in TODO list")
    public ResponseEntity<Personagem> createRandom(){
        return new ResponseEntity<>(service.generateRandom(), HttpStatus.CREATED);
    }

    Personagem p1 = new Personagem("GUERREIRO", 20, 7, 5, 6, 1, 12);
    Personagem p2 = new Personagem("BARBARO",21,10,2,5,2,8);
    Personagem p3 = new Personagem("CAVALEIRO",26,6,8,3,2,6);
    Personagem p4 = new Personagem("ORC",42,7,1,2,3,4);
    Personagem p5 = new Personagem("GIGANTE",34,10,4,4,2,6);
    Personagem p6 = new Personagem("LOBISOMEN",34,7,4,7,2,4);
}
