package vn.Quan.service;

import java.util.List;

import vn.Quan.entity.CategoryEntity;
import vn.Quan.repository.ICategoryRepository;
import vn.Quan.repository.IUserPepository;
import vn.Quan.repository.impl.CategoryRepository;
import vn.Quan.repository.impl.UserRepository;

public interface ICategoryService {

	List<CategoryEntity> findByUserId(int userId);

	List<CategoryEntity> findAll();

	CategoryEntity findById(int id);

	void delete(int cateId, int userId) throws Exception;

	void update(CategoryEntity cate) throws Exception;

	void create(CategoryEntity cate) throws Exception;

	ICategoryRepository categoryRepo = new CategoryRepository();
	IUserPepository userRepo = new UserRepository();

}
