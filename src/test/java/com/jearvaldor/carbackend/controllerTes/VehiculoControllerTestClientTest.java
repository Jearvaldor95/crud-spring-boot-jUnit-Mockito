package com.jearvaldor.carbackend.controllerTes;

import com.jearvaldor.carbackend.dtos.VehiculoDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.Matchers.hasSize;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehiculoControllerTestClientTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Order(1)
    public void saveVehiculo(){
        VehiculoDto vehiculoDto = new VehiculoDto(1L,"Toyota", "Carolla","Negro", 2023);

        webTestClient.post().uri("http://localhost:8080/api/vehiculo")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(vehiculoDto)
                .exchange()

                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.marca").isEqualTo("Toyota")
                .jsonPath("$.anio").isEqualTo(2023);
    }
    @Test
    @Order(2)
    public void getVehiculo(){
        webTestClient.get().uri("http://localhost:8080/api/vehiculo/1").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.modelo").isEqualTo("Carolla");

    }

    @Test
    @Order(3)
    public void findVehiculo(){
        webTestClient.get().uri("http://localhost:8080/api/vehiculo").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].marca").isEqualTo("Toyota")
                .jsonPath("$[0].modelo").isEqualTo("Carolla")
                .jsonPath("$").isArray()
                .jsonPath("$").value(hasSize(1));
    }

    @Test
    @Order(4)
    public void updateVehiculo(){
        VehiculoDto vehiculoDtoUpdate = new VehiculoDto(1L,"Toyota","Carolla","Blanco", 2024);

        webTestClient.put().uri("http://localhost:8080/api/vehiculo/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(vehiculoDtoUpdate)
                .exchange()

                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Order(5)
    public void deleteVehiculo(){
        webTestClient.get().uri("http://localhost:8080/api/vehiculo").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(VehiculoDto.class)
                .hasSize(1);

        webTestClient.delete().uri("http://localhost:8080/api/vehiculo/1")
                .exchange()
                .expectStatus().isOk();

        webTestClient.get().uri("http://localhost:8080/api/vehiculo").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(VehiculoDto.class)
                .hasSize(0);

        /*webTestClient.get().uri("http://localhost:8080/api/vehiculo/1").exchange()
                .expectStatus().is4xxClientError();*/
    }
}
