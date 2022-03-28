package org.projet.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.projet.data.entity.BookEntity;
import org.projet.data.entity.BookRefEntity;

@Repository
public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {
	
	public List<BookEntity> findAllByisDispo(boolean isdispo);
	public List<BookEntity> findAllByisDispoAndReference(boolean isDispo, BookRefEntity reference);
	public Integer countAllByReference(BookRefEntity reference);
	public List<BookEntity> findAllByReference(Optional<BookRefEntity> bookRef);
	public Optional <BookEntity> findById(Long id);
	
}
