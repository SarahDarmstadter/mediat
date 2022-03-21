package org.projet.controller;


import java.util.List;
import java.util.NoSuchElementException;
import org.projet.data.DTO.DvDDTO;
import org.projet.data.entity.DVDEntity;
import org.projet.data.entity.DVDRefEntity;
import org.projet.exceptions.DVDAlreadyExistsException;
import org.projet.exceptions.DVDNotFoundException;
import org.projet.service.DVDService;
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
}
