package org.projet.data.repository;

import java.util.List;

import org.projet.data.entity.BookRefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRefEntityRepository extends JpaRepository<BookRefEntity, Long> {

	List<BookRefEntity> findByAuthorIgnoreCase(String author);

}
