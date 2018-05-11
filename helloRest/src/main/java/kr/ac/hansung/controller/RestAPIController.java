package kr.ac.hansung.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import kr.ac.hansung.exception.ErrorResponse;
import kr.ac.hansung.exception.UserDuplicatedException;
import kr.ac.hansung.exception.UserNotFoundException;
import kr.ac.hansung.model.User;
import kr.ac.hansung.service.UserService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api")
public class RestAPIController {
	@Autowired
	UserService userService;

	/// ---Retrieve All users
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUser() { // header, body(JSON), HTTP.Status
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);// users가 비어있으면 Header, body가 없고 httpStatus가 No_content라고
																// 담겨있음.
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);// Body에 users를 담아줌.
	}

	/// ---Retrieve Single user
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) { // header, body(JSON), HTTP.Status
		User user = userService.findById(id);
		if (user == null) {
			throw new UserNotFoundException(id);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);// Body에 user를 담아줌.
	}

	/// ---Create a user
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {// Body가 없기 때문에
																									// ResponseEntity<Void>를
																									// 넣어줌.

		if (userService.isUserExist(user)) {
			throw new UserDuplicatedException(user.getName());
		}
		userService.saveUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("api/users/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);// Body에 user를 담아줌.
	}
	/// ---Update a User
	@RequestMapping(value="/users/{id}", method=RequestMethod.PUT) 
	public ResponseEntity<User> updateUser(@PathVariable("id")long id, @RequestBody User user){
		User currentUser = userService.findById(id);
		if(currentUser == null) {
			throw new UserNotFoundException(id);
		}
		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalaly(user.getSalaly());
		
		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
	// ---Delete a User
	@RequestMapping(value="/users/{id}", method=RequestMethod.DELETE) 
	public ResponseEntity<User> deleteUser(@PathVariable("id")long id){
		User user = userService.findById(id);
		if(user == null) {
			throw new UserNotFoundException(id);
		}	
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	/// ---Delete All Users
	@RequestMapping(value="/users", method=RequestMethod.DELETE) 
	public ResponseEntity<User> deleteAllUser(){
		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	
}
