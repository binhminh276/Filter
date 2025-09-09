package filter.Dao.Impl;

import java.util.List;

import filter.Configs.JPAConfig;
import filter.Dao.CategoryDAO;
import filter.Entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CategoryDAOImpl implements CategoryDAO {

	@Override
	public List<Category> findAll() {
		EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
        } finally {
            em.close();
        }
	}

	@Override
	public List<Category> findByUserId(int userId) {
		EntityManager em = JPAConfig.getEntityManager();
        try {
            TypedQuery<Category> q = em.createQuery("SELECT c FROM Category c WHERE c.user.id = :uid", Category.class);
            q.setParameter("uid", userId);
            return q.getResultList();
        } finally {
            em.close();
        }
	}

	@Override
	public Category findById(int id) {
		EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.find(Category.class, id);
        } finally {
            em.close();
        }
	}


	@Override
	public void delete(int id) {
		EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            Category c = em.find(Category.class, id);
            if (c != null) em.remove(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
		
	}

	@Override
	public void insert(Category category) {
		EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
		
	}

	@Override
	public void update(Category category) {
		EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(category);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
		
	}

}
