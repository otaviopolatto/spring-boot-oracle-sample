package br.com.customerorders.api.domain.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity()
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;
    
	@Size(max = 255)
	@NotBlank
    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Column(name = "UNIT_PRICE")
    private Double unitPrice;

    @Lob
    @Column(name = "PRODUCT_DETAILS")
    private byte[] productDetails;

    @Lob
    @Column(name = "PRODUCT_IMAGE")
    private byte[] productImage;

    @Column(name = "IMAGE_MIME_TYPE")
    private String imageMimeType;

    @Column(name = "IMAGE_FILENAME")
    private String imageFilename;

    @Column(name = "IMAGE_CHARSET")
    private String imageCharset;

    @Column(name = "IMAGE_LAST_UPDATED")
    private Date imageLastUpdated;
    
}
