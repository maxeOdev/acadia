package com.hb.acadia.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true, nullable = false)
	private String uuid;

	@Column(unique = true, nullable = false)
	private String path;
	@ManyToMany(mappedBy = "videos", fetch = FetchType.LAZY)
	private Set<Training> trainings;

	@Override
	public String toString() {
		return "Video [id=" + id + ", uuid=" + uuid + ", path=" + path + ", trainings=" + trainings + "]";
	}

}
