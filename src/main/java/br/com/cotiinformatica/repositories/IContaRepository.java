package br.com.cotiinformatica.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.entities.Conta;

@Repository
public interface IContaRepository extends CrudRepository<Conta, Integer>{

}
