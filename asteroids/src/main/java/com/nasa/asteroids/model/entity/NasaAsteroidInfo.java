package com.nasa.asteroids.model.entity;

import java.util.LinkedHashMap;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * Información esencial del servicio de la Nasa.
 */

@Getter
@Setter
@ToString
public class NasaAsteroidInfo {

	private LinkedHashMap<String, Object> links;

	private String id;

	// TODO: los nombres de los campos debían estar iguales que en el json para
	// poder parsearlo, supongo que existe algun tipo de anotación como en Swagger
	// donde se indica un nombre que es el que luego tiene el json, y que es
	// diferente al nombre del campo.
	// De esta forma no cumple con las reglas de nomenclatura, este debería ser: neoReferenceId

	private String neo_reference_id;

	private String name;

	private String nasa_jpl_url;

	private String absolute_magnitude_h;

	private LinkedHashMap<String, LinkedHashMap<String, Double>> estimated_diameter;

	private Boolean is_potentially_hazardous_asteroid;

	private NasaCloseApproachData[] close_approach_data;

	private Boolean is_sentry_object;

	// Se crean diferentes getters para obtener cierta información específica

	/*
	 * Obtenemos la media del diametro del asteroide
	 */
	public Double getDiameter() {
		return (this.getEstimated_diameter().get("kilometers").get("estimated_diameter_max")
				- this.getEstimated_diameter().get("kilometers").get("estimated_diameter_min")) / 2;
	}

	public String getVelocityHour() {
		return this.getClose_approach_data()[0].getRelative_velocity().get("kilometers_per_hour");
	}

	public String getDate() {
		return this.getClose_approach_data()[0].getClose_approach_date();
	}

	public String getPlanet() {
		return this.getClose_approach_data()[0].getOrbiting_body();
	}

}
