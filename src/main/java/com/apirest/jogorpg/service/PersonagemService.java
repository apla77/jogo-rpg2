package com.apirest.jogorpg.service;

import com.apirest.jogorpg.exception.ResourceNotFoundException;
import com.apirest.jogorpg.exception.InvalidInputException;
import com.apirest.jogorpg.model.Personagem;
import com.apirest.jogorpg.model.dto.Activity;
import com.apirest.jogorpg.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository repository;

    @Autowired
    private BoredApiService boredApiService;

    public Personagem create(Personagem personagem){
        return repository.save(personagem);
    }

    public List<Personagem> findAll(){
        return repository.findAll();
    }

    public Personagem findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Personagem not found with ID: " + id
        ));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Personagem update(Personagem personagem){
        if (personagem.getId() == null) {
            throw new InvalidInputException("There is no ID");
        }
        return repository.save(personagem);
    }

    public Personagem generateRandom(){
        Activity activity = boredApiService.callBoredApi();
        Personagem personagem = new Personagem("Guerreiro", 20, 7, 5, 6, 1, 12);
        return create(personagem);
    }

}
