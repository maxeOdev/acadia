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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentTimeVideo;
		result = prime * result + ((currentVideo == null) ? 0 : currentVideo.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		UserMetadata other = (UserMetadata) obj;
		if (currentTimeVideo != other.currentTimeVideo)
			return false;
		if (currentVideo == null) {
			if (other.currentVideo != null)
				return false;
		} else if (!currentVideo.equals(other.currentVideo))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
}
