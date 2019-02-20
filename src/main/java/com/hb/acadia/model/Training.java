package com.hb.acadia.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.hb.acadia.model.user.Trainer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@ToString
public class Training {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String uuid;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	private Trainer trainer;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	@NotNull
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Video> videos;
	
	@OneToMany(mappedBy="training",fetch=FetchType.EAGER)
	private Set<Comment> comments;
	
	@NotNull
	private String description;
	
	@NotNull
	private Level difficulty;
	
	@NotNull
	private int duration;
	
	@NotNull
	private double price;
	
	private boolean isActive;

	
	/**
	 * 
	 * @author anis
	 * 
	 * Level of training difficulty.
	 *
	 */
	public static enum Level {
		BEGINNER, INTERMEDIATE, CONFIRMED
	}
	
}
