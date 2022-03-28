package org.projet.controller;


import java.util.List;
import java.util.NoSuchElementException;

import org.projet.data.DTO.CDDTO;
import org.projet.data.entity.BookEntity;
import org.projet.data.entity.BookRefEntity;
import org.projet.data.entity.CdEntity;
import org.projet.data.entity.CdRefEntity;
import org.projet.data.entity.ReservationBookEntity;
import org.projet.data.entity.ReservationCdEntity;
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


	@GetMapping()
	public List <CdRefEntity> findAllCds(@RequestParam(required = false) String artist,@RequestParam(required = false) String title, @RequestParam(required=false) Long id, @RequestParam(required = false) boolean disponible ){
		if(artist == null && id !=null && disponible == true || disponible ==false) {
			return (List<CdRefEntity>) cdService.getCdRefbyId(id);

		} else if (id == null && artist!=null && (disponible == true || disponible ==false)) {
			return cdService.getCDRefByArtist(artist);

		} else if (id==null && artist != null && disponible==true) { 
			return cdService.getRefByArtistAndIsDispo(artist, disponible);				
		} else if(id == null && artist!=null && (disponible == true || disponible ==false) && title !=null){
			return cdService.getCdByTitle(title);
		}	else {
			return cdService.getAllRef();
		}

	}


	@GetMapping("/entity")
	public List <CdEntity> getAllCdEntities(@RequestParam(required = false) boolean disponible) {

		if(!disponible) {
			return cdService.getAllCdEntity();
		} else {
			return cdService.getAllCDDispo();
		}
	}

	@PostMapping("/add")
	public ResponseEntity <CdEntity> addDisque(@RequestBody CDDTO cdDTO) throws CDAlreadyExistsException {

		if(cdService.checkIfCDExists(cdDTO.getTitle())) {
			throw new CDAlreadyExistsException("CDRef déjà en base");
		} else {
			List<CdEntity> newCd = cdService.createCD(cdDTO);
			System.out.println("les livres sont ajoutés en base");
			return new ResponseEntity<CdEntity>(HttpStatus.CREATED);
		}
	}

	@DeleteMapping("/deleteEntity")
	public ResponseEntity <Void> deleteCD(@RequestParam Long id) throws CDNotFoundException{
		cdService.deleteCDById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@DeleteMapping("/deleteRef")
	public ResponseEntity <Void> deleteCDRef(@RequestParam Long id) throws NoSuchElementException{
		cdService.deleteCDRefById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}



}
