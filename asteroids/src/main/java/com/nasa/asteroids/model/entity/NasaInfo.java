package com.nasa.asteroids.model.entity;

import java.util.LinkedHashMap;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * Primera capa del json que devuelve el servicio de la Nasa
 */
@Getter
@Setter
@ToString
public class NasaInfo {

	private LinkedHashMap<String,Object > links;

	private int element_count;

	private LinkedHashMap<String,NasaAsteroidInfo[]> near_earth_objects;


}
