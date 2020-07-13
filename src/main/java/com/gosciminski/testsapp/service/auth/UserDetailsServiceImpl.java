package com.gosciminski.testsapp.service.auth;

import com.gosciminski.testsapp.conf.PdfUserDetails;
import com.gosciminski.testsapp.model.User;
import com.gosciminski.testsapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
	public UserDetailsServiceImpl(UserService userService) {
		this.userService = userService;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userService.findUserByName(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		} else {
			PdfUserDetails pdfUserDetails = new PdfUserDetails(user);
			return pdfUserDetails;
		}

    }
    
}