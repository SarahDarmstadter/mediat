package org.projet.data.repository;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> a04640e729022018c70316405b26cd587a92b6d4

import org.projet.data.entity.ReservationBookEntity;
import org.projet.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationBookEntityRepository extends JpaRepository<ReservationBookEntity, Long> {

	public Integer countAllByUser(UserEntity user);
	public List<ReservationBookEntity> findByUser(UserEntity user);

}
