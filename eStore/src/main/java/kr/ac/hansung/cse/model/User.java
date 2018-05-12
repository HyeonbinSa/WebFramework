package kr.ac.hansung.cse.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity //Table로 등록하기 위한 어노테이션
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userId")
	private int id;
	
	@NotEmpty(message="Username must not be null")
	private String username;
	@NotEmpty(message="Password must not be null")
	private String password;
	@NotEmpty(message="Email must not be null")
	private String email;
	
	//회원 1명 당 배송지 1개 OneToOne
	@OneToOne(optional=false, cascade=CascadeType.ALL)
	@JoinColumn(unique=true)//외래키 (반드시 유일한 값이 들어감 unique=true, 1대1이기 때문)
	private ShippingAddress shippingAddress;// 배송주소를 담기 위함.
	
	private boolean enabled=false;
	private String authority;
}
	