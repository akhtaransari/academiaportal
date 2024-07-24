package com.leucine.academiaportal.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.leucine.academiaportal.entity.User;
import com.leucine.academiaportal.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service implementation for loading user-specific data for Spring Security.
 * This service fetches user details from the database and converts them into
 * Spring Security's UserDetails object for authentication and authorization.
 */
@Service
@Slf4j
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Loads user-specific data by username or email.
	 *
	 * This method is called by Spring Security during authentication to retrieve
	 * the user details from the database using the provided username or email.
	 * It constructs a UserDetails object that includes user authorities.
	 *
	 * @param usernameOrEmail the username or email of the user whose details are to be loaded
	 * @return a UserDetails object containing user information and granted authorities
	 * @throws UsernameNotFoundException if the user is not found in the database
	 */
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		log.info("Attempting to load user by usernameOrEmail: {}", usernameOrEmail);

		// Retrieve the user from the database based on the provided username or email
		Optional<User> optionalUser = userRepository.findByUsernameOrEmail(usernameOrEmail);

		// Check if the user exists
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			log.info("User found: {}", user.getUsername());

			// Convert user roles to Spring Security authorities
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

			// Return a Spring Security UserDetails object with the user's email, password, and authorities
			return new org.springframework.security.core.userdetails.User(
					user.getEmail(),
					user.getPassword(),
					grantedAuthorities
			);
		} else {
			// Log a warning and throw an exception if the user is not found
			log.warn("User not found with usernameOrEmail: {}", usernameOrEmail);
			throw new BadCredentialsException("User Details not found with this username or email: " + usernameOrEmail);
		}
	}
}
