package br.com.customerorders.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.customerorders.api.domain.entity.Order;
import br.com.customerorders.api.domain.projections.OrderProjection;

/*
 * WHERE (A.ORDER_TMS >= TO_TIMESTAMP(:startDate, 'yyyy-mm-dd"T"HH24:MI:SS.FFTZH:TZM')
 * AND A.ORDER_TMS <= TO_TIMESTAMP(:endDate, 'yyyy-mm-dd"T"HH24:MI:SS.FFTZH:TZM'))
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	
	@Query(nativeQuery = true, value = "SELECT A.ORDER_ID AS orderId"
			+ ", A.CUSTOMER_ID AS customerId"
			+ ", B.FULL_NAME AS customerFullName"
			+ ", A.ORDER_STATUS AS orderStatus"
			+ ", C.STORE_ID AS storeId"
			+ ", C.STORE_NAME AS storeName"
			+ ", A.ORDER_TMS AS orderTms"
			+ " FROM ORDERS A "
			+ " INNER JOIN CUSTOMERS B ON A.CUSTOMER_ID = B.CUSTOMER_ID "
			+ " INNER JOIN STORES C ON A.STORE_ID = C.STORE_ID "
			+ " WHERE A.ORDER_TMS BETWEEN ?1 AND ?2")
	List<OrderProjection> findOrderDetailsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
	
	
}
