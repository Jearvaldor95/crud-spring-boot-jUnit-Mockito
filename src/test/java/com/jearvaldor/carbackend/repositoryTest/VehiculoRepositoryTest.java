package com.jearvaldor.carbackend.repositoryTest;

import com.jearvaldor.carbackend.models.Vehiculo;
import com.jearvaldor.carbackend.repositories.VehiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class VehiculoRepositoryTest {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    private Vehiculo vehiculo;

    @BeforeEach
    void setup(){
        vehiculo = new Vehiculo(1L, "Toyota", "Carrolla", "Gris", 2023);
    }

    @DisplayName("Test para guardar vehiculo")
    @Test
    public void saveVehiculo(){
        Vehiculo vehiculo1 = vehiculoRepository.save(vehiculo);

        assertThat(vehiculo1).isNotNull();
        assertThat(vehiculo1.getId()).isGreaterThan(0);
    }

    @DisplayName("Test para listar vehiculos")
    @Test
    public void findVehiculo(){
        List<Vehiculo> listVehiculos = vehiculoRepository.findAll();

        assertThat(listVehiculos).isNotNull();
        assertThat(listVehiculos.size());
    }

    @DisplayName("Test para obtener un vehiculo")
    @Test
    public void getVehiculo(){
        vehiculoRepository.save(vehiculo);

        Optional<Vehiculo> vehiculo1 = vehiculoRepository.findById(vehiculo.getId());

        assertThat(vehiculo1).isNotNull();
    }

    @DisplayName("Test para actualizar un vehiculo")
    @Test
    public void updateVehiculo(){
        vehiculoRepository.save(vehiculo);

        Vehiculo vehiculo1 = vehiculoRepository.findById(vehiculo.getId()).get();

        vehiculo1.setColor("Rojo");
        vehiculo1.setYear(2024);

        Vehiculo vehiculo2 = vehiculoRepository.save(vehiculo1);

        assertThat(vehiculo2.getColor()).isEqualTo("Rojo");
        assertEquals(vehiculo2.getYear(),2024);
    }

    @DisplayName("Test para eliminar un vehiculo")
    @Test
    public void deleteVehiculo(){
        Vehiculo vehiculoSave = vehiculoRepository.save(vehiculo);

        vehiculoRepository.deleteById(vehiculoSave.getId());

        Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(vehiculoSave.getId());

        assertThat(vehiculoOptional).isEmpty();
    }
}
