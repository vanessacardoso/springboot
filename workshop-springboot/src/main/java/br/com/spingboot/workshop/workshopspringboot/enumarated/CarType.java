package br.com.spingboot.workshop.workshopspringboot.enumarated;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CarType {
	@JsonProperty("hatch")
	HATCH,
	
	@JsonProperty("seda")
	SEDA;



}
