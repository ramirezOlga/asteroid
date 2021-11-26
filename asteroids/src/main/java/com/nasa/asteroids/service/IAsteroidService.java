package com.nasa.asteroids.service;

import java.util.List;

import com.nasa.asteroids.model.dto.AsteroidDTO;

public interface IAsteroidService {

	List<AsteroidDTO> getTop3AsteroidImpact(int days) ;

}
