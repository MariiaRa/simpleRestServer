package ua.com.request;

public class UpdatePassRequest {

	 private String email;
	 private String newPass;
	public UpdatePassRequest(String email, String newPass) {
		super();
		this.email = email;
		this.newPass = newPass;
	}
	public UpdatePassRequest() {}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	@Override
	public String toString() {
		return "UpdatePassRequest [email=" + email + ", newPass=" + newPass + "]";
	}
	
	
	
	
	
}
