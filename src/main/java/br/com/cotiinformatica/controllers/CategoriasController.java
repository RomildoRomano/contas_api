package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.CategoriasGetDto;
import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Categorias")
@RestController
public class CategoriasController {

	@Autowired
	private CategoriaService categoriaService;

	@ApiOperation("Serviço para consulta de categorias do sistema.")
	@GetMapping("/api/categorias")
	public ResponseEntity<List<CategoriasGetDto>> getAll() {

		try {
			// consultando as categorias
			List<Categoria> categorias = categoriaService.consultarCategorias();

			// criando uma lista da classe DTO
			List<CategoriasGetDto> lista = new ArrayList<CategoriasGetDto>();

			for (Categoria categoria : categorias) {
				// percorrer as categorias obtidas
				// Criando um um objeto DTO
				CategoriasGetDto dto = new CategoriasGetDto(categoria.getIdCategoria(), categoria.getNome());
				lista.add(dto); // adicionando o DTO na lista
			}
			// HTTP 200 - OK
			return ResponseEntity.status(HttpStatus.OK)

					.body(lista);

		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
	}
}
