package br.com.customerorders.api.domain.projections;

import java.time.LocalDateTime;
import java.util.Date;

public interface OrderProjection {
	
	String getOrderId();
	
	String getCustomerId();
	
	String getCustomerFullName();
	
	String getStoreId();
	
	String getStoreName();
	
	String getOrderStatus();
	
	LocalDateTime getOrderTms();
	

}
