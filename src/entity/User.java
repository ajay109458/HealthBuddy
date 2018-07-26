package entity;

import java.io.Serializable;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String userName;
	Integer userAge;
	boolean isUserMale;

	public User(String userName, Integer userAge, boolean isUserMale) {
		super();
		this.userName = userName;
		this.userAge = userAge;
		this.isUserMale = isUserMale;
	}

	public String getUserName() {
		return userName;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public boolean getIsUserMale() {
		return isUserMale;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", userAge=" + userAge + ", isUserMale=" + isUserMale + "]";
	}
}
