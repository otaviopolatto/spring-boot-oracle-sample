package br.com.customerorders.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.customerorders.api.domain.entity.Product;
import br.com.customerorders.api.domain.exception.EntidadeNaoEncontradaException;
import br.com.customerorders.api.repository.ProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;

	public Product buscar(Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado"));
	}

	@Transactional
	public Product salvar(Product newProduct) {

		return productRepository.save(newProduct);

	}

	@Transactional
	public void excluir(Long productId) {
		productRepository.deleteById(productId);
	}

}
