package com.apirest.jogorpg.service;

import com.apirest.jogorpg.exception.ResourceNotFoundException;
import com.apirest.jogorpg.model.Batalha;
import com.apirest.jogorpg.model.Jogador;
import com.apirest.jogorpg.repository.BatalhaRepository;
import com.apirest.jogorpg.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BatalhaServise {

    @Autowired
    private BatalhaRepository repository;

    @Autowired
    private JogadorRepository jogadorRepository;

    public List<Batalha> findAll(){
        return repository.findAll();
    }

    public Batalha getById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Jogador not found with ID: " + id
        ));
    }

    public Batalha create(Long id){
        Optional<Jogador> jogador = jogadorRepository.findById(id);
        Optional<Jogador> monstro = Optional.ofNullable(jogadorRepository.findByCodBatalha(jogador.get().getCod_batalha()));
        Batalha batalha = new Batalha();

        batalha.setCreatedAt(LocalDateTime.now());
        batalha.setJogador(jogador.get());
        batalha.setMonstro(monstro.get());
        batalha.setTurno(batalha.getTurno());
        batalha.setCod_jogador(jogador.get().getId());
        if(jogadaDado()){
            batalha.setIniciativa(jogador.get().getTipo());
        }
        else{
            batalha.setIniciativa(monstro.get().getTipo());
        }

        return repository.save(batalha);
    }

    public boolean jogadaDado(){
        boolean result = false;
        Random random = new Random();
        int jogador = random.nextInt(20) + 1;
        int monstro = random.nextInt(20) + 1;

        if(jogador == monstro){
            jogadaDado();
        }

        if(jogador > monstro){
            result = true;
        }
        return result;
    }
}
