package kr.ac.hansung.cse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.cse.model.User;
import kr.ac.hansung.cse.service.UserService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private UserService userService;
	
	@RequestMapping
	public String getCart(Model model) {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();//사용자의 이름을 가져옴.
		
		User user =  userService.getUserByName(username);//이름을 통해 user를 가져옴.
		int cartId = user.getCart().getId();//유저를 바탕으로 Cart Id를 가져옴.
		
		model.addAttribute("cartId", cartId);
		
		return "cart";
	}
}
