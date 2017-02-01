package com.imperialm.imiservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.entities.User;
import com.imperialm.imiservices.repositories.UserRepository;

@Service("userService")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserServiceImpl implements UserService, UserDetailsService {
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	public UserServiceImpl (UserRepository userRepository, PasswordEncoder passwordEncoder){
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = userRepository.findByUserId(username);
		if(user == null) throw new UsernameNotFoundException(username);
		return new UserDetailsImpl(user);
	}
}
