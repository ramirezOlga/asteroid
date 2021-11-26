package com.nasa.asteroids.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nasa.asteroids.exception.Errors;

@Service
public class NasaHelper {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(NasaHelper.class);

	private RestTemplate restTemplate = new RestTemplate();

	/* Key necesaria para obtener la información del servicio de la Nasa */
	private String API_KEY= "zdUP8ElJv1cehFM0rsZVSQN7uBVxlDnu4diHlLSb";

	/**
	 * Gets the nasa information.
	 * Obtenemos un objeto que parseamos en el servicio
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the nasa information
	 */
	public Object getNasaInformation(String startDate, String endDate) {
		Object result = null;

		HttpHeaders headersBloq = new HttpHeaders();
		HttpEntity<?> requestEntityLock = new HttpEntity<>(headersBloq);
		try {
			ResponseEntity<Object> validationStatusResponse = restTemplate.exchange(
					 "https://api.nasa.gov/neo/rest/v1/feed?start_date=" + startDate + "&end_date="+endDate+"&api_key="+API_KEY, HttpMethod.GET, requestEntityLock, Object.class);
			Object body = validationStatusResponse.getBody();
			if (body != null && validationStatusResponse.getStatusCode().equals(HttpStatus.OK)) {
				result = body;
			} else {
				if (logger.isErrorEnabled()) {
					logger.error(Errors.EXCEPTION_NASA_API_001.name(),
							String.format(Errors.EXCEPTION_NASA_API_001.getErrorMessage(), startDate, endDate));
				}
			}
		} catch (Exception e) {
			logger.error(Errors.EXCEPTION_NASA_API_001.name(),
					String.format(Errors.EXCEPTION_NASA_API_001.getErrorMessage(), startDate, endDate));
		}
		return result;
	}

}
