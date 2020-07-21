package com.gosciminski.testsapp.service.jpaimpl;

import java.util.HashSet;
import java.util.Set;

import com.gosciminski.testsapp.conf.WebMvcConfiguration;
import com.gosciminski.testsapp.dto.create.UserCreatorDto;
import com.gosciminski.testsapp.enums.RoleType;
import com.gosciminski.testsapp.exceptions.UserNotFoundException;
import com.gosciminski.testsapp.model.Role;
import com.gosciminski.testsapp.model.User;
import com.gosciminski.testsapp.repisitory.UserRepository;
import com.gosciminski.testsapp.service.UserService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

@Service("userService")
public class UserServiceJpa implements UserService {
    private final UserRepository userRepository;
	private final WebMvcConfiguration webMvcConfig;

    public UserServiceJpa(UserRepository userRepository, WebMvcConfiguration webMvcConfig) {
        this.userRepository = userRepository;
		this.webMvcConfig = webMvcConfig;
    }

	@Override
	public User saveUser(UserCreatorDto userCreatorDto) {
        if(findUserByName(userCreatorDto.getName()) != null)
		{
			throw new IllegalArgumentException("User with name " + userCreatorDto.getName() + " exist.");
		}

		userCreatorDto.setPassword(webMvcConfig.passwordEncoder().encode(userCreatorDto.getPassword()));
		Set<Role> roles = new HashSet<>();
		Role userRole = new Role();
		userRole.setRoleType(RoleType.USER);
		roles.add(userRole);

		User newUser = new User(userCreatorDto.getEmail(),userCreatorDto.getPassword(),userCreatorDto.getName(),1,roles);
		return userRepository.save(newUser);
	}

	@Override
	public User findUserByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public User getUser() throws UserNotFoundException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User user = findUserByName(username);

		if(user == null){
			throw new UserNotFoundException();
		}

		return user;
	}
    
  
    
    
}