package vn.Quan.service.impl;

import java.util.List;

import vn.Quan.entity.CategoryEntity;
import vn.Quan.entity.UserEntity;
import vn.Quan.service.ICategoryService;

public class CategoryService implements ICategoryService{

    @Override
	public void create(CategoryEntity cate) throws Exception {

        if (cate.getCateName() == null || cate.getCateName().isEmpty()) {
            throw new Exception("Tên category không được bỏ trống");
        }

        if (cate.getUser() == null || cate.getUser().getId() <= 0) {
            throw new Exception("Category phải thuộc về 1 user");
        }

        UserEntity user = userRepo.findById(cate.getUser().getId());
        if (user == null) {
            throw new Exception("User không tồn tại");
        }

        List<CategoryEntity> list = categoryRepo.findByUserId(user.getId());
        for (CategoryEntity c : list) {
            if (c.getCateName().equalsIgnoreCase(cate.getCateName())) {
                throw new Exception("Category này đã tồn tại");
            }
        }

        categoryRepo.create(cate);
    }


    @Override
	public void update(CategoryEntity cate) throws Exception {

        CategoryEntity old = categoryRepo.findById(cate.getCateId());

        if (old == null) {
            throw new Exception("Category không tồn tại");
        }

        if (cate.getCateName() == null || cate.getCateName().isEmpty()) {
            throw new Exception("Tên category không được bỏ trống");
        }

        // kiểm tra trùng tên với category khác
        List<CategoryEntity> list = categoryRepo.findByUserId(old.getUser().getId());
        for (CategoryEntity c : list) {
            if (c.getCateId() != cate.getCateId()
                && c.getCateName().equalsIgnoreCase(cate.getCateName())) {

                throw new Exception("Tên category đã tồn tại");
            }
        }

        categoryRepo.update(cate);
    }


    @Override
	public void delete(int cateId, int userId) throws Exception {

        CategoryEntity cate = categoryRepo.findById(cateId);

        if (cate == null) {
            throw new Exception("Category không tồn tại");
        }
        if (cate.getUser().getId() != userId) {
            throw new Exception("Bạn không có quyền xóa category này");
        }

        categoryRepo.delete(cateId);
    }


    @Override
	public CategoryEntity findById(int id) {
        return categoryRepo.findById(id);
    }

    @Override
	public List<CategoryEntity> findAll() {
        return categoryRepo.findAll();
    }

    @Override
	public List<CategoryEntity> findByUserId(int userId) {
        return categoryRepo.findByUserId(userId);
    }

}
