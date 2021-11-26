package com.nasa.asteroids.exception;

public enum Errors {

	EXCEPTION_NASA_API_001("Ha habido un error en la llamada al API de la NASA entre las fechas %s y %s");

	/** The mensaje error. */
	private String errorMessage;

	/**
	 * Instantiates a new errores.
	 *
	 * @param errorMessage the mensaje error
	 */
	private Errors(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets the mensaje error.
	 *
	 * @return the mensaje error
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
}
