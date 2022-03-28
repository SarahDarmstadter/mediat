package org.projet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.projet.data.DTO.BookDTO;
import org.projet.data.DTO.CDDTO;
import org.projet.data.entity.BookEntity;
import org.projet.data.entity.BookRefEntity;
import org.projet.data.entity.CdEntity;
import org.projet.data.entity.CdRefEntity;
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
	
	public List<CdEntity> getAllCDDispo(){
		return cdEntityRepository.findAllByisDispo(true);
	}
	
	public List <CdEntity> getAllCDDispoByRef(CdRefEntity reference){
		return cdEntityRepository.findAllByisDispoAndReference(true, reference);
	}
	
	public Integer getCDDispoCountByRef(CdRefEntity cdReference) {
		return cdEntityRepository.countAllByReference(cdReference);
	}
	
	public List <CdRefEntity> getCDRefByArtist(String artist) {
		return cdRefRepository.findByArtistIgnoreCase(artist);
	}

	public boolean cdIsDispo(CdEntity cdEntity) throws NoSuchElementException{
		CdEntity cd =cdEntityRepository.findById(cdEntity.getId())
							.orElseThrow(() -> new NoSuchElementException("Le disque " + cdEntity.getId() + " n'existe pas."));
		return cdEntity.getIsDispo();	
		
	}

	public CdEntity updateCd(CdEntity cdEntity) {
		if (!cdEntityRepository.existsById(cdEntity.getId())) {
            throw new NoSuchElementException("Le disque " + cdEntity.getId() + " n'existe pas.");
        }
        return cdEntityRepository.save(cdEntity);
	}

	public List<CdRefEntity> getAllRef() {
		return cdRefRepository.findAll();
	}

	public CdEntity getCDById(Long id) {
		return cdEntityRepository.getById(id);
	}

	public List<CdEntity> getCDByArtistAndIsDispo(String artist, boolean b) {
		List<CdRefEntity> listRef = cdRefRepository.findByArtistIgnoreCase(artist);
		List <CdEntity> listCDDispo = new ArrayList<>();
		for (CdRefEntity cdRefEntity : listRef) {
			List <CdEntity> cdDispo =  getAllCDDispoByRef(cdRefEntity);
			listCDDispo.addAll(cdDispo);
		}
		return listCDDispo;

	}

	public List<CdEntity> createCD(CDDTO cdDTO) {
		CdRefEntity cdRefEntity = new CdRefEntity();
		cdRefEntity.setArtist(cdDTO.getArtist());
		cdRefEntity.setDuration(cdDTO.getDuration());
		cdRefEntity.setCopies(cdDTO.getCopies());
		cdRefEntity.setSongNumber(cdDTO.getSongNumber());
		cdRefEntity.setPublicationDate(cdDTO.getPublicationDate());
		cdRefEntity.setTitle(cdDTO.getTitle());
		cdRefEntity = cdRefRepository.save(cdRefEntity);
		List <CdEntity> cdList = new ArrayList<CdEntity>();

		for (int i = 0; i < cdDTO.getCopies(); i++) {

			CdEntity cdEntity = new CdEntity();
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

	public void deleteCDById(Long id) {
		CdEntity cd = cdEntityRepository.getById(id);
		cdEntityRepository.delete(cd);
	CdRefEntity cdRef = cdRefRepository.getById(cd.getId());
		cdRef.setCopies(cdRef.getCopies()-1);	
		updateCdRef(cdRef);
	}

	
	public CdRefEntity updateCdRef(CdRefEntity cdRefEntity) {
		if (!cdRefRepository.existsById(cdRefEntity.getId())) {
			throw new NoSuchElementException("Le disque " + cdRefEntity.getId() + " n'existe pas.");
		}
		return cdRefRepository.save(cdRefEntity);
	}

	public void deleteCDRefById(long id) {
		CdRefEntity cdRef = cdRefRepository.getById(id);
		
		for (int i = 0; i < cdRef.getCopies(); i++) {
			CdEntity cdEntity = cdEntityRepository.getById(cdRef.getId());
			cdEntityRepository.delete(cdEntity);
		}
		cdRefRepository.delete(cdRef);
		
	}

	public List<CdRefEntity> getCdRefbyId(Long id) {
		return cdRefRepository.findAllById(id);
<<<<<<< HEAD
	}	
=======
	}

	public List<CdRefEntity> getCdRefByArtist(String artist) {
		return cdRefRepository.findByArtist(artist);
	}

	
>>>>>>> a04640e729022018c70316405b26cd587a92b6d4

	public List<CdRefEntity> getRefByArtistAndIsDispo(String artist, boolean b) {
		List <CdRefEntity> listRef = cdRefRepository.findByArtistIgnoreCase(artist);
		List <CdEntity> listCdDispo = new ArrayList<>();

		List <CdRefEntity> listRefDispo = new ArrayList<>();

		for (CdRefEntity cdRefEntity : listRef) {
			List <CdEntity> cdDispo =  getAllCDDispoByRef(cdRefEntity);
			if (cdDispo.size() >=1) {
				listRefDispo.add(cdRefEntity);
			}
		} return listRefDispo;
	}

	public List<CdEntity> getAllCdEntity() {
		return cdEntityRepository.findAll();
	}

	public List<CdRefEntity> getCdByTitle(String title) {
		return cdRefRepository.findAllByTitle(title);
	}
	



}
