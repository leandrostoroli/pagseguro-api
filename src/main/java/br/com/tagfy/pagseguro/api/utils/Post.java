package br.com.tagfy.pagseguro.api.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Post {
	
	private HttpClient client;
	
	private HttpPost request;
	
	private String encoding;
	
	private List<NameValuePair> formParams;
	
	private HttpResponse response;
	
	private String accept;
	
	public Post(String uri) {
		client = new DefaultHttpClient();
		request = new HttpPost(uri);
		encoding = "UTF-8";
		formParams = new ArrayList<NameValuePair>();
	}
	
	public static Post at(String uri) {
		return new Post(uri);
	}
	
	public Post contentType(String contentType) {
		request.addHeader("Content-type", contentType);
		return this;
	}
	
	public Post encoding(String encoding) {
		this.encoding = encoding;
		return this;
	}
	
	public Post accept(String accept) {
		this.accept = accept;
		return this;
	}
	
	public Post param(String name, String value) {
		formParams.add(new BasicNameValuePair(name, value));
		return this;
	}
	
	public <T> Post param(T object) {
		if (request.getFirstHeader("Content-type").getValue().contains("application/xml")) {
			xmlParam(object);
		} else if (request.getFirstHeader("Content-type").getValue().contains("application/json")) {
			jsonParam(object);
		}
		return this;
	}
	
	public Post post() throws Exception {
		try {
			if (formParams.size() > 0) {
				formParam();
			}
			response = client.execute(request);
		} catch (ClientProtocolException e) {
			throw new Exception(e);
		} catch (IOException e) {
			throw new Exception(e);
		}
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T resource(Class<T> cls) throws Exception {
		if (accept.contains("application/xml")) {
			return xmlResponse(cls);
		} else if (accept.contains("application/json")) {
			return jsonResponse(cls);
		}
		return (T) EntityUtils.toString(response.getEntity());
	}
	
	private <T> void xmlParam(T object) {
		try {
			StringEntity xmlEntity = new StringEntity(XmlUtils.toXml(object, encoding), encoding);
			request.setEntity(xmlEntity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private <T> void jsonParam(T object) {
		try {
			StringEntity jsonEntity = new StringEntity(JsonUtils.toJson(object), encoding);
			request.setEntity(jsonEntity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private void formParam() {
		try {
			request.setEntity(new UrlEncodedFormEntity(formParams, encoding));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private <T> T xmlResponse(Class<T> cls) throws Exception {
		try {
			return XmlUtils.fromXml(EntityUtils.toString(response.getEntity()), cls);
		} catch (ParseException e) {
			throw new Exception(e);
		} catch (IOException e) {
			throw new Exception(e);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	private <T> T jsonResponse(Class<T> cls) throws Exception {
		try {
			return JsonUtils.fromJson(EntityUtils.toString(response.getEntity()), cls);
		} catch (ParseException e) {
			throw new Exception(e);
		} catch (IOException e) {
			throw new Exception(e);
		}
	}
	
}
