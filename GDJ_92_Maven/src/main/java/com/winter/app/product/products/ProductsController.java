package com.winter.app.product.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/products/*")
public class ProductsController {
	@Autowired
	private ProductService productService;
	@GetMapping("list")
	public void list(Model model) throws Exception{
		
		List<ProductVO> list = productService.list();
		
		model.addAttribute("list",list);
		
		
	}
	
	@GetMapping("detail")
	public void detail(ProductVO product ,Model model) throws Exception{
		product= productService.detail(product);
		model.addAttribute("detail",product);
		
	}
	@GetMapping("add")
	public String add() throws Exception{
		return "products/product_form";
	}
	@PostMapping("add")
	public String add(ProductVO product, Model model) throws Exception{
		int result =productService.insert(product);
		String msg="등록 실패";
		String url ="./list";
		if(result>0) {
			msg="등록 성공";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return  "commons/result";
	}
	
	@GetMapping("update")
	public String update(ProductVO product , Model model)throws Exception {
		product = productService.detail(product);
		model.addAttribute("productVO",product);
		return "products/product_form";
	}
	@PostMapping("update")
	public String update(ProductVO product,Model model,Integer i)throws Exception {
		int result = productService.update(product);
		String msg="수정 실패";
		String url="./detail?productNum="+product.getProductNum();
		if(result>0) {
			msg="수정 성공";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "commons/result";
	}
	
	@PostMapping("delete")
	public String delete(ProductVO product,Model model) throws Exception{
		int result = productService.delete(product);
		String msg="삭제 실패";
		String url="./list";
		if(result>0) {
			msg="삭제 성공";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "commons/result";
	}
	
}
