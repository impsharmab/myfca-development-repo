package com.imperialm.imiservices.services;

import com.imperialm.imiservices.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	public User getByUsername(String username) throws UsernameNotFoundException;
}
