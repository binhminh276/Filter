package filter.Services;

import filter.Entity.Users;

public interface UserService {
	Users login(String username, String password);
}
