package org.projet.data.repository;

import java.util.Optional;

import org.projet.data.entity.ReservationBookEntity;
import org.projet.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationBookEntityRepository extends JpaRepository<ReservationBookEntity, Long> {

	public Integer countAllByUser(UserEntity user);
	public ReservationBookEntity findByUser(UserEntity user);

}
