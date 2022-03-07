package org.projet.controller;

import java.util.List;
import java.util.Optional;

import org.projet.config.BcryptEncoder;
import org.projet.exceptions.UserAlreadyExistsException;
import org.projet.model.UserEntity;
import org.projet.model.UserRepository;
import org.projet.model.UserRequest;
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

	private String salt = "DOBBY2022";
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	BcryptEncoder bcryptEncorder;


	@GetMapping
	public List <UserEntity> findAllUsers(){
		return userRepository.findAll();
	}

	@GetMapping("/{idUser}")
	public Optional<UserEntity> findUserById(@PathVariable Long idUser) {
		return userRepository.findById(idUser);
	}

	@GetMapping("/search")
	public Optional<UserEntity> findUserByEmail(@RequestParam String email){
		return userRepository.findByEmail(email);
	}

	@PostMapping("/create-account")
	public ResponseEntity <UserEntity> createUser(@RequestBody UserRequest userRequest) throws UserAlreadyExistsException {
		//Check if user exists already
		if(checkIfUserExists(userRequest.getEmail())) {
			throw new UserAlreadyExistsException("Email déjà utilisé");
		} else {

			UserEntity userEntity = new UserEntity();
			userEntity.setPassword(BCrypt.hashpw(userRequest.getEmail(), BCrypt.gensalt(salt, 10)));
			userEntity = userRepository.save(userEntity);
			return new ResponseEntity<UserEntity>(userEntity, HttpStatus.NO_CONTENT);
		}
	}

	public boolean checkIfUserExists(String email) {
		return userRepository.findByEmail(email) != null ? true : false;
	}


}

