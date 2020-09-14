package senai.audio.entities.enums;

public enum Resolution {

	STANDARD("Padrão", 16);
	
	Resolution(String name, Integer value) {
		this.name = name;
		this.value = value;
	}
	
	private String name;
	private Integer value;
	
	public String getName() {
		return name;
	}
	public Integer getValue() {
		return value;
	}
	
	public static Resolution getEnumByName(String name) {
		for (Resolution res : Resolution.values()) {
			if(name.contains(res.getName())) {
				return res;
			}
		}
		return null;
	}
}
