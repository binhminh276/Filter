package filter.Dao;

import filter.Entity.Users;

public interface UserDAO {
	Users login(String username, String password);
}
