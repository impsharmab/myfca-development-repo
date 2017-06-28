package com.imperialm.imiservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.entities.User;
import com.imperialm.imiservices.repositories.UserRepository;

//@Service("userService")
@Component("UserServiceImpl")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserServiceImpl implements UserDetailsService {
	private UserRepository userRepository;
	
	
	@Autowired
	public UserServiceImpl (UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	@Override
	@Cacheable("loadUserByUsername")
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = userRepository.findByUserId(username);
		if(user == null) throw new UsernameNotFoundException(username);
		return new UserDetailsImpl(user);
	}
	
	public User getByUsername(String username) throws UsernameNotFoundException{
		User user = userRepository.findByUserId(username);
		if(user == null) throw new UsernameNotFoundException(username);
		return user;
	}
}
