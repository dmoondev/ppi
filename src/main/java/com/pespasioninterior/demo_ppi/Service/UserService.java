package com.pespasioninterior.demo_ppi.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pespasioninterior.demo_ppi.Security.Entity.User;
import com.pespasioninterior.demo_ppi.Security.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	
	@Autowired
	 UserRepository userRepository;
	
	public Optional<User> getUser(int id){
		return userRepository.findById(id);
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public boolean existsById(int id) {
		return userRepository.existsById(id);
	}
}
