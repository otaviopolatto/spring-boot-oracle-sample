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

import br.com.customerorders.api.domain.entity.Product;
import br.com.customerorders.api.repository.ProductRepository;
import br.com.customerorders.api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
@Tag(name = "Products Controller", description = "Operações relacionadas ao recurso Produto")
public class ProductController {

	private final ProductService productService;
	private final ProductRepository productRepository;

	@GetMapping
	@Operation(summary = "Busca de lista de produtos", description = "Retorna uma lista de todos os produtos")
	public List<Product> listar() {
		return productRepository.findAll();
	}

	@GetMapping("/{productId}")
	@Operation(summary = "Busca de um produto por ID", description = "Retorna o produto com o ID informado")
	public ResponseEntity<Product> buscar(@PathVariable Long productId) {
		return productRepository.findById(productId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Salva um novo registro de produto", description = "Retorna o produto salvo no banco de dados")
	public Product adicionar(@Valid @RequestBody Product product) {
		product.setProductId(null);
		return productService.salvar(product);
	}
	
	@PutMapping("/{productId}")
	@Operation(summary = "Atualiza um produto já existente", description = "Retorna o produto atualizado no banco de dados")
	public ResponseEntity<Product> atualizar(@PathVariable Long productId, @Valid @RequestBody Product product) {

		if (!productRepository.existsById(productId)) {
			return ResponseEntity.notFound().build();
		}

		product.setProductId(productId);
		Product productAtualizado = productService.salvar(product);

		return ResponseEntity.ok(productAtualizado);
		
	}
	
	@DeleteMapping("/{productId}")
	@Operation(summary = "Deleta um produto por ID", description = "Retorna status da operação")
	public ResponseEntity<Void> remover(@PathVariable Long productId) {
		if (!productRepository.existsById(productId)) {
			return ResponseEntity.notFound().build();
		}

		productService.excluir(productId);
		return ResponseEntity.noContent().build();

	}
	
	

}
