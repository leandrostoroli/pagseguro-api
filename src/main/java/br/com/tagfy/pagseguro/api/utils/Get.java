package br.com.tagfy.pagseguro.api.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Get {
	
	private HttpClient client; 

	private HttpGet request;
	
	private String encoding;
	
	private HttpResponse response;
	
	private List<NameValuePair> queryStringParams;

	public Get(String uri) {
		client = new DefaultHttpClient();
		request = new HttpGet(uri);
		encoding = "UTF-8";
		queryStringParams = new ArrayList<NameValuePair>();
	}

	public static Get at(String uri) {
		return new Get(uri);
	}
	
	public Get encoding(String encoding) {
		this.encoding = encoding;
		return this;
	}
	
	public Get accept(String accept) {
		request.addHeader("Accept", accept);
		return this;
	}
	
	public Get param(String name, String value) {
		queryStringParams.add(new BasicNameValuePair(name, value));
		return this;
	}
	
	public Get get() throws Exception {
		try {
			if (queryStringParams.size() > 0) {
				queryStringParam();
			}
			request.addHeader("Content-type", "Content-Type: application/x-www-form-urlencoded; charset="+encoding);
			System.out.println(request.getURI());
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
		Header accept = response.getFirstHeader("Content-type");
		if (accept != null) {
			if (accept.getValue().contains("application/xml")) {
				return xmlResponse(cls);
			} else if (accept.getValue().contains("application/json")) {
				return jsonResponse(cls);
			}
		}
		return (T) EntityUtils.toString(response.getEntity());
	}
	
	public int getCode() {
		return response.getStatusLine().getStatusCode();
	}
	
	public String getReason() {
		return response.getStatusLine().getReasonPhrase();
	}
	
	@SuppressWarnings("static-access")
	private void queryStringParam() throws Exception {
		try {
			String params = new URLEncodedUtils().format(queryStringParams, encoding);
			request.setURI(new URI(request.getURI()+"?"+params));
		} catch (URISyntaxException e) {
			throw new Exception(e);
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
