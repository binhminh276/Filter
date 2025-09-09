package filter.Services.Impl;

import java.util.List;

import filter.Dao.CategoryDAO;
import filter.Dao.Impl.CategoryDAOImpl;
import filter.Entity.Category;
import filter.Services.CategoryService;

public class CategoryServiceImpl implements CategoryService{

	private CategoryDAO dao = new CategoryDAOImpl();
	
	@Override
	public List<Category> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Category> findByUserId(int userId) {
		return dao.findByUserId(userId);
	}

	@Override
	public Category findById(int id) {
		return dao.findById(id);
	}


	@Override
	public void delete(int id) {
		dao.delete(id);
	}

	@Override
	public void insert(Category category) {
		dao.insert(category);
		
	}

	@Override
	public void update(Category category) {
		dao.update(category);
		
	}


}
