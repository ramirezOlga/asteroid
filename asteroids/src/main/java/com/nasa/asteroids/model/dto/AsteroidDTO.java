package com.nasa.asteroids.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nasa.asteroids.model.entity.NasaAsteroidInfo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * Objeto que usamos para devolver la información del impacto de asteroides.
 * Se incluyen las anotaciones de swagger para una mayor compresión del API.
 */


@Getter
@Setter
@ToString
@Schema(name = "top3Asteroide", description = "Objeto con los datos de los 3 asteroides más grandes con potencial riesgo de impacto en la tierra entre hoy y la cantidad de días indicado.")
public class AsteroidDTO {


	@JsonProperty(value = "nombre")
	@Schema(name = "nombre", description = "Nombre del asteroide ")
	private String name;

	@JsonProperty(value = "diametro")
	@Schema(name = "diametro", description = "Tamaño medio calculado del asteroide ")
	private Double diameter;

	@JsonProperty(value = "velocidad")
	@Schema(name = "velocidad", description = "Velocidad en km/h del asteroide ")
	private String velocity;

	@JsonProperty(value = "fecha")
	@Schema(name = "fecha", description = "Fecha de impacto del asteroide ")
	private String date;

	@JsonProperty(value = "planeta")
	@Schema(name = "planeta", description = "Planeta donde impacta del asteroide ")
	private String planet;


	// Se crea constuctor para poder pasar la información de la entidad al DTO, aunque preferiblemente se usaría con Mapstruct (pueden ver la interfaz del mapper (AsteroidMapper))
	public AsteroidDTO(NasaAsteroidInfo info) {
		super();
		this.name = info.getName();
		this.diameter = info.getDiameter();
		this.velocity = info.getVelocityHour();
		this.date = info.getDate();
		this.planet = info.getPlanet();
	}



}
