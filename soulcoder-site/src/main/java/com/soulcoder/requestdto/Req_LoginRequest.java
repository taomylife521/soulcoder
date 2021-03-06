package com.soulcoder.requestdto;

/**
 * The type Req login request.
 */
public class Req_LoginRequest {

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
		return userName;
	}

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
		this.userName = userName;
	}

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
		return password;
	}

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
		this.password = password;
	}
	private String userName="";
	private String password="";
	
}
