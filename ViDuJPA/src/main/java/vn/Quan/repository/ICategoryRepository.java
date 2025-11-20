package vn.Quan.repository;

import java.util.List;

import vn.Quan.entity.CategoryEntity;

public interface ICategoryRepository {

	List<CategoryEntity> findByUserId(int userId);

	List<CategoryEntity> findAll();

	CategoryEntity findById(int cateId);

	void delete(int cateId);

	void update(CategoryEntity entity);

	void create(CategoryEntity entity);

}
