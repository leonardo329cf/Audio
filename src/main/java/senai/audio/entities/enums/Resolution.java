package senai.audio.entities.enums;

import java.util.ArrayList;
import java.util.List;

public enum Resolution {

	STANDARD("Padrão", 24);
	
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
	
	public Resolution getResolutionByName(String name) {
		for (Resolution res : Resolution.values()) {
			if(name.contains(res.getName())) {
				return res;
			}
		}
		return null;
	}
	
	public List<String> getList() {
		List<String> list = new ArrayList<>();
		for (Resolution x : Resolution.values()) {
			list.add(x.name + "(" + x.value.toString() + ")");
		}
		return list;
	}
}
