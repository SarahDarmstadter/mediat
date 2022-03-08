package org.projet.controller;

import java.util.List;
import java.util.Optional;

import org.projet.exceptions.UserAlreadyExistsException;
import org.projet.exceptions.UserNotFoundException;
import org.projet.model.UserEntity;
import org.projet.model.UserRepository;
import org.projet.model.UserRequest;
import org.projet.model.UserRole;
import org.projet.model.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

	private String salt = "$2b$10$//DXiVVE59p7G5k/4Klx/e";

	@Autowired
	UserRepository userRepository;

	//	@Autowired
	//	BcryptEncoder bcryptEncorder;


	@GetMapping
	public List <UserEntity> findAllUsers(){
		return userRepository.findAll();
	}

	@GetMapping("/{idUser}")
	public Optional<UserEntity> findUserById(@PathVariable Long idUser) {
		return userRepository.findById(idUser);
	}

	@GetMapping("/search")
	public UserEntity findUserByEmail(@RequestParam String email) throws UserNotFoundException {
		return userRepository.findByEmail(email);
	}

	@PostMapping("/auth/signup")
	public ResponseEntity <UserEntity> createUser(@RequestBody UserRequest userRequest) throws UserAlreadyExistsException {
		//Check if user exists already
		if(checkIfUserExists(userRequest.getEmail())) {
			throw new UserAlreadyExistsException("Email déjà utilisé");
		} else {

			UserEntity userEntity = new UserEntity();
			userEntity.setPassword(BCrypt.hashpw(userRequest.getEmail(), BCrypt.gensalt(salt)));
			userEntity.setFirstName(userRequest.getFirstname());
			userEntity.setLastName(userRequest.getLastname());
			userEntity.setEmail(userRequest.getEmail());
			userEntity.setUserRole(UserRole.USER);
			userEntity.setUserStatus(UserStatus.CONNECTED);
			userEntity = userRepository.save(userEntity);
			return new ResponseEntity<UserEntity>(userEntity, HttpStatus.CREATED);
		}
	}

	public boolean checkIfUserExists(String email) {
		return userRepository.findByEmail(email) != null ? true : false;
	}

	@PostMapping("/auth/login")
	public ResponseEntity <UserEntity> login(@RequestBody UserRequest userRequest) throws UserNotFoundException {
		if(!checkIfUserExists(userRequest.getEmail())){
			throw new UserNotFoundException("Email non reconnu");
		} else {
			
			UserEntity userInBase = userRepository.findByEmail(userRequest.getEmail());
			String hashedPsw = userInBase.getPassword();
			if(BCrypt.checkpw(userRequest.getEmail(), hashedPsw)) {
				userInBase.setUserStatus(UserStatus.CONNECTED);
				return new ResponseEntity<UserEntity>(userInBase, HttpStatus.ACCEPTED);
			} else throw new UserNotFoundException("Mot de passe invalide");
		}

	}

}

