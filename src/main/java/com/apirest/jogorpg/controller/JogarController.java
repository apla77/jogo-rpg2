package com.apirest.jogorpg.controller;

import com.apirest.jogorpg.model.Jogador;
import com.apirest.jogorpg.service.JogadorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/jogar")
@Api(value = "JOGAR API REST")
@CrossOrigin(origins = "*")
public class JogarController {

    @Autowired
    private JogadorService service;

    @PutMapping("")
    @ApiOperation("Update a Jogada on TODO list")
    public ResponseEntity<Jogador> update(@RequestBody Jogador jogador){
        return new ResponseEntity<>(service.update(jogador), HttpStatus.OK);
    }
}
