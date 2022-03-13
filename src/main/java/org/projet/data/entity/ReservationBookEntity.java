package org.projet.data.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
public class ReservationBookEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private BookEntity book;

    private LocalDateTime borrowingDate;

    private LocalDateTime returningDate;

    @ManyToOne
    private UserEntity user;
   
}
