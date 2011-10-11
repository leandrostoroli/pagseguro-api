package br.com.tagfy.pagseguro.api.utils;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	public static void toString(HttpPost request) {
			System.out.println("METHOD: "+request.getRequestLine().getMethod());
			System.out.println("PROTOCOL VERSION: "+request.getProtocolVersion());
			System.out.println("URI: "+request.getRequestLine().getUri());
		Header[] headers = request.getAllHeaders();
		for (Header header : headers) {
			System.out.println("HEADER: "+header.getName()+" : "+header.getValue());
		}
		try {
			System.out.println("ENTITY: "+EntityUtils.toString(request.getEntity()));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
