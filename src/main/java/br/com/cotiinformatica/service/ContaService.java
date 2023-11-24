package br.com.cotiinformatica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.repositories.ICategoriaRepository;
import br.com.cotiinformatica.repositories.IContaRepository;

@Service
public class ContaService {

	@Autowired
	private ICategoriaRepository categoriaRepository;
	@Autowired
	private IContaRepository contaRepository;

	// variáveis constantes para armazenar as mensagens
	private static final String VALOR_INVALIDO = "O valor da conta não pode ser menor ou igual a zero.";
	private static final String CATEGORIA_NAO_ENCONTRADA = "Categoria não encontrada, verifique o ID informado.";
	private static final String CONTA_NAO_ENCONTRADA = "Conta não encontrada, verifique o ID informado.";

	// Método para cadastrar uma conta
	public void salvarConta(Conta conta, Integer idCategoria) {

		// verificar se a conta possui um ID
		// Se a conta possui ID, então ela deverá
		// ser atualizada, senão será cadastrada
		if (conta.getIdConta() != null && conta.getIdConta() > 0) {

			// Verificar se a conta informada existe no banco de dados
			Optional<Conta> consultaConta = contaRepository.findById(conta.getIdConta());
			if (consultaConta.isEmpty()) { // se conta não foi encontrada
				throw new IllegalArgumentException(CONTA_NAO_ENCONTRADA);

			}
		}
		// Não é permitido cadastrar conta com
		// valor igual ou menor que zero
		if (conta.getValor() <= 0) {
			throw new IllegalArgumentException(VALOR_INVALIDO);

		}
		// Verificar se a categoria informada
		// existe no banco de dados
		Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);

		if (categoria.isEmpty()) {
			// se categoria não foi encontrada
			throw new IllegalArgumentException(CATEGORIA_NAO_ENCONTRADA);

		}
		// Relacionar a conta com a categoria
		conta.setCategoria(categoria.get());
		// Salvar a conta no banco de dados
		contaRepository.save(conta);
	}

	// método para excluir uma conta
	public void excluirConta(Integer idConta) {

		// consultar a conta no banco de dados através do ID
		Optional<Conta> conta = contaRepository.findById(idConta);

		// verificar se a conta foi encontrada
		if (conta.isPresent()) {
			// excluir a conta
			contaRepository.delete(conta.get());
		} else {
			throw new IllegalArgumentException(CONTA_NAO_ENCONTRADA);
		}
	}

	// método para consultar todas as contas cadastradas
	public List<Conta> consultarContas() {
		// retornar a consulta de contas do banco de dados
		return (List<Conta>) contaRepository.findAll();
	}

	// método para consultar 1 conta através do ID
	public Conta consultarPorId(Integer idConta) {

		// pesquisar a conta no banco de dados
		Optional<Conta> conta = contaRepository.findById(idConta);

		// verificar se a conta foi encontrada
		if (conta.isPresent()) {

			// retornar os dados da conta
			return conta.get();
		} else {
			throw new IllegalArgumentException(CONTA_NAO_ENCONTRADA);
		}
	}
}
