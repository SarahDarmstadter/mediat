package org.projet.controller;


import java.util.List;
import java.util.NoSuchElementException;

import org.projet.data.DTO.CDDTO;
import org.projet.data.entity.BookEntity;
import org.projet.data.entity.CDEntity;
import org.projet.data.entity.CDRefEntity;
import org.projet.data.entity.ReservationBookEntity;
import org.projet.data.entity.ReservationCDEntity;
import org.projet.data.entity.UserEntity;
import org.projet.exceptions.CDAlreadyExistsException;
import org.projet.exceptions.CDNotFoundException;
import org.projet.exceptions.ReservationNotFoundException;
import org.projet.service.CDService;
import org.projet.service.ReservationService;
import org.projet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/disques")
public class CDRestController {


	@Autowired
	CDService cdService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ReservationService reservationService;

	@GetMapping("/alldisques")
	public List <CDRefEntity> findAllCd(){
		return cdService.getAllRef();
	}

	@GetMapping("/{artist}")
	public List <CDRefEntity> findByAuthor(@RequestParam String artist ){
		return cdService.getCDRefByArtist(artist);
	}

	@GetMapping("/{id}")
	public CDEntity getBookById(@PathVariable Long id ) {
			return cdService.getCDById(id);
	}
	
	@GetMapping("/disponibles")
	public List <CDEntity> getAllCDDisponibles(){
		return cdService.getAllCDDispo();
	}
	
	@GetMapping("/{artist}/disponibles")
	public List <CDEntity> findCDByAuthorAndDispo(@PathVariable String artist){
		return cdService.getCDByArtistAndIsDispo(artist, true);				
	}
	
	@PostMapping("/addDisque")
	public ResponseEntity <CDEntity> addDisque(@RequestBody CDDTO cdDTO) throws CDAlreadyExistsException {
		
		if(cdService.checkIfCDExists(cdDTO.getTitle())) {
			throw new CDAlreadyExistsException("CDRef déjà en base");
		} else {
			List<CDEntity> newCd = cdService.createCD(cdDTO);
			System.out.println("les livres sont ajoutés en base");
			return new ResponseEntity<CDEntity>(HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity <Void> deleteCD(@PathVariable long id) throws CDNotFoundException{
	cdService.deleteCDById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@DeleteMapping("{id}/deleteRef")
	public ResponseEntity <Void> deleteCDRef(@PathVariable long id) throws NoSuchElementException{
		cdService.deleteCDRefById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@PostMapping("/{idDvd}/{idUser}/reserver")
	public ResponseEntity <ReservationCDEntity> reserverCD(@PathVariable Long idCD, @PathVariable Long idUser ) throws Exception{
		UserEntity user = userService.getById(idUser);
		CDEntity cd = cdService.getCDById(idCD);
		ReservationCDEntity resaCD = reservationService.reserverCD(cd, user);
		return new ResponseEntity<ReservationCDEntity>(resaCD, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{idReservation}/cancelReservation")
	public ResponseEntity <Void> deleteResaCD(@PathVariable Long idReservation) throws ReservationNotFoundException{
		reservationService.cancelResaCDById(idReservation);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}
