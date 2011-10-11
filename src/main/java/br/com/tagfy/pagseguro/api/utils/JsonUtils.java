package br.com.tagfy.pagseguro.api.utils;

import com.google.gson.Gson;

public class JsonUtils {
	
	public static <T> String toJson(T object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}
	
	public static <T> T fromJson(String json, Class<T> cls) {
		Gson gson = new Gson();
		return gson.fromJson(json, cls);
	}

}
