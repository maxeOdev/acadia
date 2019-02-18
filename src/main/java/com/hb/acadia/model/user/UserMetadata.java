package com.hb.acadia.model.user;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.hb.acadia.model.Video;

@Entity
@AssociationOverrides({
		@AssociationOverride(name = "items.user", joinColumns = @JoinColumn(name = "idUser")),
		@AssociationOverride(name = "items.training", joinColumns = @JoinColumn(name = "idTraining"))})
public class UserMetadata {

	@EmbeddedId
	private PK_Customer_Training items;

	@Column(nullable=false, unique=true)
	private String uuid;
	
	private int currentTime;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Video currentVideo;
	
	public UserMetadata() {}

	public UserMetadata(PK_Customer_Training items, String uuid,
			int currentTime, Video currentVideo) {
		this.items = items;
		this.uuid = uuid;
		this.currentTime = currentTime;
		this.currentVideo = currentVideo;
	}

	public PK_Customer_Training getItems() {
		return items;
	}

	public void setItems(PK_Customer_Training items) {
		this.items = items;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	public Video getCurrentVideo() {
		return currentVideo;
	}

	public void setCurrentVideo(Video currentVideo) {
		this.currentVideo = currentVideo;
	}

	@Override
	public String toString() {
		return "UserMetadata [items=" + items + ", uuid=" + uuid
				+ ", currentTime=" + currentTime + ", currentVideo="
				+ currentVideo + "]";
	}
	
}
