package com.nasa.asteroids.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nasa.asteroids.config.ErrorInfo;
import com.nasa.asteroids.model.dto.AsteroidDTO;
import com.nasa.asteroids.service.IAsteroidService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="Asteriode", description="Información de posibles impactos de asteriodes")
@RequestMapping("/asteroide")
public class AsteroidsController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(AsteroidsController.class);

	// No he podido configurar bien Mapstruct ( creo que es problema de tener también Lombok) y no me genera la implementación del mapper
//	@Autowired
//	private AsteroidMapper asteroidMapper;

	@Autowired
	private IAsteroidService asteroidService;

	@Operation(summary = "Top3 tamaño asteriodes con potencial impacto sobre la tierra en los próximos días indicados ", operationId = "getTop3AsteriodsUsingGET", description = "", tags = "asteroidImpact")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AsteroidDTO.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
			@ApiResponse(responseCode = "503", description = "Servicio no disponible", content = @Content(schema = @Schema(implementation = ErrorInfo.class))) })
	@GetMapping("/")
	public ResponseEntity<List<AsteroidDTO>> getTop3AsteriodsUsingGET(
			@RequestParam(name = "dias", required = true)  int days){

		logger.info("Entramos en el método para obtener los 3 asteriodes de mayor tamaño con potencial impacto sobre la tierra.");

		return new ResponseEntity<>(asteroidService.getTop3AsteroidImpact(days),
				HttpStatus.OK);

	}

}
