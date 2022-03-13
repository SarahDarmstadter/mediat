package org.projet.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.projet.data.entity.DVDEntity;
import org.projet.data.entity.DVDRefEntity;
import org.projet.data.entity.TypeDVD;
import org.projet.data.repository.CDEntityRepository;
import org.projet.data.repository.CDRefEntityRepository;
import org.projet.data.repository.DVDEntityRepository;
import org.projet.data.repository.DVDRefEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DVDService {

	@Autowired
	DVDEntityRepository dvdEntityRepository;
	
	@Autowired
	DVDRefEntityRepository dvdRefRepository; 
	
	// Retrouver tous les dvd disponibles 
	public List<DVDEntity> getAlldvdDispo(){
		return dvdEntityRepository.findAllByisDispo(true);
	}
	
	//Retrouver tous les dvd disponibles par reference 
	public List <DVDEntity> getAllDVDDispoByRef(DVDRefEntity reference){
		return dvdEntityRepository.findAllByisDispoAndReference(true, reference);
	}
	
	//Compter le nombre de dvd d'une même référence 
	public Integer getDVDDispoCountByRef(DVDRefEntity dvdReference) {
		return dvdEntityRepository.countAllByReference(dvdReference);
	}
	
	//Retrouver les dvd par type 
	public List <DVDEntity> getAllDVDbyType(TypeDVD typeDvd){
		return dvdEntityRepository.findByTypedvd(typeDvd);	
	}
	
	//retrouver les DVD disponibles par reference et type
	public List <DVDEntity> getDvdDispoByRefAndType(@Valid DVDRefEntity reference, TypeDVD typeDvd){
		return dvdEntityRepository.findAllByisDispoAndReferenceAndTypeDvd(true, reference, typeDvd );
	}
	
	// Retrouver tous les dvd d'un réalisateur
	public List <DVDRefEntity> getDVDRefByDirector(String director) {
		return dvdRefRepository.findByDirectorIgnoreCase(director);
	}

	//Verifier la disponibilité d'un dvd
	public boolean dvdIsDispo(DVDEntity dvdEntity) throws NoSuchElementException{
		//verifions qu'il existe en bdd
		DVDEntity dvd = dvdEntityRepository.findById(dvdEntity.getId())
							.orElseThrow(() -> new NoSuchElementException("Le DVD " + dvdEntity.getId() + " n'existe pas."));
		return dvdEntity.getIsDispo();
		
	}
	
	

	//Updater un dvd (dispo / plus dispo)
	public DVDEntity updateDvd(DVDEntity dvdEntity) {
		if (!dvdEntityRepository.existsById(dvdEntity.getId())) {
            throw new NoSuchElementException("Le disque " + dvdEntity.getId() + " n'existe pas.");
        }
        return dvdEntityRepository.save(dvdEntity);
	}

	

}
