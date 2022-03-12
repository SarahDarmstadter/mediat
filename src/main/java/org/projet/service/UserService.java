package org.projet.service;

import java.util.List;
import java.util.Optional;
import org.projet.data.DTO.UserDTO;
import org.projet.data.entity.UserEntity;
import org.projet.data.entity.UserRole;
import org.projet.data.entity.UserStatus;
import org.projet.data.repository.UserRepository;
import org.projet.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	private String salt = "$2b$10$//DXiVVE59p7G5k/4Klx/e";


	public boolean checkIfUserExists(String email) {
		return userRepository.findByEmail(email) != null ? true : false;
	}


	public boolean checkPassword(UserDTO newUser) {
		UserEntity user = userRepository.findByEmail(newUser.getEmail());
		String hashedPsw = user.getPassword();
		System.out.println("hashed " + hashedPsw);
		System.out.println("not hash " + newUser.getPassword());

		if((BCrypt.checkpw(newUser.getPassword(), hashedPsw)) == false) {
			System.out.println("LES MOTS DE PASSE NE MATCHENt PAS ");
			return false;
		} else {
			System.out.println("LES MOTS DE PASSE  MATCHENT ");
			return true;

		}
	}

	public Optional<UserEntity> findbyId(Long id) {
		return userRepository.findById(id);
	}


	public List <UserEntity> findAll() {
		return userRepository.findAll();
	}


	public UserEntity findbyEmail(String email) {
		return userRepository.findByEmail(email);
	}


	public UserEntity createUser(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		userEntity.setPassword(BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(salt)));
		userEntity.setFirstname(userDTO.getFirstname());
		userEntity.setLastname(userDTO.getLastname());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setUserRole(UserRole.USER);
		userEntity.setUserStatus(UserStatus.CONNECTED);
		return userEntity = userRepository.save(userEntity);

	}


	public UserEntity updateUser(UserEntity userEntity) throws UserNotFoundException {
		if(!userRepository.existsById(userEntity.getId())) {
			throw new UserNotFoundException("L'utilisateur ayant pour id " + userEntity.getId() + " n'existe pas.");
		}
		return userRepository.save(userEntity);	
	}

	public void deleteUserById(Long id) {
			UserEntity user = userRepository.getById(id);
			userRepository.delete(user);
	}
	
}









//	//methode pour find all
//	verifier que le user exist 
//	le creer 
//	crypter mot de passe
//	le mettre a jour 



