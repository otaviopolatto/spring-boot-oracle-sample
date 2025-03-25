package br.com.customerorders.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.customerorders.api.domain.entity.Customer;
import br.com.customerorders.api.domain.exception.EntidadeNaoEncontradaException;
import br.com.customerorders.api.domain.exception.RegraNegocioException;
import br.com.customerorders.api.repository.CustomerRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	public Customer buscar(Long customerId) {
		return customerRepository.findById(customerId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
	}

	@Transactional
	public Customer salvar(Customer newCustomer) {

		boolean emailEmUso = customerRepository.findByEmailAddress(newCustomer.getEmailAddress())
				.filter(p -> !p.equals(newCustomer)).isPresent();

		if (emailEmUso) {
			throw new RegraNegocioException("Já existe um cliente cadastrado com este e-mail");
		}

		return customerRepository.save(newCustomer);

	}

	@Transactional
	public void excluir(Long customerId) {
		customerRepository.deleteById(customerId);
	}

}
