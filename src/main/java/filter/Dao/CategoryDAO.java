package filter.Dao;

import java.util.List;

import filter.Entity.Category;

public interface CategoryDAO {
	List<Category> findAll();
    List<Category> findByUserId(int userId);
    Category findById(int id);
    void delete(int id);
    void insert(Category category);
    void update(Category category);
}
