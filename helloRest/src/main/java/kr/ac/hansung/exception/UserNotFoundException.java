package kr.ac.hansung.exception;

import lombok.Getter;


public class UserNotFoundException extends RuntimeException {
	//Generated SerialVersionID
	private static final long serialVersionUID = 8200011107621319300L;
	
	private long userID;

	public UserNotFoundException(long userID) {
		super();
		this.userID = userID;
	}

	public long getUserID() {
		return userID;
	}
	 
}
