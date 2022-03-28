package org.projet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.projet.data.DTO.CDDTO;
import org.projet.data.DTO.DvDDTO;
import org.projet.data.entity.CdEntity;
import org.projet.data.entity.CdRefEntity;
import org.projet.data.entity.DvdEntity;
import org.projet.data.entity.DvdRefEntity;
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
	
	public List<DvdEntity> getAlldvdDispo(){
		return dvdEntityRepository.findAllByisDispo(true);
	}
	
	public List <DvdEntity> getAllDVDDispoByRef(DvdRefEntity reference){
		return dvdEntityRepository.findAllByisDispoAndReference(true, reference);
	}
	
	public Integer getDVDDispoCountByRef(DvdRefEntity dvdReference) {
		return dvdEntityRepository.countAllByReference(dvdReference);
	}
		public List <DvdEntity> getAllDVDbyType(TypeDVD typeDvd){
		return dvdEntityRepository.findByTypeDvd(typeDvd);	
	}
		public List <DvdEntity> getDvdDispoByRefAndType(@Valid DvdRefEntity reference, TypeDVD typeDvd){
		return dvdEntityRepository.findAllByisDispoAndReferenceAndTypeDvd(true, reference, typeDvd );
	}
	
	public List <DvdRefEntity> getDVDRefByDirector(String director) {
		return dvdRefRepository.findByDirectorIgnoreCase(director);
	}
	public boolean dvdIsDispo(DvdEntity dvdEntity) throws NoSuchElementException{
		DvdEntity dvd = dvdEntityRepository.findById(dvdEntity.getId())
							.orElseThrow(() -> new NoSuchElementException("Le DVD " + dvdEntity.getId() + " n'existe pas."));
		return dvd.getIsDispo();
		
	}
	
	public DvdEntity updateDvd(DvdEntity dvdEntity) {
		if (!dvdEntityRepository.existsById(dvdEntity.getId())) {
            throw new NoSuchElementException("Le DVD " + dvdEntity.getId() + " n'existe pas.");
        }
        return dvdEntityRepository.save(dvdEntity);
	}

	public List<DvdRefEntity> getAllRef() {
		return dvdRefRepository.findAll();
	}

	public DvdEntity getDVDById(Long id) {
		return dvdEntityRepository.getById(id);
	}

	public List<DvdEntity> getDVDByDirectorAndIsDispo(String director, boolean b) {
		List<DvdRefEntity> listRef = dvdRefRepository.findByDirectorIgnoreCase(director);
		List <DvdEntity> listDVDDispo = new ArrayList<>();
		for (DvdRefEntity dvdRefEntity : listRef) {
			List <DvdEntity> dvdDispo =  getAllDVDDispoByRef(dvdRefEntity);
			listDVDDispo.addAll(dvdDispo);
		}
		return listDVDDispo;

	}

	public List<DvdEntity> createDVD(DvDDTO dvdDTO) {
		DvdRefEntity dvdRefEntity = new DvdRefEntity();
		dvdRefEntity.setDirector(dvdDTO.getDirector());
		dvdRefEntity.setDuration(dvdDTO.getDuration());
		dvdRefEntity.setCopies(dvdDTO.getCopies());
//		dvdRefEntity.setType(dvdDTO.getTypeDvd());
		dvdRefEntity.setPublicationDate(dvdDTO.getPublicationDate());
		dvdRefEntity.setTitle(dvdDTO.getTitle());
		dvdRefEntity = dvdRefRepository.save(dvdRefEntity);
		List <DvdEntity> dvdList = new ArrayList<DvdEntity>();

		for (int i = 0; i < dvdDTO.getCopies(); i++) {

			DvdEntity dvdEntity = new DvdEntity();
			dvdEntity.setIsDispo(true);
			dvdEntity.setReference(dvdRefEntity);
			dvdEntityRepository.save(dvdEntity);	
			dvdList.add(dvdEntity);

		} dvdRefEntity.setDvdList(dvdList); 	
			return dvdList;

	}

	public boolean checkIfDVDExists(String title) {
		if(dvdRefRepository.findByTitle(title) == null) {
			return false;
		}
		return true;
	}

	// supprimer un disque en particulier sans supprimer la réference
	public void deleteDVDById(Long id) {
		DvdEntity dvd = dvdEntityRepository.getById(id);
		dvdEntityRepository.delete(dvd);
	DvdRefEntity dvdRef = dvdRefRepository.getById(dvd.getId());
		dvdRef.setCopies(dvdRef.getCopies()-1);	
		updateDvdRef(dvdRef);
	}

	// mise à jour du bookRefEntity 
	
	public DvdRefEntity updateDvdRef(DvdRefEntity dvdRefEntity) {
		if (!dvdRefRepository.existsById(dvdRefEntity.getId())) {
			throw new NoSuchElementException("Le dvd " + dvdRefEntity.getId() + " n'existe pas.");
		}
		return dvdRefRepository.save(dvdRefEntity);
	}
	
	
	public void deleteDVDRefById(long id) {
		DvdRefEntity dvdRef = dvdRefRepository.getById(id);
		
		for (int i = 0; i < dvdRef.getCopies(); i++) {
			DvdEntity dvdEntity = dvdEntityRepository.getById(dvdRef.getId());
			dvdEntityRepository.delete(dvdEntity);
		}
		dvdRefRepository.delete(dvdRef);	
	}



	public List<DvdRefEntity> getDvdRefbyId(Long id) {
		return dvdRefRepository.findAllById(id);
	}	

	public List<DvdRefEntity> getRefByArtistAndIsDispo(String director, boolean b) {
		List <DvdRefEntity> listRef = dvdRefRepository.findByDirectorIgnoreCase(director);
		List <CdEntity> listDvdDispo = new ArrayList<>();

		List <DvdRefEntity> listRefDispo = new ArrayList<>();
		for (DvdRefEntity dvdRefEntity : listRef) {
			List <DvdEntity> dvdDispo =  getAllDVDDispoByRef(dvdRefEntity);
			if (dvdDispo.size() >=1) {
				listRefDispo.add(dvdRefEntity);
			}
		} return listRefDispo;
	}

	public List<DvdEntity> getAllDvdEntity() {
		// TODO Auto-generated method stub
		return dvdEntityRepository.findAll();
	}

	public List<DvdRefEntity> getDvdByTitle(String title) {
		return dvdRefRepository.findAllByTitle(title);
	}
	


	
	
	

}
