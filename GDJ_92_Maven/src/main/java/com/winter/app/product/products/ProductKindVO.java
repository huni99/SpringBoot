package com.winter.app.product.products;

import java.util.List;

import lombok.Data;

@Data
public class ProductKindVO {

		private Long kindNum;
		private String kindName;
	
		//1:N
//		private List<ProductVO> list;
}
