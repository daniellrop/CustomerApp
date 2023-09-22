package com.version1.CustomerApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsManager implements UserDetailsManager {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("No user found : " + username));
		
	}

	@Override
	public void createUser(UserDetails user) {
		userRepository.save((User)user);
	}

	@Override
	public void updateUser(UserDetails user) {
		userRepository.save((User)user);
	}

	@Override
	public void deleteUser(String username) {
		User userDetails = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("No user found : " + username));
		userRepository.delete(userDetails);
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		
		
	}

	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username).isPresent();
	}

}
