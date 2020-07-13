package com.gosciminski.testsapp.conf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.gosciminski.testsapp.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class PdfUserDetails implements UserDetails {

    private User user;

    public PdfUserDetails(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

		user.getRoles().stream().forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getRoleType().toString())));

		user.getRoles().stream()
				.forEach(r -> authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getRoleType().toString())));

		return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getActive() == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getActive() == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getActive() == 1;
    }

    @Override
    public boolean isEnabled() {
        return user.getActive() == 1;
    }
    
}