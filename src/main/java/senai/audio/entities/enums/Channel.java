package senai.audio.entities.enums;


public enum Channel {

	MONO("Mono", 1),
	STEREO("Estéreo", 2);
	
	private Channel(String name, Integer value) {
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
	
	public static Channel getEnumByName(String name) {
		for (Channel ch : Channel.values()) {
			if(name.contains(ch.getValue().toString())) {
				return ch;
			}
		}
		return null;
	}
}

