package com.nasa.asteroids.model.entity;

import java.util.LinkedHashMap;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * Información específica dentro de la información esencial del json del servicio de la Nasa.
 */

@Getter
@Setter
@ToString
public class NasaCloseApproachData {

	private String close_approach_date;

	private String close_approach_date_full;

	private String epoch_date_close_approach;

	private LinkedHashMap<String, String> relative_velocity;

	private LinkedHashMap<String, String> miss_distance;

	private String orbiting_body;


}
