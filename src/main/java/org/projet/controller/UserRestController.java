package org.projet.controller;



import org.projet.data.DTO.UserDTO;
import org.projet.data.entity.UserEntity;
import org.projet.data.entity.UserStatus;
import org.projet.data.repository.UserRepository;
import org.projet.exceptions.UserAlreadyExistsException;
import org.projet.exceptions.UserNotFoundException;
import org.projet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserRestController {


	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<UserEntity> getAllUser(){
		userService.findAll();
		return new ResponseEntity<UserEntity>(HttpStatus.ACCEPTED);

	}

	@GetMapping("/{idUser}/profile")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Long idUser) throws UserNotFoundException {
		userService.findbyId(idUser).orElseThrow(()-> new UserNotFoundException("id non reconnu"));
		return new ResponseEntity<UserEntity>(HttpStatus.ACCEPTED);
	}

	@GetMapping("/search")
	public ResponseEntity<UserEntity> findUserByEmail(@RequestParam String email) throws UserNotFoundException {
		if(userService.checkIfUserExists(email)) {
			return new ResponseEntity<UserEntity>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/auth/signup")
	public ResponseEntity <UserEntity> createUser(@RequestBody UserDTO userDTO) throws UserAlreadyExistsException {
		//Check if user exists already
		if(userService.checkIfUserExists(userDTO.getEmail())) {
			throw new UserAlreadyExistsException("Email déjà utilisé");
		} else {
			UserEntity newUser = userService.createUser(userDTO);
			return new ResponseEntity<UserEntity>(newUser, HttpStatus.CREATED);
		}
	}

	
	@PostMapping("/auth/login")
	public ResponseEntity <UserEntity> login(@RequestBody UserDTO userDTO) throws UserNotFoundException {
		if(!userService.checkIfUserExists(userDTO.getEmail())){
			throw new UserNotFoundException("Email non reconnu");
		} else if(!userService.checkPassword(userDTO)){
			return new ResponseEntity<UserEntity>(HttpStatus.BAD_REQUEST);
		} else {
			userService.findbyEmail(userDTO.getEmail()).setUserStatus(UserStatus.CONNECTED);
			return new ResponseEntity<UserEntity>(HttpStatus.ACCEPTED);			
		}
	}

	//Modification email / mot de passe // role et statut ?

	@PatchMapping("/{idUser}/modify")
	public ResponseEntity <UserEntity> modifyInfos(@PathVariable long id, @RequestBody UserDTO userDTO) throws UserNotFoundException{
		UserEntity user = userService.findbyId(id).orElseThrow(()-> new UserNotFoundException("Utilisateur non reconnu via son id :" + id ));
		userService.updateUser(user);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@DeleteMapping("/auth/{id}/delete")
	public ResponseEntity <Void> deleteAccount(@PathVariable long id) throws UserNotFoundException{
		userService.deleteUserById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}


}

