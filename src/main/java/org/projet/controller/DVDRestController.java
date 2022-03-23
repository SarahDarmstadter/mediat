package org.projet.controller;


import java.util.List;
import java.util.NoSuchElementException;
import org.projet.data.DTO.DvDDTO;
import org.projet.data.entity.CDEntity;
import org.projet.data.entity.DVDEntity;
import org.projet.data.entity.DVDRefEntity;
import org.projet.data.entity.ReservationCDEntity;
import org.projet.data.entity.ReservationDVDEntity;
import org.projet.data.entity.UserEntity;
import org.projet.exceptions.DVDAlreadyExistsException;
import org.projet.exceptions.DVDNotFoundException;
import org.projet.exceptions.ReservationNotFoundException;
import org.projet.service.DVDService;
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
@RequestMapping("/api/films")
public class DVDRestController {


	@Autowired
	DVDService dvdService;

	@Autowired
	UserService userService;

	@Autowired
	ReservationService reservationService;

	@GetMapping("/allfilms")
	public List <DVDRefEntity> findAllDvd(){
		return dvdService.getAllRef();
	}

	@GetMapping("/{artist}")
	public List <DVDRefEntity> findByDirector(@RequestParam String director ){
		return dvdService.getDVDRefByDirector(director);
	}

	@GetMapping("/{id}")
	public DVDEntity getBookById(@PathVariable Long id ) {
		return dvdService.getDVDById(id);
	}

	@GetMapping("/disponibles")
	public List <DVDEntity> getAllDvDDisponibles(){
		return dvdService.getAlldvdDispo();
	}

	@GetMapping("/{director}/disponibles")
	public List <DVDEntity> findDVDByDirectorAndDispo(@PathVariable String director){
		return dvdService.getDVDByDirectorAndIsDispo(director, true);				
	}

	@PostMapping("/addfilms")
	public ResponseEntity <DVDEntity> addFilm(@RequestBody DvDDTO dvdDTO) throws DVDAlreadyExistsException {

		if(dvdService.checkIfDVDExists(dvdDTO.getTitle())) {
			throw new DVDAlreadyExistsException("DVDRef déjà en base");
		} else {
			List<DVDEntity> newDVD = dvdService.createDVD(dvdDTO);
			System.out.println("les films sont ajoutés en base");
			return new ResponseEntity<DVDEntity>(HttpStatus.CREATED);
		}
	}

	@PostMapping("/{idDvd}/{idUser}/reserver")
	public ResponseEntity <ReservationDVDEntity> reserverCD(@PathVariable Long idDvd, @PathVariable Long idUser ) throws Exception{
		UserEntity user = userService.getById(idUser);
		DVDEntity dvd = dvdService.getDVDById(idDvd);
		ReservationDVDEntity resaDvd = reservationService.reserverDVD(dvd, user);
		return new ResponseEntity<ReservationDVDEntity>(resaDvd, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity <Void> deleteDVD(@PathVariable long id) throws DVDNotFoundException{
		dvdService.deleteDVDById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}


	@DeleteMapping("{id}/deleteDvdRef")
	public ResponseEntity <Void> deleteCDRef(@PathVariable long id) throws NoSuchElementException{
		dvdService.deleteDVDRefById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@DeleteMapping("/{idReservation}/cancelReservation")
	public ResponseEntity <Void> deleteResaDVD(@PathVariable Long idReservation) throws ReservationNotFoundException{
		reservationService.cancelResaBookById(idReservation);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}
