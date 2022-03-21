package org.projet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.projet.data.DTO.BookDTO;
import org.projet.data.DTO.CDDTO;
import org.projet.data.entity.BookEntity;
import org.projet.data.entity.BookRefEntity;
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
	public List<CDEntity> getAllCDDispo(){
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
	public List <CDRefEntity> getCDRefByArtist(String artist) {
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

	public List<CDRefEntity> getAllRef() {
		return cdRefRepository.findAll();
	}

	public CDEntity getCDById(Long id) {
		return cdEntityRepository.getById(id);
	}

	public List<CDEntity> getCDByArtistAndIsDispo(String artist, boolean b) {
		List<CDRefEntity> listRef = cdRefRepository.findByArtistIgnoreCase(artist);
		List <CDEntity> listCDDispo = new ArrayList<>();
		for (CDRefEntity cdRefEntity : listRef) {
			List <CDEntity> cdDispo =  getAllCDDispoByRef(cdRefEntity);
			listCDDispo.addAll(cdDispo);
		}
		return listCDDispo;

	}

	//Enregistrer des disques 
	public List<CDEntity> createCD(CDDTO cdDTO) {
		CDRefEntity cdRefEntity = new CDRefEntity();
		cdRefEntity.setArtist(cdDTO.getArtist());
		cdRefEntity.setDuration(cdDTO.getDuration());
		cdRefEntity.setCopies(cdDTO.getCopies());
		cdRefEntity.setSongNumber(cdDTO.getSongNumber());
		cdRefEntity.setPublicationDate(cdDTO.getPublicationDate());
		cdRefEntity.setTitle(cdDTO.getTitle());
		cdRefEntity = cdRefRepository.save(cdRefEntity);
		List <CDEntity> cdList = new ArrayList<CDEntity>();

		for (int i = 0; i < cdDTO.getCopies(); i++) {

			CDEntity cdEntity = new CDEntity();
			cdEntity.setIsDispo(true);
			cdEntity.setReference(cdRefEntity);
			cdEntityRepository.save(cdEntity);	
			cdList.add(cdEntity);

		} cdRefEntity.setCdList(cdList); 	
			return cdList;

	}

	public boolean checkIfCDExists(String title) {
		if(cdRefRepository.findByTitle(title) == null) {
			return false;
		}
		return true;
	}

	// supprimer un disque en particulier sans supprimer la réference
	public void deleteCDById(Long id) {
		CDEntity cd = cdEntityRepository.getById(id);
		cdEntityRepository.delete(cd);
	CDRefEntity cdRef = cdRefRepository.getById(cd.getId());
		cdRef.setCopies(cdRef.getCopies()-1);	
		updateCdRef(cdRef);
	}

	// mise à jour du bookRefEntity 
	
	public CDRefEntity updateCdRef(CDRefEntity cdRefEntity) {
		if (!cdRefRepository.existsById(cdRefEntity.getId())) {
			throw new NoSuchElementException("Le disque " + cdRefEntity.getId() + " n'existe pas.");
		}
		return cdRefRepository.save(cdRefEntity);
	}

	public void deleteCDRefById(long id) {
		CDRefEntity cdRef = cdRefRepository.getById(id);
		
		for (int i = 0; i < cdRef.getCopies(); i++) {
			CDEntity cdEntity = cdEntityRepository.getById(cdRef.getId());
			cdEntityRepository.delete(cdEntity);
		}
		cdRefRepository.delete(cdRef);
		
	}
	
	



}
