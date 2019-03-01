package io.hexaforce.webmusic.model;

import lombok.Getter;

public enum BitRate {
	CBR("Constant"), 
	VBR("Variable"), 
	ABR("Average");

	@Getter
	private final String type;

	BitRate(String type) {
		this.type = type;
	}

	public static BitRate typeOf(String type) {
		for (BitRate v : values()) {
			if (v.getType() == type) {
				return v;
			}
		}
		throw new IllegalArgumentException("no such enum object for the id: " + type);
	}

}
