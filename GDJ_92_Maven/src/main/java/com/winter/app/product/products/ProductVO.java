package com.winter.app.product.products;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProductVO {

	private Long productNum;
	private String productName;
	private String productContents;
	private LocalDate productDate; 
	private Double productRate;
	private Long kindNum;
	
	//1:1
	//단방향
	private ProductKindVO productKindVO;
}
