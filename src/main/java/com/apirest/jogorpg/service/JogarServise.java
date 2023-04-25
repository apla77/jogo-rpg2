package com.apirest.jogorpg.service;

import com.apirest.jogorpg.exception.InvalidInputException;
import com.apirest.jogorpg.model.Jogador;
import com.apirest.jogorpg.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogarServise {

    @Autowired
    private JogadorRepository repository;

    public Jogador jogadas(Jogador jogador){
        if (jogador.getId() == null) {
            throw new InvalidInputException("There is no ID");
        }
        return repository.save(jogador);
    }
}
