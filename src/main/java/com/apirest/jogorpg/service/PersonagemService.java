package com.apirest.jogorpg.service;

import com.apirest.jogorpg.exception.ResourceNotFoundException;
import com.apirest.jogorpg.exception.InvalidInputException;
import com.apirest.jogorpg.model.Personagem;
import com.apirest.jogorpg.model.dto.Activity;
import com.apirest.jogorpg.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository repository;

    @Autowired
    private BoredApiService boredApiService;

    public Personagem create(Personagem person){
        createRandom();
        Personagem p = criarPersnagem(person.getNome(), person.getTipo());
        person.setQtdDado(p.getQtdDado());
        person.setPoder(p.getPoder());
        person.setAgilidade(p.getAgilidade());
        person.setDefesa(p.getDefesa());
        person.setTolalFaces(p.getTolalFaces());
        person.setQtdVidas(p.getQtdVidas());
        return repository.save(person);
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

    public Personagem generateRandom() {
        Random random = new Random();
        int n = random.nextInt(3) + 1;
        switch (n) {
            case 1: {
                Personagem person = new Personagem("MONSTRO","ORC", 42, 7, 1, 2, 3, 4);
                return person;
            }
            case 2: {
                Personagem person = new Personagem("MONSTRO","GIGANTE", 34, 10, 4, 4, 2, 6);
                return person;
            }
            case 3: {
                Personagem person = new Personagem("MONSTRO","LOBISOMEN", 34, 7, 4, 7, 2, 4);
                return person;
            }
        }
        return null;
    }
    public Personagem createRandom(){
        return  repository.save(generateRandom());
    }

    public Personagem criarPersnagem(String nome, String p){
        switch (p) {
            case "GUERREIRO": {
                Personagem person = new Personagem(nome,"GUERREIRO",20, 7, 5, 6, 1, 12);
                return person;
            }
            case "BARBARO": {
                Personagem person = new Personagem(nome,"BARBARO", 21, 10, 2, 5, 2, 8);
                return person;
            }case "CAVALEIRO": {
                Personagem person = new Personagem(nome,"CAVALEIRO", 26, 6, 8, 3, 2, 6);
                return person;
            }
        }
        return null;
    }
}
