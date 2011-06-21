package controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class Application extends ActionSupport implements Preparable, SessionAware, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6412470008174702605L;
	
	protected InputStream inputStream;
	protected Map<String, Object> session;
	protected HttpServletRequest request;
	
	protected void setInputStream(String str) {
		byte[] strInBytes = str.getBytes();
		inputStream = new ByteArrayInputStream(strInBytes);
	}

	public InputStream getInputStream() {
		return inputStream;
	}	

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Override
	public String execute() {
		return SUCCESS;
	}

}
