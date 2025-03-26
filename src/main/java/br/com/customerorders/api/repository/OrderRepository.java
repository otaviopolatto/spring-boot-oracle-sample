package br.com.customerorders.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.customerorders.api.domain.dto.CountOrderByStoreDto;
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
	
	@Query("SELECT new br.com.customerorders.api.domain.dto.CountOrderByStoreDto( A.store.id, A.store.storeName, COUNT(A.orderId) ) FROM Order A GROUP BY (A.store.id, A.store.storeName) ORDER BY 3 DESC")
	List<CountOrderByStoreDto> findTotalOrdersByStore();
	
	/* 
	    Quantidade de pedidos por dia dentro de um período 
	 
		SELECT COUNT(A.ORDER_ID) AS QTD_PEDIDOS, TRUNC(A.ORDER_TMS) AS DATA_PEDIDO
		FROM ORDERS A
		WHERE (A.ORDER_TMS >= TO_TIMESTAMP('2020-08-20T00:00:00', 'yyyy-mm-dd"T"HH24:MI:SS.FFTZH:TZM')
		AND A.ORDER_TMS <= TO_TIMESTAMP('2021-08-21T00:00:00', 'yyyy-mm-dd"T"HH24:MI:SS.FFTZH:TZM'))
		GROUP BY (TRUNC(A.ORDER_TMS)) ORDER BY 2 ASC;
		
		Quantidade de pedidos por loja 
		
		SELECT COUNT(A.ORDER_ID) AS QTD_PEDIDOS, A.STORE_ID AS LOJA
		FROM ORDERS A
		WHERE (A.ORDER_TMS >= TO_TIMESTAMP('2020-08-20T00:00:00', 'yyyy-mm-dd"T"HH24:MI:SS.FFTZH:TZM')
		AND A.ORDER_TMS <= TO_TIMESTAMP('2021-08-21T00:00:00', 'yyyy-mm-dd"T"HH24:MI:SS.FFTZH:TZM'))
		GROUP BY (A.STORE_ID) ORDER BY 2 DESC;

		Quantidade de pedidos por cliente 
		SELECT COUNT(A.ORDER_ID) AS QTD_PEDIDOS, A.CUSTOMER_ID AS CLIENTE
		FROM ORDERS A
		WHERE (A.ORDER_TMS >= TO_TIMESTAMP('2020-08-20T00:00:00', 'yyyy-mm-dd"T"HH24:MI:SS.FFTZH:TZM')
		AND A.ORDER_TMS <= TO_TIMESTAMP('2021-08-21T00:00:00', 'yyyy-mm-dd"T"HH24:MI:SS.FFTZH:TZM'))
		GROUP BY (A.CUSTOMER_ID) ORDER BY 1 DESC;

		Quantidade de pedidos por status
		
		SELECT COUNT(A.ORDER_ID) AS QTD_PEDIDOS, A.ORDER_STATUS AS STATUS
		FROM ORDERS A
		WHERE (A.ORDER_TMS >= TO_TIMESTAMP('2020-08-20T00:00:00', 'yyyy-mm-dd"T"HH24:MI:SS.FFTZH:TZM')
		AND A.ORDER_TMS <= TO_TIMESTAMP('2021-08-21T00:00:00', 'yyyy-mm-dd"T"HH24:MI:SS.FFTZH:TZM'))
		GROUP BY (A.ORDER_STATUS) ORDER BY 1 DESC;
		
	 
	 */
	
}
