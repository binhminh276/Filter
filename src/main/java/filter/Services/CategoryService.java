package filter.Services;

import java.util.List;

import filter.Entity.Category;

public interface CategoryService {
	List<Category> findAll();
	Category findById(int id);
	void delete(int id);
	List<Category> findByUserId(int userId);
	void insert(Category category);
    void update(Category category);
}
