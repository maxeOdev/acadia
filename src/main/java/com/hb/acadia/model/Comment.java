package com.hb.acadia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.hb.acadia.model.user.User;

@Entity
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

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", date=" + date + ", stars=" + stars + ", parentComment="
				+ parentComment + ", training=" + training + ", user=" + user + "]";
	}



	

}
