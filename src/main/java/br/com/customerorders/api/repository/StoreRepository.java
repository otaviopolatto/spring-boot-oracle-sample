package br.com.customerorders.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.customerorders.api.domain.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	
	Optional<Store> findByStoreName(String storeName);
	
}
