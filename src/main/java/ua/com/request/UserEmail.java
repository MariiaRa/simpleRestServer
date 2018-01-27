package ua.com.request;

public class UserEmail {
private String email;

public UserEmail(String email) {
	super();
	this.email = email;
}

public UserEmail() {
	super();
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

@Override
public String toString() {
	return "UserEmail [email=" + email + "]";
}


}
