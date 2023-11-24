package br.com.cotiinformatica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.repositories.ICategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private ICategoriaRepository categoriaRepository;

	// Método para consultar todas as categorias
	public List<Categoria> consultarCategorias() {
		// consultar e retornas todas as categorias
		// cadastradas no banco de dados
		return (List<Categoria>) categoriaRepository.findAll();
	}
	
	//método para consultar uma categoria por id
public Categoria consultarPorId(Integer id) {
	
	return  categoriaRepository.findById(id).get();
	
}
}
