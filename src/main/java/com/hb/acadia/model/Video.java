package com.hb.acadia.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@ToString
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true, nullable = false)
	private String uuid;
	
	@NotNull
	@NotEmpty
	@Column(length=255)
	private String name;
	
	@Column(unique = true, nullable = false)
	private String path;
	
	@ManyToMany(mappedBy = "videos", fetch = FetchType.LAZY)
	private Set<Training> trainings;

}
