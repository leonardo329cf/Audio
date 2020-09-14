package senai.audio.entities.enums;

import java.util.ArrayList;
import java.util.List;

public enum SamplingRate {
	
	STANDARD("Padrão", 44100);

	SamplingRate(String name, Integer value) {
		this.value = value;
		this.name = name;
	}

	private String name;
	private Integer value;
	
	public String getName() {
		return name;
	}
	public Integer getValue() {
		return value;
	}
	
	public SamplingRate getEnumByname(String name) {
		for (SamplingRate sR : SamplingRate.values()) {
			if(name.contains(sR.getName())) {
				return sR;
			}
		}
		return null;
	}
}
