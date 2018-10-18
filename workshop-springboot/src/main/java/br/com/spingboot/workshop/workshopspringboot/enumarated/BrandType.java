package br.com.spingboot.workshop.workshopspringboot.enumarated;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BrandType {
	@JsonProperty("fiat")
	FIAT,
	
	@JsonProperty("ford")
	FORD,
	
	@JsonProperty("chevrolet")
	CHEVROLET;


}
