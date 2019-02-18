package com.hb.acadia.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Video {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(unique=true, nullable=false)
	private String uuid;
	
	@Column(unique=true, nullable=false)
	private String path;
	@ManyToMany(mappedBy="videos",fetch=FetchType.LAZY)
	private Set<Training> trainings;
	
	public Video() {
		
	}

	public Video(String uuid, String path, Set<Training> trainings) {
		this.uuid = uuid;
		this.path = path;
		this.trainings = trainings;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Set<Training> getTrainings() {
		return trainings;
	}

	public void setTrainings(Set<Training> trainings) {
		this.trainings = trainings;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", uuid=" + uuid + ", path=" + path
				+ ", trainings=" + trainings + "]";
	}
	
}
