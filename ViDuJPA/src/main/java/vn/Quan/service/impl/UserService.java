package vn.Quan.service.impl;

import java.util.List;

import vn.Quan.entity.UserEntity;
import vn.Quan.repository.IUserPepository;
import vn.Quan.repository.impl.UserRepository;
import vn.Quan.service.IUserService;

public class UserService implements IUserService {
	
	
	@Override
	public UserEntity login(String username, String password) {
		
	    IUserPepository userRepo = new UserRepository();
	    List<UserEntity> users = userRepo.findAll();

	    for (UserEntity u : users) {
	        if (u.getUsername().equals(username) &&
	            u.getPassword().equals(password)) {
	            return u; 
	        }
	    }

	    return null; 
	}


    @Override
	public void create(UserEntity user) throws Exception {

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new Exception("Username không được bỏ trống");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new Exception("Password không được bỏ trống");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new Exception("Email không được bỏ trống");
        }

        List<UserEntity> allUsers = userRepo.findAll();
        for (UserEntity u : allUsers) {
            if (u.getUsername().equalsIgnoreCase(user.getUsername())) {
                throw new Exception("Username đã tồn tại");
            }
            if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
                throw new Exception("Email đã tồn tại");
            }
        }

        userRepo.create(user);
    }



    @Override
	public void update(UserEntity user) throws Exception {

        UserEntity old = userRepo.findById(user.getId());

        if (old == null) {
            throw new Exception("User không tồn tại");
        }

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new Exception("Username không được bỏ trống");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new Exception("Password không được bỏ trống");
        }

        if (user.getPassword().length() < 6) {
            throw new Exception("Password phải có ít nhất 6 ký tự");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new Exception("Email không được bỏ trống");
        }

        List<UserEntity> allUsers = userRepo.findAll();
        for (UserEntity u : allUsers) {
            if (u.getId() != user.getId()) {

                if (u.getUsername().equalsIgnoreCase(user.getUsername())) {
                    throw new Exception("Username đã tồn tại");
                }

                if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
                    throw new Exception("Email đã tồn tại");
                }
            }
        }

        userRepo.update(user);
    }



    @Override
	public void delete(int id) throws Exception {

        UserEntity old = userRepo.findById(id);

        if (old == null) {
            throw new Exception("User không tồn tại");
        }

        if (old.getCategories() != null && !old.getCategories().isEmpty()) {
            throw new Exception("Không thể xoá user vì đang sở hữu các category");
        }

        userRepo.delete(id);
    }


    @Override
	public UserEntity findById(int id) {
        return userRepo.findById(id);
    }

    @Override
	public List<UserEntity> findAll() {
        return userRepo.findAll();
    }
    
    @Override
    public boolean checkExistUsername(String username) {
        return userRepo.existsUsername(username);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userRepo.existsEmail(email);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userRepo.existsPhone(phone);
    }
    
    @Override
    public UserEntity findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
