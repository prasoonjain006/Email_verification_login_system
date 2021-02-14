package com.example.demo.appUser;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.registration.token.ConfirmationToken;
import com.example.demo.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private final String USER_NOT_FOUND_MSG="user with %s not found";
	private final ConfirmationTokenService confirmationTokenService;
	
	
	public AppUserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ConfirmationTokenService confirmationTokenService) {
		
		super();
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.confirmationTokenService=confirmationTokenService;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email).orElseThrow( ()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)) );
	}
	
	
	public String signUpUser(AppUser appUser) {
		boolean userExists =userRepository.findByEmail(appUser.getEmail()).isPresent();
		if(userExists) {
			throw new IllegalStateException("Email Exists already");
		}
		else {
			String encodedPassword=bCryptPasswordEncoder.encode(appUser.getPassword());
			appUser.setPassword(encodedPassword);
			userRepository.save(appUser);
		}
		
		String token =UUID.randomUUID().toString();
		ConfirmationToken confirmationToken =new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15),
				appUser
			);
		confirmationTokenService.saveConfirmationToken(confirmationToken);
				
		// TODO Send EMAil
		return token;
	}
	
	 public int enableAppUser(String email) {
	        return userRepository.enableAppUser(email);
	    }
	

}
