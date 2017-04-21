package com.imperialm.imiservices.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class SIRewardsYOYDetailsDTO implements Serializable {
	@Id private String DealerCode;
}
