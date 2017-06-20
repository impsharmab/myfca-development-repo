package com.imperialm.imiservices.dto;

import com.imperialm.imiservices.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class UserDetailsImpl implements UserDetails {
	
	private static final long serialVersionUID = 5197941260523577515L;
	
	@Getter @Setter
	private User user;
	
	public UserDetailsImpl(User user){
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		//get the user role
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(1);
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getHashPass();
	}

	@Override
	public String getUsername() {
		return user.getName();
	}
	
	public String getSalt() {
		return user.getSalt();
	}

	public String getUserId() {
		return user.getUserId();
	}
	
	public String getEmail() {
		return user.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
