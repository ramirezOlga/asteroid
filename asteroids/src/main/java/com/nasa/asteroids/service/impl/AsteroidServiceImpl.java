package com.nasa.asteroids.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nasa.asteroids.helper.NasaHelper;
import com.nasa.asteroids.model.dto.AsteroidDTO;
import com.nasa.asteroids.model.entity.NasaAsteroidInfo;
import com.nasa.asteroids.model.entity.NasaInfo;
import com.nasa.asteroids.service.IAsteroidService;

@Service
public class AsteroidServiceImpl implements IAsteroidService {

	/* Se crea una clase específica para la llamada al servicio de la Nasa */
	@Autowired
	private NasaHelper nasaUtil;

	@Override
	public List<AsteroidDTO> getTop3AsteroidImpact(int days) {

		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.DATE, days);

		// Formato de fecha para realizar la llamada al api de la NASA
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// Llamada al servicio de la Nasa
		Object response = nasaUtil.getNasaInformation(dateFormat.format(today), dateFormat.format(calendar.getTime()));

		Gson gson = new GsonBuilder().create();

		// Parseamos el json obtenido en una clase definida
		NasaInfo nasaInfo = gson.fromJson(gson.toJson(response), NasaInfo.class);

		// Obtenemos todos los asteroides de todos los días (definididos por el parámetro days)
		Set<Entry<String, NasaAsteroidInfo[]>> astInfo = nasaInfo.getNear_earth_objects().entrySet();

		List<NasaAsteroidInfo> asteroidsInfo = new ArrayList<>();

		// Pasamos a lista los asteroides
		astInfo.stream().forEach(s -> asteroidsInfo.addAll(Arrays.asList(s.getValue())));

		// Se usan streams para filtrar los que no tienen riesgo potencial de impacto, los ordenamos por su diametro medio, y cogemos los tres primeros
		List<NasaAsteroidInfo> filt = asteroidsInfo.stream().filter(a -> a.getIs_potentially_hazardous_asteroid())
				.sorted(Comparator.comparing(NasaAsteroidInfo::getDiameter)).limit(3).collect(Collectors.toList());


		// estas líneas podríamos sustituirlas por un return asteroidMapper.toDto(filt); si funcionara mapstruct
		// como solución se plantea un constructor de DTO a partir de los datos de la entidad.
		List<AsteroidDTO> dtos =new ArrayList<>();
		filt.stream().forEach(f->dtos.add(new AsteroidDTO(f)));

		return dtos;
	}

}
