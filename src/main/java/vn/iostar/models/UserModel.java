package vn.iostar.models;

public class UserModel {
	private static final long serialVersionUID = -1L ;
	
	
	private int id;
	private String username;
	private String fullname;
	private String password;
	private String email;
	private String role;
	
	public UserModel(String username, String email, String password, String fullname, String role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
	}

	public UserModel(String username, String email, String password, String fullname) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.role = "MEMBER";
	}
	public UserModel() {
		
	}
	public UserModel(int id, String username, String fullname, String password, String email, String role) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", username=" + username + ", fullname=" + fullname + ", password=" + password
				+ ", email=" + email + ", role=" + role + "]";
	}
	
	
	
}
