package vn.iostar.service;

import vn.iostar.models.UserModel;

public interface IUserService {
	UserModel login (String username, String password);
	UserModel get(String username);
	
	boolean register (String username, String fullname, String email, String password);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean updatePassword(String email, String password);
}