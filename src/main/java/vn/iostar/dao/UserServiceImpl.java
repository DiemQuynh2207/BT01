package vn.iostar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vn.iostar.configs.DBConnectionMySQL;
import vn.iostar.dao.impl.IUserDao;
import vn.iostar.dao.impl.UserDAOImpl;
import vn.iostar.models.UserModel;
import vn.iostar.service.IUserService;

public class UserServiceImpl implements IUserService {
	IUserDao userDao = new UserDAOImpl();

	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.get(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public boolean register(String username, String email, String password, String fullname) {

		if (checkExistEmail(email) || checkExistUsername(username))
			return false;
		userDao.insert(new UserModel(username, email, password, fullname));
		return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public UserModel get(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public boolean updatePassword(String email, String password) {
		String sql = "update users set pword = ? where email = ?";
		try {
			Connection conn = DBConnectionMySQL.getDatabaseConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, email);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
