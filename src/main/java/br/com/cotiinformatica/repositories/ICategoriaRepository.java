package br.com.cotiinformatica.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.entities.Categoria;

@Repository
public interface ICategoriaRepository extends CrudRepository<Categoria, Integer>{

}
