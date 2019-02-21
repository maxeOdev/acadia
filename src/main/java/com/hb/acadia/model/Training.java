package com.hb.acadia.model;

import java.util.Set;
import java.util.UUID;

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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
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
	
	public Training() {
		this.uuid = UUID.randomUUID().toString();
	}

	/**
	 * @param trainer
	 * @param category
	 * @param videos
	 * @param comments
	 * @param description
	 * @param difficulty
	 * @param duration
	 * @param price
	 * @param isActive
	 */
	public Training(@NotNull Trainer trainer, @NotNull Category category, @NotNull Set<Video> videos,
			Set<Comment> comments, @NotNull String description, @NotNull Level difficulty, @NotNull int duration,
			@NotNull double price, boolean isActive) {
		this();
		this.trainer = trainer;
		this.category = category;
		this.videos = videos;
		this.comments = comments;
		this.description = description;
		this.difficulty = difficulty;
		this.duration = duration;
		this.price = price;
		this.isActive = isActive;
	}
	
}
