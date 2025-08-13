package com.winter.app.product.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDAO productDAO;
	public List<ProductVO> list() throws Exception{
		return productDAO.list();
	};
	
	public ProductVO detail(ProductVO product) throws Exception{
		return productDAO.detail(product);
	}
	public int insert (ProductVO product)throws Exception{
		return productDAO.insert(product);
	}
	public int update(ProductVO product)throws Exception{
		return productDAO.update(product);
	}
	public int delete(ProductVO product)throws Exception{
		return productDAO.delete(product);
	}
}
