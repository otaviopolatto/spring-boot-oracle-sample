package br.com.customerorders.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.customerorders.api.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
