package com.nasa.asteroids.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorInfo {

	@JsonProperty
	private String message;

	@JsonProperty
	private List<String> listaErrores;

	@JsonProperty("timestamp")
	private Date timestamp;

	@JsonProperty("uri")
	private String uriRequested;

	public ErrorInfo(Exception exception, String uriRequested) {
		this.message = extractDetailedMessage(exception);
		this.uriRequested = uriRequested;
		this.timestamp = new Date();
	}

	public ErrorInfo(String message, String uriRequested) {
		this.message = message;
		this.uriRequested = uriRequested;
		this.timestamp = new Date();
	}

	public String getMessage() {
		return message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getUriRequested() {
		return uriRequested;
	}

	public List<String> getListaErrores() {
		if (listaErrores == null) {
			listaErrores = new ArrayList<>();
		}
		return listaErrores;
	}

	private String extractDetailedMessage(Throwable e) {
		final String message = e.getMessage();
		if (message == null) {
			return "";
		}
		final int tailIndex = StringUtils.indexOf(message, "; nested exception is");
		if (tailIndex == -1) {
			return message;
		}
		return StringUtils.left(message, tailIndex);
	}
}