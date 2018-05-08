package testHibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Product")
public class Product {
	@Id
	@GeneratedValue
	@Column(name="product_id")
	private int id;
	
	private String name;
	private int price;
	private String description;
	
	@ManyToOne
	@JoinColumn(name="category_id")//두 애노테이션은 외래키로 사용하기 위함
	private Category category;
}
