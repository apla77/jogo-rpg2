package com.apirest.jogorpg.service;

import com.apirest.jogorpg.exception.InvalidInputException;
import com.apirest.jogorpg.exception.ResourceNotFoundException;
import com.apirest.jogorpg.model.Batalha;
import com.apirest.jogorpg.model.Jogador;
import com.apirest.jogorpg.repository.BatalhaRepository;
import com.apirest.jogorpg.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Autowired
    private JogadorService jogadorService;

    public List<Batalha> findAll(){
        return repository.findAll();
    }

    public Batalha getById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Jogador not found with ID: " + id
        ));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Batalha create(Batalha batalha){
        Optional<Jogador> jogador = jogadorRepository.findById(batalha.getCod_jogador());
        Optional<Jogador> monstro = Optional.ofNullable(jogadorService.createMonstros(batalha.getCod_jogador()));

        batalha.setCreatedAt(LocalDateTime.now());
        batalha.setJogador(jogador.get());
        batalha.setMonstro(monstro.get());

        batalha.setTurno(batalha.getTurno());
        batalha.setCod_jogador(jogador.get().getId());
        if(jogadaDado()){
            batalha.setIniciativa(jogador.get().getNome());
        }
        else{
            batalha.setIniciativa(monstro.get().getNome());
        }
        return repository.save(batalha);
    }

    public Batalha ataque(Batalha batalha){
        if (batalha.getId() == null || batalha.getValorDado() > 0) {
            throw new InvalidInputException("A Batalha não existe ou já foi finalizada");
        }
        if(batalha.getIniciativa().equals("Monstro")) {
            Optional<Jogador> monstro = Optional.ofNullable(jogadorRepository.findByCodBatalha(batalha.getMonstro().getCod_batalha()));
            monstro.get().setSaldo(jogarDados(1, 12) + batalha.getJogador().getPersonagem().getPoder() + batalha.getJogador().getPersonagem().getAgilidade());
            batalha.setMonstro(monstro.get());
            batalha.setIniciativa("Jogador");
        }
        else{
            Optional<Jogador> jogador = jogadorRepository.findById(batalha.getJogador().getCod_batalha());
            jogador.get().setSaldo(jogarDados(1, 12) + batalha.getMonstro().getPersonagem().getPoder() + batalha.getMonstro().getPersonagem().getAgilidade());
            batalha.setJogador(jogador.get());
            batalha.setIniciativa("Monstro");
        }
        return repository.save(batalha);
    }

    public Batalha defesa(Batalha batalha){
        if (batalha.getId() == null || batalha.getValorDado() > 0) {
            throw new InvalidInputException("A Batalha não existe ou já foi finalizada");
        }
        if(batalha.getIniciativa().equals("Monstro")) {
            Optional<Jogador> monstro = Optional.ofNullable(jogadorRepository.findByCodBatalha(batalha.getMonstro().getCod_batalha()));
            monstro.get().setSaldo(jogarDados(1, 12) + batalha.getJogador().getPersonagem().getDefesa() + batalha.getJogador().getPersonagem().getAgilidade());
            batalha.setMonstro(monstro.get());
            batalha.setIniciativa("Jogador");
        }
        else{
            Optional<Jogador> jogador = jogadorRepository.findById(batalha.getJogador().getCod_batalha());
            jogador.get().setSaldo(jogarDados(1, 12) + batalha.getMonstro().getPersonagem().getDefesa() + batalha.getMonstro().getPersonagem().getAgilidade());
            batalha.setJogador(jogador.get());
            batalha.setIniciativa("Monstro");
        }
        return repository.save(batalha);
    }

    public Batalha calculoDano(Batalha batalha){
        if (batalha.getId() == null || batalha.getValorDado() > 0) {
            throw new InvalidInputException("Batalha já finalizada");
        }
            Optional<Jogador> monstro = Optional.ofNullable(jogadorRepository.findByCodBatalha(batalha.getMonstro().getCod_batalha()));
            Optional<Jogador> jogador = jogadorRepository.findById(batalha.getJogador().getCod_batalha());

            if (batalha.getIniciativa().equals("Jogador")) {
                if (jogador.get().getSaldo() > monstro.get().getSaldo()) {
                    int dano = jogarDados(monstro.get().getPersonagem().getQtdDado(), monstro.get().getPersonagem().getTolalFaces());
                    monstro.get().getPersonagem().setQtdVidas(monstro.get().getPersonagem().getQtdVidas() - dano);
                    if (monstro.get().getPersonagem().getQtdVidas() <= 0) {
                        batalha.setValorDado(1); // Finaliza o jogo caso valor do daso seja maior que zero
                    } else {
                        batalha.setTurno(batalha.getTurno() + 1);
                    }
                    batalha.setMonstro(monstro.get());
                }
                batalha.setIniciativa("Monstro");
            }
            else if(batalha.getIniciativa().equals("Monstro")) {
                if (monstro.get().getSaldo() > jogador.get().getSaldo()) {
                    int dano = jogarDados(jogador.get().getPersonagem().getQtdDado(), jogador.get().getPersonagem().getTolalFaces());
                    jogador.get().getPersonagem().setQtdVidas(jogador.get().getPersonagem().getQtdVidas() - dano);
                    if (jogador.get().getPersonagem().getQtdVidas() <= 0) {
                        batalha.setValorDado(1); // Finaliza o jogo caso valor do daso seja maior que zero
                    } else {
                        batalha.setTurno(batalha.getTurno() + 1);
                    }
                    batalha.setJogador(jogador.get());
                }
                batalha.setIniciativa("Jogador");
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

    public int jogarDados(int v, int faces){
        int total = 0;
        Random random = new Random();
        for (int i = 0; i < v; i++) {
            total += (random.nextInt(faces) + 1);
        }
        return total;
    }
}
