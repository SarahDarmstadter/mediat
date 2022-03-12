package org.projet.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.projet.data.entity.BookEntity;
import org.projet.data.entity.BookRefEntity;

@Repository
public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {
	
	public List<BookEntity> findAllByisDispo(boolean isdispo);
	public List<BookEntity> findAllByisDispoAndReference(boolean isDispo, BookRefEntity reference);
	public Integer countAllByReference(BookRefEntity reference);
	
}
