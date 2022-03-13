package org.projet.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.projet.data.entity.CDEntity;
import org.projet.data.entity.CDRefEntity;
import org.projet.data.repository.CDEntityRepository;
import org.projet.data.repository.CDRefEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CDService {

	@Autowired
	CDEntityRepository cdEntityRepository;
	
	@Autowired
	CDRefEntityRepository cdRefRepository; 
	
	// Retrouver tous les disques disponibles 
	public List<CDEntity> getAllBookDispo(){
		return cdEntityRepository.findAllByisDispo(true);
	}
	
	//Retrouver tous les disques disponibles par reference 
	public List <CDEntity> getAllCDDispoByRef(CDRefEntity reference){
		return cdEntityRepository.findAllByisDispoAndReference(true, reference);
	}
	
	//Compter le nombre de livres disponibles d'une même référence 
	public Integer getCDDispoCountByRef(CDRefEntity cdReference) {
		return cdEntityRepository.countAllByReference(cdReference);
	}
	
	// Retrouver tous les disque d'un artiste
	public List <CDRefEntity> getBookRefByArtist(String artist) {
		return cdRefRepository.findByArtistIgnoreCase(artist);
	}

	//Verifier la disponibilité d'un disque
	public boolean cdIsDispo(CDEntity cdEntity) throws NoSuchElementException{
		//verifions qu'il existe en bdd
		CDEntity cd =cdEntityRepository.findById(cdEntity.getId())
							.orElseThrow(() -> new NoSuchElementException("Le disque " + cdEntity.getId() + " n'existe pas."));
		return cdEntity.getIsDispo();	
		
	}

	//Updater un disque (dispo / plus dispo)
	public CDEntity updateCd(CDEntity cdEntity) {
		if (!cdEntityRepository.existsById(cdEntity.getId())) {
            throw new NoSuchElementException("Le disque " + cdEntity.getId() + " n'existe pas.");
        }
        return cdEntityRepository.save(cdEntity);
	}

	



}
