package com.hb.acadia.model.user;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

import com.hb.acadia.model.Video;
import com.hb.acadia.repository.BillRepository;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AssociationOverrides({
		@AssociationOverride(name = "items.user", joinColumns = @JoinColumn(name = "idUser")),
		@AssociationOverride(name = "items.training", joinColumns = @JoinColumn(name = "idTraining"))})
public class UserMetadata {

	@EmbeddedId
	private PK_Customer_Training items;

	@Column(nullable = false, unique = true)
	private String uuid;

	private int currentTime;

	@OneToOne(fetch = FetchType.LAZY)
	private Video currentVideo;

	@Override
	public String toString() {
		return "UserMetadata [items=" + items + ", uuid=" + uuid + ", currentTime=" + currentTime + ", currentVideo="
				+ currentVideo + "]";
	}



}
