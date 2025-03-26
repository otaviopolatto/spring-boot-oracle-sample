package br.com.customerorders.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.customerorders.api.domain.dto.CountOrderByStoreDto;
import br.com.customerorders.api.repository.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	public List<CountOrderByStoreDto> getTotalOrdersByStoreWithJpql() {
		return orderRepository.findTotalOrdersByStore();
	}	

}
