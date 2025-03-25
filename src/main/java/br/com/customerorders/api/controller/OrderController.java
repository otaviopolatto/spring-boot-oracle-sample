package br.com.customerorders.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.customerorders.api.domain.projections.OrderProjection;
import br.com.customerorders.api.repository.OrderRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
@Tag(name = "Orders Controller", description = "Operações relacionadas ao recurso Pedidos")
public class OrderController {

	private final OrderRepository orderRepository;
	
	@GetMapping("/details")
	@Operation(summary = "Busca de pedidos com base em um range de datas", description = "Retorna a lista de pedidos")
	public ResponseEntity<List<OrderProjection>> buscar(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
		        
        List<OrderProjection> ordersList = orderRepository.findOrderDetailsBetweenDates(startDate, endDate);

		if (ordersList.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(ordersList);

	}

}
