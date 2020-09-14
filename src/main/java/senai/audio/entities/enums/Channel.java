package senai.audio.entities.enums;

import java.util.ArrayList;
import java.util.List;

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
	
	public Channel getByName(String name) {
		for (Channel ch : Channel.values()) {
			if(name.contains(ch.name())) {
				return ch;
			}
		}
		return null;
	}
	
	public List<String> getList() {
		List<String> list = new ArrayList<>();
		for (Channel x : Channel.values()) {
			list.add(x.name + "(" + x.value.toString() + ")");
		}
		return list;
	}
}

