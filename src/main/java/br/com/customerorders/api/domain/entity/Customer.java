package br.com.customerorders.api.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity()
@Table(name = "CUSTOMERS")
public class Customer {
	
	@Id
	@EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
	
	@Size(max = 255)
	@NotBlank
    @Email
    @Column(name = "EMAIL_ADDRESS", nullable = false)
    private String emailAddress;
	
	@NotBlank
    @Size(max = 255)
    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;
	

}
