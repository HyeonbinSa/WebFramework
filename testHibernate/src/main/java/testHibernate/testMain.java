package testHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class testMain {
	private static SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		//Configuration conf = new Configuration();
		//conf.configure("hibernate.cfg.xml");
		//sessionFactory = conf.buildSessionFactory();
		//위의 3줄을 아래 한줄로 표현할 수 있음.
		
		sessionFactory = new Configuration().configure().buildSessionFactory();
		
		Category category1 = new Category();
		category1.setName("Computer");
		
		Category category2 = new Category();
		category2.setName("Car");
		Product product1 = new Product();
		product1.setName("notebook");
		product1.setPrice(20000);
		product1.setDescription("Awesome notebook");
		product1.setCategory(category1);
		
		category1.getProducts().add(product1);
		Product product2 = new Product();
		product2.setName("Desktop");
		product2.setPrice(18000);
		product2.setDescription("Powerful Desktop");
		product2.setCategory(category1);
		
		category1.getProducts().add(product2);
		Product product3 = new Product();
		product3.setName("Sonate");
		product3.setPrice(500000);
		product3.setDescription("good car!!");
		product3.setCategory(category2);
		
		category2.getProducts().add(product3);
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(category1);
		session.save(category2);
		
		
		
		tx.commit();
		session.close();
	}

}
