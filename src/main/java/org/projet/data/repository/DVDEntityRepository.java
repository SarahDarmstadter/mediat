package org.projet.data.repository;

import java.util.List;

import javax.validation.Valid;

import org.projet.data.entity.DvdEntity;
import org.projet.data.entity.DvdRefEntity;
import org.projet.data.entity.TypeDVD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DVDEntityRepository extends JpaRepository<DvdEntity, Long> {

	public List<DvdEntity> findAllByisDispo(boolean b);
	public List<DvdEntity> findAllByisDispoAndReference(boolean b, DvdRefEntity reference);
	public Integer countAllByReference(DvdRefEntity dvdReference);
	public List<DvdEntity> findAllByisDispoAndReferenceAndTypeDvd(boolean b, @Valid DvdRefEntity reference,TypeDVD typeDvd);
	public List<DvdEntity> findByTypeDvd(TypeDVD typeDvd);

}
