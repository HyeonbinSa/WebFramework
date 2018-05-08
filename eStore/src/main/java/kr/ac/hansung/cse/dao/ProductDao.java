package kr.ac.hansung.cse.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.model.Product;

@Repository
@Transactional
@EnableTransactionManagement
public class ProductDao {
	// JdbcTemplate -> Hibernate
	//Hibernate를 이용한 Dao
	@Autowired
	private SessionFactory sessionFactory;//Bean으로 등록된 sessionFactory 주입
	
	public Product getProductById(int id) {//id를 통해 product 조회
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product)session.get(Product.class, id);//record를 읽음.
		
		return 	product;
	}
	@SuppressWarnings("unchecked")
	public List<Product> getProducts(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product");
		List<Product> productList = query.list();
		
		return productList;
	}
	
	public void addProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();//@Transactional 에 의해 자동적으로 메소드 끝날때 flush 해주기 때문에 안 넣어줘도 됨.
	}
	
	public void deleteProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(product);
		session.flush();
	}
	
	public void updateProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
	}
	//JdbcTemplate를 이용한 Dao
	/*
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Product> getProducts() {
		String sqlstatement = "select * from product";// 레코드를 객체로

		return jdbcTemplate.query(sqlstatement, new RowMapper<Product>() {// 인터페이스 메소드라 구현해줘야함.
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setCategory(rs.getString("category"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setUnitInStock(rs.getInt("unitInStock"));
				product.setDescription(rs.getString("description"));

				return product;
			}

		});
	}

	public boolean addProduct(Product product) {
		String name = product.getName();
		String category = product.getCategory();
		int price = product.getPrice();
		String manufacturer = product.getManufacturer();
		int unitInStock = product.getUnitInStock();
		String description = product.getDescription();

		String sqlStatement = "insert into product (name, category, price, manufacturer, unitInStock, description) value (?, ?, ?, ?, ?, ?)";
		return (jdbcTemplate.update(sqlStatement,	new Object[] { name, category, price, manufacturer, unitInStock, description }) == 1);
	}

	public boolean deleteProduct(int id) {
		String sqlStatement = "delete from product where id=?";
		return (jdbcTemplate.update(sqlStatement, new Object[] { id }) == 1);
	}

	public Product getProductById(int id) {
		String sqlstatement = "select * from product where id=?";// 레코드를 객체로

		return jdbcTemplate.queryForObject(sqlstatement, new Object[] { id }, new RowMapper<Product>() {// 인터페이스 메소드라
																										// 구현해줘야함.
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setCategory(rs.getString("category"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setUnitInStock(rs.getInt("unitInStock"));
				product.setDescription(rs.getString("description"));

				return product;
			}

		});
	}

	public boolean updateProduct(Product product) {
		int id = product.getId();
				
		String name = product.getName();
		String category = product.getCategory();
		int price = product.getPrice();
		String manufacturer = product.getManufacturer();
		int unitInStock = product.getUnitInStock();
		String description = product.getDescription();

		String sqlStatement = "update product set name=?, category=?, price=?, manufacturer=?, unitInStock=?, description=? where id=?";
		return (jdbcTemplate.update(sqlStatement,
				new Object[] { name, category, price, manufacturer, unitInStock, description, id }) == 1);
	}
	*/
}
