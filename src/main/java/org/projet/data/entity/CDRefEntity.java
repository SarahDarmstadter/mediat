package org.projet.data.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Entity
@JsonInclude(JsonInclude.Include.ALWAYS)

public class CDRefEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name="title")
    private String title;

	@Column(name="artist")
    private String artist;

	@Column(name="publication_date")
    private LocalDateTime publicationDate;

	@Column(name="song_number")
    private Integer songNumber;

	@Column(name="duration")
    private Integer duration;
    
	@Column(name="copies")
    private Integer copies;
    
	@OneToMany(mappedBy = "reference", cascade = CascadeType.ALL)
	@JsonIgnore
    private List <CDEntity> cdList;
}
