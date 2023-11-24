package br.com.cotiinformatica.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.CategoriasGetDto;
import br.com.cotiinformatica.dtos.ContasGetDto;
import br.com.cotiinformatica.dtos.ContasPostDto;
import br.com.cotiinformatica.dtos.ContasPutDto;
import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.enums.TipoConta;
import br.com.cotiinformatica.service.CategoriaService;
import br.com.cotiinformatica.service.ContaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Contas")
@RestController
public class ContasController {

	@Autowired
	private ContaService contaService;
	@Autowired
	private CategoriaService categoriaService;

	@ApiOperation("Serviço para cadastro de conta.")
	@PostMapping("/api/contas")
	public ResponseEntity<String> post(@RequestBody ContasPostDto dto) {

		try {
			Conta conta = new Conta();
			conta.setNome(dto.getNome());
			conta.setValor(dto.getValor());
			conta.setData(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getData()));
			conta.setObservacoes(dto.getObservacoes());

			if (dto.getTipo() == 1)
				conta.setTipo(TipoConta.PAGAR);

			else if (dto.getTipo() == 2)
				conta.setTipo(TipoConta.RECEBER);

			contaService.salvarConta(conta, dto.getIdCategoria());

			return ResponseEntity.status(HttpStatus.CREATED).body("Conta cadastrada com sucesso.");

		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}

	@ApiOperation("Serviço para atualização de conta.")
	@PutMapping("/api/contas")
	public ResponseEntity<String> put(@RequestBody ContasPutDto dto) {

		try {
			Conta conta = new Conta();

			conta.setIdConta(dto.getIdConta());
			conta.setNome(dto.getNome());
			conta.setValor(dto.getValor());
			conta.setData(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getData()));
			conta.setObservacoes(dto.getObservacoes());

			if (dto.getTipo() == 1)
				conta.setTipo(TipoConta.PAGAR);

			else if (dto.getTipo() == 2)
				conta.setTipo(TipoConta.RECEBER);

			contaService.salvarConta(conta, dto.getIdCategoria());

			return ResponseEntity.status(HttpStatus.CREATED).body("Conta atualizada com sucesso.");

		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@ApiOperation("Serviço para exclusão de conta.")
	@DeleteMapping("/api/contas/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {

		try {
			contaService.excluirConta(id);

			return ResponseEntity.status(HttpStatus.OK).body("Conta excluida com sucesso.");

		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@ApiOperation("Serviço para consulta de contas.")
	@GetMapping("/api/contas")
	public ResponseEntity<List<ContasGetDto>> getAll() {
		try {
			// consultando as contas
			List<Conta> contas = contaService.consultarContas();

			// criando uma lista da classe DTO
			List<ContasGetDto> lista = new ArrayList<ContasGetDto>();

			for (Conta conta : contas) { // percorrer as contas obtidas

				// Criando um um objeto DTO
				ContasGetDto dto = new ContasGetDto();

				dto.setIdConta(conta.getIdConta());
				dto.setNome(conta.getNome());
				dto.setValor(conta.getValor());
				dto.setData(new SimpleDateFormat("dd/MM/yyyy").format(conta.getData()));
				dto.setObservacoes(conta.getObservacoes());

				if (conta.getTipo() == TipoConta.PAGAR) {
					dto.setTipo(1);

				} else if (conta.getTipo() == TipoConta.RECEBER) {
					dto.setTipo(2);
				}

				Categoria categoria = categoriaService.consultarPorId(conta.getCategoria().getIdCategoria());
				CategoriasGetDto categoriaDto = new CategoriasGetDto(categoria.getIdCategoria(), categoria.getNome());
				dto.setCategoria(categoriaDto);

				// adicionando o ContasGetDto na lista
				lista.add(dto);
			}
			// HTTP 200 - OK
			return ResponseEntity.status(HttpStatus.OK)

					.body(lista);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
	}

	@ApiOperation("Serviço para consultar 1 conta através do ID.")
	@GetMapping("/api/contas/{id}")
	public ResponseEntity<ContasGetDto> getById(@PathVariable("id") Integer id) {

		try {
			// consultando as contas
			Conta conta = contaService.consultarPorId(id);

			// criando um objeto DTO
			ContasGetDto dto = new ContasGetDto();

			dto.setIdConta(conta.getIdConta());
			dto.setNome(conta.getNome());
			dto.setValor(conta.getValor());
			dto.setData(new SimpleDateFormat("dd/MM/yyyy").format(conta.getData()));
			dto.setObservacoes(conta.getObservacoes());

			if (conta.getTipo() == TipoConta.PAGAR) {
				dto.setTipo(1);

			} else if (conta.getTipo() == TipoConta.RECEBER) {
				dto.setTipo(2);
			}

			Categoria categoria = categoriaService.consultarPorId(conta.getCategoria().getIdCategoria());
			CategoriasGetDto categoriaDto = new CategoriasGetDto(categoria.getIdCategoria(), categoria.getNome());
			dto.setCategoria(categoriaDto);

			// HTTP 200 - OK
			return ResponseEntity.status(HttpStatus.OK)

					.body(dto);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
	}

}
