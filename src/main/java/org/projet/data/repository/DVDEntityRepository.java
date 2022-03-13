package org.projet.data.repository;

import java.util.List;

import javax.validation.Valid;

import org.projet.data.entity.DVDEntity;
import org.projet.data.entity.DVDRefEntity;
import org.projet.data.entity.TypeDVD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DVDEntityRepository extends JpaRepository<DVDEntity, Long> {

	public List<DVDEntity> findAllByisDispo(boolean b);
	public List<DVDEntity> findAllByisDispoAndReference(boolean b, DVDRefEntity reference);
	public Integer countAllByReference(DVDRefEntity dvdReference);
	public List<DVDEntity> findAllByisDispoAndReferenceAndTypeDvd(boolean b, @Valid DVDRefEntity reference,TypeDVD typeDvd);
	public List<DVDEntity> findByTypeDvd(TypeDVD typeDvd);

}
