package br.com.customerorders.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.customerorders.api.domain.entity.Customer;
import br.com.customerorders.api.repository.CustomerRepository;
import br.com.customerorders.api.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/customers")
@Tag(name = "Customers Controller", description = "Operações relacionadas ao recurso Cliente")
public class CustomerController {

	private final CustomerService customerService;
	private final CustomerRepository customerRepository;

	@GetMapping
	@Operation(summary = "Busca de lista de clientes", description = "Retorna uma lista de todos os clientes")
	public List<Customer> listar() {
		return customerRepository.findAll();
	}

	@GetMapping("/{customerId}")
	@Operation(summary = "Busca de um cliente por ID", description = "Retorna o cliente com o ID informado")
	public ResponseEntity<Customer> buscar(@PathVariable Long customerId) {
		return customerRepository.findById(customerId).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Salva um novo registro de cliente", description = "Retorna o cliente salvo no banco de dados")
	public Customer adicionar(@Valid @RequestBody Customer customer) {
		customer.setCustomerId(null);
		return customerService.salvar(customer);
	}

	@PutMapping("/{customerId}")
	@Operation(summary = "Atualiza um cliente já existente", description = "Retorna o cliente atualizado no banco de dados")
	public ResponseEntity<Customer> atualizar(@PathVariable Long customerId, @Valid @RequestBody Customer customer) {

		if (!customerRepository.existsById(customerId)) {
			return ResponseEntity.notFound().build();
		}

		customer.setCustomerId(customerId);
		Customer customerAtualizado = customerService.salvar(customer);

		return ResponseEntity.ok(customerAtualizado);
	}

	@DeleteMapping("/{customerId}")
	@Operation(summary = "Deleta um cliente por ID", description = "Retorna status da operação")
	public ResponseEntity<Void> remover(@PathVariable Long customerId) {
		if (!customerRepository.existsById(customerId)) {
			return ResponseEntity.notFound().build();
		}

		customerService.excluir(customerId);
		return ResponseEntity.noContent().build();

	}

}
