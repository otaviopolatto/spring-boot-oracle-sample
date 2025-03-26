package br.com.customerorders.api;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.customerorders.api.domain.entity.Store;
import br.com.customerorders.api.repository.StoreRepository;

@SpringBootTest
class CustomerordersApiApplicationTests {
	
	@Autowired
	StoreRepository storeRepository;

	@Test
	void contextLoads() {
		
		List<Store> storeList = storeRepository.findAll();
		
		Assertions.assertTrue(storeList.size() > 0);
		
		storeList.forEach(System.out::println);
	}

}
