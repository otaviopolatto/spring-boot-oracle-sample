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
@Entity
@Table(name = "STORES")
public class Store {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_ID")
    private Long storeId;
    
	@Size(max = 255)
	@NotBlank
    @Column(name = "STORE_NAME", nullable = false)
    private String storeName;
       
	@Size(max = 100)
    @Column(name = "WEB_ADDRESS")
    private String webAddress;
	
	@Size(max = 512)
    @Column(name = "PHYSICAL_ADDRESS")
    private String physicalAddress;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;

    @Lob
    @Column(name = "LOGO")
    private byte[] logo;

    @Column(name = "LOGO_MIME_TYPE")
    private String logoMimeType;

    @Column(name = "LOGO_FILENAME")
    private String logoFilename;

    @Column(name = "LOGO_CHARSET")
    private String logoCharset;

    @Column(name = "LOGO_LAST_UPDATED")
    private Date logoLastUpdated;
    
}
