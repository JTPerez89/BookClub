package com.jperez.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.jperez.bookclub.models.Book;
import com.jperez.bookclub.models.LoginUser;
import com.jperez.bookclub.models.User;
import com.jperez.bookclub.repositories.UserRepository;
    
    
@Service
public class UserService {
    
    @Autowired
    private UserRepository repo;
    
    public User register(User newUser, BindingResult result) {
        if(repo.findByEmail(newUser.getEmail()).isPresent()) {
        	result.rejectValue("email", "Unique", "Email is already in use.");
        }
        
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
    	    result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
    	}
    	
    	if(result.hasErrors()) {
    		return null;
    	}

    	String hashedPW = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hashedPW);

    	return repo.save(newUser);
    }
    
    public User login(LoginUser newLogin, BindingResult result) {
    	
    	if(result.hasErrors()) {
    		return null;
    	}
    	
    	Optional<User> storedUser = repo.findByEmail(newLogin.getEmail());
    	
    	if(!storedUser.isPresent()) {
    		result.rejectValue("email", "Unique", "Invalid Credentials!");
    		return null;
    	}
    	
    	User user = storedUser.get();
    	
    	if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
    	    result.rejectValue("password", "Matches", "Invalid Credentials!");
    	    return null;
    	}

    	return user;
    	
    }
    
    public User save(User s) {
		return repo.save(s);
	}
	
	public List<User> getAll(){
		return repo.findAll();
	}
	
    public User findUser(Long id) {
        Optional<User> optionalUser = repo.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    public void deleteUser(Long id) {
    	repo.deleteById(id);
    }
}
