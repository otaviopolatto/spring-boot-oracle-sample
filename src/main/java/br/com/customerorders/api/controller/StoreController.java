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
import br.com.customerorders.api.domain.entity.Store;
import br.com.customerorders.api.repository.StoreRepository;
import br.com.customerorders.api.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/stores")
@Tag(name = "Stores Controller", description = "Operações relacionadas ao recurso de Lojas")
public class StoreController {

	private StoreService storeService;
	private StoreRepository storeRepository;

	@GetMapping
	@Operation(summary = "Busca de lista de lojas", description = "Retorna uma lista de todos as lojas")
	public List<Store> listar() {
		return storeRepository.findAll();
	}

	@GetMapping("/{storeId}")
	@Operation(summary = "Busca de uma loja por ID", description = "Retorna a loja com o ID informado")
	public ResponseEntity<Store> buscar(@PathVariable Long storeId) {
		return storeRepository.findById(storeId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Salva um novo registro de loja", description = "Retorna a loja salva no banco de dados")
	public Store adicionar(@Valid @RequestBody Store store) {
		store.setStoreId(null);
		return storeService.salvar(store);
	}

	@PutMapping("/{storeId}")
	@Operation(summary = "Atualiza uma loja já existente", description = "Retorna a loja atualizada no banco de dados")
	public ResponseEntity<Store> atualizar(@PathVariable Long storeId, @Valid @RequestBody Store store) {

		if (!storeRepository.existsById(storeId)) {
			return ResponseEntity.notFound().build();
		}

		store.setStoreId(storeId);
		Store storeAtualizada = storeService.salvar(store);

		return ResponseEntity.ok(storeAtualizada);

	}

	@DeleteMapping("/{storeId}")
	@Operation(summary = "Deleta uma loja por ID", description = "Retorna status da operação")
	public ResponseEntity<Void> remover(@PathVariable Long storeId) {
		if (!storeRepository.existsById(storeId)) {
			return ResponseEntity.notFound().build();
		}

		storeService.excluir(storeId);
		return ResponseEntity.noContent().build();

	}

}
