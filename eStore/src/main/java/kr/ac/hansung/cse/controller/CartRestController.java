package kr.ac.hansung.cse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.cse.model.Cart;
import kr.ac.hansung.cse.model.CartItem;
import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.model.User;
import kr.ac.hansung.cse.service.CartItemService;
import kr.ac.hansung.cse.service.CartService;
import kr.ac.hansung.cse.service.ProductService;
import kr.ac.hansung.cse.service.UserService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/cart")
public class CartRestController {

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;
	//cart ID 를 통한 조회
	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET) // cartId를 통해 Cart  정보 조회 (어떤 상품이 담겨있는가)
	public ResponseEntity<Cart> getCartById(@PathVariable(value = "cartId") int cartId) {
		Cart cart = cartService.getCartById(cartId);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	//cart의 내용을 제거
	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)// cartId를 통해 cart 전체를 제거
	public ResponseEntity<Void> clearCart(@PathVariable(value = "cartId") int cartId) {
		Cart cart = cartService.getCartById(cartId);
		cartItemService.removeAllCartItems(cart);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> addItem(@PathVariable(value = "productId") int productId) {
		
		Product product = productService.getProductById(productId);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userService.getUserByName(username);
		Cart cart = user.getCart();
		
		List<CartItem> cartItems = cart.getCartItems();
		
		for (int i = 0; i < cartItems.size(); i++) {
			if (product.getId() == cartItems.get(i).getProduct().getId()) {
/*				if(product.getUnitInStock()<=cartItems.get(i).getQuantity()) {
					return new ResponseEntity<>(HttpStatus.);
				}*/
				CartItem cartItem = cartItems.get(i);
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
				cartItemService.addCartItem(cartItem);

				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		//Create new CartItem
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(1);
		cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
		cartItem.setProduct(product);
		cartItem.setCart(cart);
		//Bidirectional
		cart.getCartItems().add(cartItem);
		
		cartItemService.addCartItem(cartItem);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/cartitem/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> romoveItem(@PathVariable(value = "productId") int productId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userService.getUserByName(username);
		Cart cart = user.getCart();

		CartItem cartItem  = cartItemService.getCartItemByProductId(cart.getId(), productId);
		cartItemService.removeCartItem(cartItem);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		 
	}
	@RequestMapping(value = "/plus/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> plusItem(@PathVariable(value = "productId") int productId) {
		
		Product product = productService.getProductById(productId);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userService.getUserByName(username);
		Cart cart = user.getCart();
		
		List<CartItem> cartItems = cart.getCartItems();
		
		for (int i = 0; i < cartItems.size(); i++) {
			if (product.getId() == cartItems.get(i).getProduct().getId() && product.getUnitInStock()>cartItems.get(i).getQuantity()) {
				CartItem cartItem = cartItems.get(i);
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
				cartItemService.addCartItem(cartItem);

				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "/minus/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> minusItem(@PathVariable(value = "productId") int productId) {
		
		Product product = productService.getProductById(productId);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		User user = userService.getUserByName(username);
		Cart cart = user.getCart();
		
		List<CartItem> cartItems = cart.getCartItems();
		
		for (int i = 0; i < cartItems.size(); i++) {
			if (product.getId() == cartItems.get(i).getProduct().getId() && 0 < cartItems.get(i).getQuantity()) {
				CartItem cartItem = cartItems.get(i);
				cartItem.setQuantity(cartItem.getQuantity() - 1);
				cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
				if(cartItem.getQuantity()==0) {
					cartItemService.removeCartItem(cartItem);
				}else {
					cartItemService.addCartItem(cartItem);
				}
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
