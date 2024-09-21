package vn.iostar.dao.impl;

import java.util.List;
import vn.iostar.models.UserModel;

public interface IUserDao {
	List<UserModel> findAll();
	
	UserModel findById(int id);
	
	void insert(UserModel user);
	
	UserModel  findByUsername (String username);
	
	void insert(UserModel user, String role);
	
	UserModel findByEmail(String email);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	String findRoleByID(int id);
	
	boolean changePassWordByMail(String mail, String password);
	

}
