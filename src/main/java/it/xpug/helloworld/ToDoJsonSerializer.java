package it.xpug.helloworld;

import com.google.gson.Gson;

public class ToDoJsonSerializer {

	private String json;

	public ToDoJsonSerializer(String json) {
		this.json = json;
	}

	public ToDo deserialize() {
		return new Gson().fromJson(json, ToDo.class);
	}
}
