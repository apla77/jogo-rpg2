package com.apirest.jogorpg.controller;

import com.apirest.jogorpg.model.Batalha;
import com.apirest.jogorpg.model.Jogador;
import com.apirest.jogorpg.service.BatalhaServise;
import com.apirest.jogorpg.service.JogadorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/batalha")
@Api(value = "BATALHA API REST")
@CrossOrigin(origins = "*")
public class BatalhaController {

    @Autowired
    private BatalhaServise service;

    @GetMapping("/{id}")
    @ApiOperation("Start a battle by your id in the TODO list")
    public ResponseEntity<Batalha> getById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("")
    @ApiOperation("find a Batalha in TODO list")
    public ResponseEntity<List<Batalha>> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @ApiOperation("Create a new Batalha in TODO list")
    public ResponseEntity<Batalha> create(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(service.create(id), HttpStatus.CREATED);
    }
}