package com.winter.app.product.products;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDAO {
	public List<ProductVO> list() throws Exception;
	public ProductVO detail(ProductVO product)throws Exception;
	public int insert(ProductVO product)throws Exception;
	public int update(ProductVO product)throws Exception;
	public int delete(ProductVO product)throws Exception;
}
