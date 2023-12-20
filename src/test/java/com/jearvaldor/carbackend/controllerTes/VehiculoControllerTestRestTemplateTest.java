package com.jearvaldor.carbackend.controllerTes;

import com.jearvaldor.carbackend.dtos.VehiculoDto;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehiculoControllerTestRestTemplateTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Order(1)
    public void createVehiculo(){
        VehiculoDto vehiculoDto = new VehiculoDto(1L, "Toyota", "Carolla", "Gris", 2024);

        ResponseEntity<VehiculoDto> respuesta = testRestTemplate.postForEntity("http://localhost:8080/api/vehiculo",vehiculoDto,VehiculoDto.class);
        assertEquals(HttpStatus.CREATED,respuesta.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,respuesta.getHeaders().getContentType());

        VehiculoDto vehiculoDtoCreado  =respuesta.getBody();

        assertNotNull(vehiculoDtoCreado);

        assertEquals(1L, vehiculoDtoCreado.getId());
        assertEquals("Toyota", vehiculoDtoCreado.getMarca());
    }

    @Test
    @Order(2)
    public void findVehiculo(){
        ResponseEntity<VehiculoDto[]> respuesta = testRestTemplate.getForEntity("http://localhost:8080/api/vehiculo",VehiculoDto[].class);
        VehiculoDto[] vehiculoDto = respuesta.getBody();
        List<Object> vehiculoDtos = Arrays.asList(vehiculoDto);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,respuesta.getHeaders().getContentType());

        assertNotNull(vehiculoDtos);
        assertEquals(1, vehiculoDtos.size());
        //assertEquals(1L, vehiculoDtos.get(0).getId());
        //assertEquals("Carolla", vehiculoDtos.get(0).getModelo());

    }

    @Test
    @Order(3)
    public void getVehiculo(){
        ResponseEntity<VehiculoDto> respuesta = testRestTemplate.getForEntity("http://localhost:8080/api/vehiculo/1",VehiculoDto.class);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,respuesta.getHeaders().getContentType());

        VehiculoDto vehiculoDto = respuesta.getBody();

        assertNotNull(vehiculoDto);
        assertEquals(1L, vehiculoDto.getId());
        assertEquals(2024, vehiculoDto.getAnio());
        assertEquals("Carolla", vehiculoDto.getModelo());

    }

    @Test
    @Order(4)
    public void updateVehiculo(){
        ResponseEntity<VehiculoDto> vehiculoDtoActual = testRestTemplate.getForEntity("http://localhost:8080/api/vehiculo/1",VehiculoDto.class);

        assertEquals(HttpStatus.OK, vehiculoDtoActual.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,vehiculoDtoActual.getHeaders().getContentType());

        vehiculoDtoActual.getBody().setColor("Negro");
        vehiculoDtoActual.getBody().setAnio(2025);

        ResponseEntity<VehiculoDto> respuesta = testRestTemplate.exchange("http://localhost:8080/api/vehiculo/1",HttpMethod.PUT, vehiculoDtoActual,VehiculoDto.class);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,respuesta.getHeaders().getContentType());

        VehiculoDto vehiculoDtoUpdate = respuesta.getBody();

        assertNotNull(vehiculoDtoUpdate);
        assertEquals(1L, vehiculoDtoUpdate.getId());
        assertEquals("Negro", vehiculoDtoUpdate.getColor());
        assertEquals(2025, vehiculoDtoUpdate.getAnio());

    }

    @Test
    @Order(5)
    public void deleteVehiculo(){
        ResponseEntity<VehiculoDto> vehiculoDto = testRestTemplate.getForEntity("http://localhost:8080/api/vehiculo/1",VehiculoDto.class);

        assertEquals(HttpStatus.OK, vehiculoDto.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, vehiculoDto.getHeaders().getContentType());

        ResponseEntity<VehiculoDto> respuesta = testRestTemplate.exchange("http://localhost:8080/api/vehiculo/1", HttpMethod.DELETE, vehiculoDto, VehiculoDto.class);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertTrue(respuesta.hasBody());

    }
}
