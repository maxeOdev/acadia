package com.hb.acadia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.hb.acadia.model.user.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	private String content;

	private long date;

	private int stars;

	@ManyToOne
	private Comment parentComment;

	@ManyToOne
	private Training training;

	@ManyToOne
	private User user;

	public Comment() {
		
	}

	/**
	 * @param content
	 * @param date
	 * @param stars
	 * @param parentComment
	 * @param training
	 * @param user
	 */
	public Comment(String content, long date, int stars, Comment parentComment, Training training, User user) {
		this.content = content;
		this.date = date;
		this.stars = stars;
		this.parentComment = parentComment;
		this.training = training;
		this.user = user;
	}
	
}
