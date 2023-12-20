package com.jearvaldor.carbackend.mapperTest;

import com.jearvaldor.carbackend.dtos.VehiculoDto;
import com.jearvaldor.carbackend.mappers.VehiculoMapper;
import com.jearvaldor.carbackend.mappers.VehiculoMapperImpl;
import com.jearvaldor.carbackend.models.Vehiculo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Prueba de vehiculo mapper")
public class VehiculoMapperTest {

    @Autowired
    private VehiculoMapper vehiculoMapper = new VehiculoMapperImpl();

    @Test
    public void vehiculoToVehiculoDto(){
        Vehiculo vehiculo = new Vehiculo(1L,"Toyota", "Carolla", "Negro", 2023);


        VehiculoDto vehiculoDto=vehiculoMapper.toVehiculoDto(vehiculo);

        assertNotNull(vehiculoDto);
        assertTrue(vehiculoDto.getAnio()==2023);
        assertEquals(vehiculo.getColor(), vehiculoDto.getColor());

    }

    @Test
    public void vehiculoDtoToVehiculo(){
        VehiculoDto vehiculoDto = new VehiculoDto();
        vehiculoDto.setId(1L);
        vehiculoDto.setMarca("Toyota");
        vehiculoDto.setModelo("Carolla");
        vehiculoDto.setColor("Negro");
        vehiculoDto.setAnio(2023);

        Vehiculo vehiculo=vehiculoMapper.toVehiculo(vehiculoDto);

        assertNotNull(vehiculo);
        assertTrue(vehiculo.getYear()==2023);
        assertEquals(vehiculo.getColor(), vehiculoDto.getColor());
    }

    @Test
    public void vehiculosToVehiculoDtos(){
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(1L);
        vehiculo.setMarca("Toyota");
        vehiculo.setModelo("Carolla");
        vehiculo.setColor("Negro");
        vehiculo.setYear(2023);

        List<Vehiculo> vehiculos = Collections.singletonList(vehiculo);

        List<VehiculoDto> vehiculoDtos = vehiculoMapper.toVehiculoDtos(vehiculos);

        assertNotNull(vehiculoDtos);
        assertEquals(1,vehiculoDtos.size());
        assertEquals(vehiculo.getColor(),vehiculoDtos.get(0).getColor());
        assertEquals(vehiculo.getYear(),vehiculoDtos.get(0).getAnio());
    }

}
