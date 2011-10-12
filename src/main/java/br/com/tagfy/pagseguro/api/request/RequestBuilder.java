package br.com.tagfy.pagseguro.api.request;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import br.com.tagfy.pagseguro.api.utils.Get;
import br.com.tagfy.pagseguro.api.utils.Post;

public class RequestBuilder {
	
	private String encoding;
	
	private List<NameValuePair> params;
	
	private String contentType;
	
	private String accept;
	
	private String uri;
	
	private Object param;
	
	public RequestBuilder(String uri) {
		this.uri = uri;
		this.encoding = "UTF-8";
		this.params = new ArrayList<NameValuePair>();
	}
	
	public static RequestBuilder at(String uri) {
		return new RequestBuilder(uri);
	}
	
	public RequestBuilder contentType(String contentType) {
		this.contentType = contentType;
		return this;
	}
	
	public RequestBuilder encoding(String encoding) {
		this.encoding = encoding;
		return this;
	}
	
	public RequestBuilder accept(String accept) {
		this.accept = accept;
		return this;
	}
	
	public RequestBuilder param(String name, String value) {
		params.add(new BasicNameValuePair(name, value));
		return this;
	}
	
	public <T> RequestBuilder param(T param) {
		this.param = param;
		return this;
	}
	
	public Post buildPost() {
		Post post = Post.at(uri)
			.encoding(encoding);
		if (contentType != null && !contentType.isEmpty()) {
			post.contentType(contentType);
		}
		if (accept != null && !accept.isEmpty()) {
			post.accept(accept);
		}
		if (params.size() > 0) {
			for (NameValuePair param : params) {
				post.param(param.getName(), param.getValue());
			}
		} else {
			post.param(param);
		}
		return post;
	}
	
	public Get buildGet() {
		Get get = Get.at(uri)
			.encoding(encoding);
		if (accept != null && !accept.isEmpty()) {
			get.accept(accept);
		}
		if (params.size() > 0) {
			for (NameValuePair param : params) {
				get.param(param.getName(), param.getValue());
			}
		}
		return get;
	}
	
	
}
