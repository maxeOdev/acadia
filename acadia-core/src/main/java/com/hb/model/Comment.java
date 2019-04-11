package com.hb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.hb.model.user.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + (int) (date ^ (date >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + stars;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (date != other.date)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (stars != other.stars)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}
