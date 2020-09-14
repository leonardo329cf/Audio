package senai.audio.entities.enums;

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
	
	public static SamplingRate getEnumByname(String name) {
		for (SamplingRate sR : SamplingRate.values()) {
			if(name.contains(sR.getName())) {
				return sR;
			}
		}
		return null;
	}
}
