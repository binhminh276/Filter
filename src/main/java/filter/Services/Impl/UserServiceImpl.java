package filter.Services.Impl;

import filter.Dao.UserDAO;
import filter.Dao.Impl.UserDAOImpl;
import filter.Entity.Users;
import filter.Services.UserService;

public class UserServiceImpl implements UserService {
	private UserDAO userDAO = new UserDAOImpl();

	@Override
	public Users login(String username, String password) {
		return userDAO.login(username, password);
	}

}
