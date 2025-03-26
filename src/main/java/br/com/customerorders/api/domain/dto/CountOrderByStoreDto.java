package br.com.customerorders.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CountOrderByStoreDto {
	
	private Long storeId;
	
	private String storeName;
	
	private Long totalOrders;
	
}
