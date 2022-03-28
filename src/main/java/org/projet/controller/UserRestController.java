package org.projet.controller;



import java.util.List;
import org.projet.data.DTO.UserDTO;
import org.projet.data.entity.BookEntity;
import org.projet.data.entity.CdEntity;
import org.projet.data.entity.DvdEntity;
import org.projet.data.entity.ReservationBookEntity;
import org.projet.data.entity.ReservationCdEntity;
import org.projet.data.entity.ReservationDvdEntity;
import org.projet.data.entity.UserEntity;
import org.projet.data.entity.UserStatus;
import org.projet.exceptions.ReservationNotFoundException;
import org.projet.exceptions.UserAlreadyExistsException;
import org.projet.exceptions.UserNotFoundException;
import org.projet.service.BookService;
import org.projet.service.CDService;
import org.projet.service.DVDService;
import org.projet.service.ReservationService;
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
	UserService userService;
	
	@Autowired 
	BookService bookService;
	
	@Autowired
	CDService cdService;
	
	@Autowired 
	DVDService dvdService;
	
	@Autowired
	ReservationService reservationService;

	@GetMapping()
	public List<UserEntity> getAllUser(){
		return  userService.findAll();

	}

	@GetMapping("/profile")
	public ResponseEntity<UserEntity> getUserById(@RequestParam Long id) throws UserNotFoundException {
		UserEntity user = userService.findbyId(id)
		.orElseThrow(()-> new UserNotFoundException("id non reconnu"));
		
		return new ResponseEntity<UserEntity>(user, HttpStatus.ACCEPTED);
	}

	@GetMapping("/search")
	public ResponseEntity<UserEntity> findUserByEmail(@RequestParam String email) throws UserNotFoundException {
		if(userService.checkIfUserExists(email)) {
			UserEntity user = userService.findbyEmail(email);
			return new ResponseEntity<UserEntity>(user, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("reservations")
	public List<Object> getAllReseravtions(@PathVariable Long id) throws UserNotFoundException{
		UserEntity user = userService.findbyId(id)
				.orElseThrow(()-> new UserNotFoundException("id non reconnu"));
		
		return userService.getAllReservationByUser(user);
	}
	
	@PostMapping("/reserver/livre")
	public ResponseEntity <ReservationBookEntity> reserverBook(@RequestParam Long idBook, @RequestParam Long idUser ) throws Exception{
	
		UserEntity user = userService.getById(idUser);
		BookEntity book = bookService.getBookById(idBook);
		ReservationBookEntity resaBook = reservationService.reserverBook(book, user);
		return new ResponseEntity<ReservationBookEntity>(resaBook, HttpStatus.CREATED);
	}
	
	@PostMapping("reserver/disque")
	public ResponseEntity <ReservationCdEntity> reserverCD(@RequestParam Long idCD, @RequestParam Long idUser ) throws Exception{
		UserEntity user = userService.getById(idUser);
		CdEntity cd = cdService.getCDById(idCD);
		ReservationCdEntity resaCD = reservationService.reserverCD(cd, user);
		return new ResponseEntity<ReservationCdEntity>(resaCD, HttpStatus.CREATED);
	}
	
	@PostMapping("reserver/dvd")
	public ResponseEntity <ReservationDvdEntity> reserverDvd(@RequestParam Long idDvd, @RequestParam Long idUser ) throws Exception{
		UserEntity user = userService.getById(idUser);
		DvdEntity dvd = dvdService.getDVDById(idDvd);
		ReservationDvdEntity resaDvd = reservationService.reserverDVD(dvd, user);
		return new ResponseEntity<ReservationDvdEntity>(resaDvd, HttpStatus.CREATED);
	}
	

	@DeleteMapping("/cancelReservation/book")
	public ResponseEntity <Void> deleteResaBook(@RequestParam Long idReservation) throws ReservationNotFoundException{
		reservationService.cancelResaBookById(idReservation);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@DeleteMapping("/cancelReservation/disque")
	public ResponseEntity <Void> deleteResaCD(@RequestParam Long idReservation) throws ReservationNotFoundException{
		reservationService.cancelResaCDById(idReservation);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@DeleteMapping("/cancelReservation/dvd")
	public ResponseEntity <Void> deleteResaDVD(@RequestParam Long idReservation) throws ReservationNotFoundException{
		reservationService.cancelResaBookById(idReservation);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@PostMapping("/signup")
	public ResponseEntity <UserEntity> createUser(@RequestBody UserDTO userDTO) throws UserAlreadyExistsException {
		//Check if user exists already
		if(userService.checkIfUserExists(userDTO.getEmail())) {
			throw new UserAlreadyExistsException("Email déjà utilisé");
		} else {
			UserEntity newUser = userService.createUser(userDTO);
			return new ResponseEntity<UserEntity>(newUser, HttpStatus.CREATED);
		}
	}

	
	@PostMapping("/login")
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


	@PatchMapping("/modify")
	public ResponseEntity <UserEntity> modifyInfos(@RequestParam Long id, @RequestBody UserDTO userDTO) throws UserNotFoundException{
		UserEntity user = userService.findbyId(id).orElseThrow(()-> new UserNotFoundException("Utilisateur non reconnu via son id :" + id ));
		userService.updateUser(user);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@DeleteMapping("/delete")
	public ResponseEntity <Void> deleteAccount(@RequestParam Long id) throws UserNotFoundException{
		userService.deleteUserById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	



}

