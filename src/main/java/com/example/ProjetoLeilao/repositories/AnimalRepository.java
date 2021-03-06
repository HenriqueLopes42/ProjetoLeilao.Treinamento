package com.example.ProjetoLeilao.repositories;

import com.example.ProjetoLeilao.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    List<Animal> findByAtivo (Boolean ativo);
    List<Animal> findByNome(String nome);
}
