package com.hb.acadia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.hb.acadia.model.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
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

	

}
