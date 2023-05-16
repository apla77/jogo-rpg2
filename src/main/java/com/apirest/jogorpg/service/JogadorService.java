package com.apirest.jogorpg.service;

import com.apirest.jogorpg.exception.InvalidInputException;
import com.apirest.jogorpg.exception.ResourceNotFoundException;
import com.apirest.jogorpg.model.Jogador;
import com.apirest.jogorpg.model.Personagem;
import com.apirest.jogorpg.repository.JogadorRepository;
import com.apirest.jogorpg.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository repository;

    @Autowired
    private PersonagemService personagemService;

    @Autowired
    private PersonagemRepository personRepository;

    public List<Jogador> findAll(){
        return repository.findAll();
    }

    public Jogador findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Jogador not found with ID: " + id
        ));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Jogador update(Jogador jogador){
        if (jogador.getId() == null) {
            throw new InvalidInputException("There is no ID");
        }
        return repository.save(jogador);
    }

    public Jogador create(Jogador jogador){
        List<Personagem> persons = personRepository.findAll();
        jogador.setCreatedAt(LocalDateTime.now());
        for (Personagem p : persons) {
            if(p.getTipo().equals(jogador.getTipo())){
                jogador.setPersonagem(p);
                jogador.setCod_batalha(p.getId());
            }
        }

        return repository.save(jogador);
    }

    public Jogador createMonstros(Long id){
        Jogador monster = new Jogador();
        Personagem p = personagemService.createRandom();
        monster.setPersonagem(p);
        monster.setTipo(p.getTipo());
        monster.setNome("Monstro");
        monster.setCod_batalha(id);
        monster.setCreatedAt(LocalDateTime.now());
        return repository.save(monster);
    }
}
