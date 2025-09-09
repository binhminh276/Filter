package filter.Dao.Impl;

import filter.Configs.JPAConfig;
import filter.Dao.UserDAO;
import filter.Entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class UserDAOImpl implements UserDAO{

	@Override
	public Users login(String username, String password) {
		EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM Users u WHERE u.username = :username AND u.password = :password";
            TypedQuery<Users> query = em.createQuery(jpql, Users.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
        } finally {
            em.close();
        }
	}

}
