package com.hb.acadia.model.user;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.hb.acadia.model.Training;
import com.hb.acadia.model.Video;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@AssociationOverrides({
		@AssociationOverride(name = "items.user", joinColumns = @JoinColumn(name = "idUser")),
		@AssociationOverride(name = "items.training", joinColumns = @JoinColumn(name = "idTraining"))})
public class UserMetadata {

	@EmbeddedId
	private PK_Customer_Training items;

	@Column(nullable = false, unique = true)
	private String uuid;

	private int currentTimeVideo;

	@OneToOne(fetch = FetchType.LAZY)
	private Video currentVideo;

	public UserMetadata() {}
	
	/**
	 * @param items
	 * @param currentTimeVideo
	 * @param currentVideo
	 */
	public UserMetadata(User customer, Training training, int currentTimeVideo, Video currentVideo) {
		this.items = new PK_Customer_Training(customer, training);
		this.currentTimeVideo = currentTimeVideo;
		this.currentVideo = currentVideo;
	}
	
}
