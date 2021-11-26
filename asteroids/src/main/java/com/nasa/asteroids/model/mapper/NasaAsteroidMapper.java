package com.nasa.asteroids.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nasa.asteroids.model.dto.AsteroidDTO;
import com.nasa.asteroids.model.entity.NasaAsteroidInfo;

@Mapper
public interface NasaAsteroidMapper {

	// TODO: Dejo la implementación de mapper como sería para mapear la entidad al DTO en lugar de usar un constructor

	@Mapping(target="isPotentiallyHazardousAsteroid", source="is_potentially_hazardous_asteroid")
	List<AsteroidDTO> toDto(List<NasaAsteroidInfo> entities);

	@Mapping(target="isPotentiallyHazardousAsteroid", source="is_potentially_hazardous_asteroid")
	AsteroidDTO toDto(NasaAsteroidInfo entity);


}
