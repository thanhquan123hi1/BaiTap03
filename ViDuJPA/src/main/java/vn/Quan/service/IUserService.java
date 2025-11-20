package vn.Quan.service;

import java.util.List;

import vn.Quan.entity.UserEntity;
import vn.Quan.repository.impl.UserRepository;

public interface IUserService {

	List<UserEntity> findAll();

	UserEntity findById(int id);

	void delete(int id) throws Exception;

	void update(UserEntity user) throws Exception;

	void create(UserEntity user) throws Exception;

	UserRepository userRepo = new UserRepository();
	
	UserEntity login(String username, String password);
	
	boolean checkExistUsername(String username);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistPhone(String phone);
	
    UserEntity findByEmail(String email);


}
