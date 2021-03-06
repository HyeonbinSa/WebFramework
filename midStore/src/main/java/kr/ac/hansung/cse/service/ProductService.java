package kr.ac.hansung.cse.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.ProductDao;
import kr.ac.hansung.cse.model.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;

	public List<Product> getProducts() {
		List<Product> products = productDao.getProducts();
		
		return products;
	}

	public void addProduct(Product product) {
		productDao.addProduct(product);		
	}

	public void deleteProduct(Product product) {
		productDao.deleteProduct(product);
	}

	public Product getProductById(int id) {
		return productDao.getProductById(id);
	}

	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}
}
