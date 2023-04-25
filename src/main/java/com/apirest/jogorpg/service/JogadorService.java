package com.apirest.jogorpg.service;

import com.apirest.jogorpg.model.Jogador;
import com.apirest.jogorpg.model.Personagem;
import com.apirest.jogorpg.repository.JogadorRepository;
import com.apirest.jogorpg.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository repository;

    @Autowired
    private PersonagemRepository personRepository;

    public List<Jogador> findAll(){
        return repository.findAll();
    }

    public Jogador create(Jogador jogador){
        System.out.println(" ***************************** ////////// ******************************");
        List<Personagem> persons = personRepository.findAll();
        for (Personagem p : persons) {
            if(p.getTipo().equals(jogador.getTipo())){
                jogador.setPersonagem(p);
                System.out.println(p.getTipo() + " <-- p - j --> " + jogador.getTipo());
            }
        }
        return repository.save(jogador);
    }
}
