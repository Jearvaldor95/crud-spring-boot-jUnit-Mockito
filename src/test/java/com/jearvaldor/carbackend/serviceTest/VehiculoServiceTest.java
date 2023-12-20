package com.jearvaldor.carbackend.serviceTest;

import com.jearvaldor.carbackend.dtos.VehiculoDto;
import com.jearvaldor.carbackend.exceptions.AppException;
import com.jearvaldor.carbackend.mappers.VehiculoMapper;
import com.jearvaldor.carbackend.models.Vehiculo;
import com.jearvaldor.carbackend.repositories.VehiculoRepository;
import com.jearvaldor.carbackend.services.VehiculoServiceImple;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Prueba para vehiculo service")
public class VehiculoServiceTest {

    @InjectMocks
    private VehiculoServiceImple vehiculoService;

    @Mock
    private VehiculoRepository vehiculoRepository;

    @Mock
    private VehiculoMapper vehiculoMapper;

    private VehiculoDto vehiculoDto;

    private Vehiculo vehiculo;

    @Mock
    private Logger log;

    Long miId = 1L;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        vehiculo = new Vehiculo();
        vehiculo.setId(miId);
        vehiculo.setModelo("Ford");
        vehiculo.setYear(2023);
        vehiculo.setMarca("Toyota");
        vehiculo.setColor("Gris");

        //vehiculoDto = vehiculoMapper.toVehiculoDto(vehiculo);

        vehiculoDto = new VehiculoDto();
        vehiculoDto.setId(miId);
        vehiculoDto.setModelo("Ford");
        vehiculoDto.setAnio(2023);
        vehiculoDto.setMarca("Toyota");
        vehiculoDto.setColor("Gris");
    }

    @DisplayName("Test para listar todo los vehiculos")
    @Test
    public void listarVehiculo() {
        // Simula el comportamiento de vehiculoRepository.findAll()
        when(vehiculoRepository.findAll()).thenReturn(Arrays.asList(vehiculo));

        // Define el comportamiento simulado para vehiculoMapper.toVehiculoDtos()
        when(vehiculoMapper.toVehiculoDtos(anyList())).thenReturn(Arrays.asList(vehiculoDto));

        // Ejecuta el método que quieres probar
        List<VehiculoDto> result = vehiculoService.allVehiculo();

        assertNotNull(vehiculoService.allVehiculo());
        assertTrue(result.size() > 0);

        assertEquals(result.get(0).getColor(),"Gris");

    }

    @DisplayName("Test para obtener un vehiculo")
    @Test
    public void getVehiculo() {

        // Simula el comportamiento de vehiculoRepository.findById()
        when(vehiculoRepository.findById(anyLong())).thenReturn(Optional.of(vehiculo));

        // Simula el comportamiento de vehiculoMapper.toVehiculoDto()
        when(vehiculoMapper.toVehiculoDto(vehiculo)).thenReturn(vehiculoDto);

        // Ejecuta el método que quieres probar, en este caso, getVehiculo
        VehiculoDto result = vehiculoService.getVehiculo(1L); // Puedes pasar cualquier ID

        // Verifica si el método findById del repositorio fue invocado con el ID esperado
        verify(vehiculoRepository, times(1)).findById(1L); // Asegúrate de usar el ID correcto

        // Aserciones para verificar si el resultado es el esperado
        assertEquals(vehiculoDto, result);
        assertNotNull(result);
        assertEquals(vehiculoDto.getColor(),"Gris");
        assertEquals(vehiculoDto.getMarca(),"Toyota");
        assertEquals(vehiculoDto.getModelo(),"Ford");
        assertEquals(miId, vehiculoDto.getId());
        assertTrue(vehiculoDto.getAnio()==2023);
        assertTrue(vehiculoDto.getId()== 1L);
    }

    @DisplayName("Test para guradar un vehiculo")
    @Test
    public void save() {
        // Define el comportamiento simulado para vehiculoMapper.toVehiculo()
        when(vehiculoMapper.toVehiculo(vehiculoDto)).thenReturn(vehiculo);

        // Define el comportamiento simulado para vehiculoRepository.save()
        when(vehiculoRepository.save(any(Vehiculo.class))).thenReturn(vehiculo);

        // Define el comportamiento simulado para vehiculoMapper.toVehiculoDto()
        when(vehiculoMapper.toVehiculoDto(vehiculo)).thenReturn(vehiculoDto);

        // Ejecuta el método que quieres probar
        VehiculoDto result = vehiculoService.createVehiculo(vehiculoDto);

        // Verifica que el método save del repositorio haya sido invocado una vez
        verify(vehiculoRepository, times(1)).save(any(Vehiculo.class));

        //Assert.assertThat(result,notNull());

        assertNotNull(result);
    }

    @DisplayName("Test para actualizar un vehiculo")
    @Test
    public void update() {
        Long vehiculoId = 1L;
        VehiculoDto updatedVehiculo = new VehiculoDto(miId, "Toyota", "Ford", "Negra", 2024);

        when(vehiculoRepository.findById(vehiculoId)).thenReturn(Optional.of(vehiculo));
        when(vehiculoRepository.save(any(Vehiculo.class))).thenReturn(vehiculo);
        when(vehiculoMapper.toVehiculoDto(vehiculo)).thenReturn(updatedVehiculo);

        // Ejecutar el método bajo prueba
        VehiculoDto result = vehiculoService.updateVehiculo(vehiculoId, updatedVehiculo);

        // Verificar interacciones
        verify(vehiculoRepository).findById(vehiculoId);
        verify(vehiculoMapper).toVehiculo(updatedVehiculo);

        verify(vehiculoRepository).save(vehiculo);

        assertNotNull(result);
        assertEquals(updatedVehiculo.getColor(),"Negra");
        assertEquals(updatedVehiculo.getAnio(),2024);
    }

    @DisplayName("Test para eliminar un vehiculo")
    @Test
    public void delete() {
        Long vehiculoId = 1L;

        // Simula el comportamiento de vehiculoRepository.findById()
        when(vehiculoRepository.findById(anyLong())).thenReturn(Optional.of(vehiculo));

        // Ejecuta el método que quieres probar, en este caso, deleteVehiculo
        VehiculoDto result = vehiculoService.deleteVehiculo(vehiculoId);

        // Ejecuta el método que quieres probar, en este caso, deleteVehiculo
        verify(vehiculoRepository, times(1)).findById(vehiculoId);

        // Verifica si el método deleteById del repositorio fue invocado para eliminar el vehículo
        verify(vehiculoRepository, times(1)).deleteById(vehiculoId);

    }

    @DisplayName("Test para simular una exception")
    @Test
    void vehiculoNotFound() {

        // aqui simularemos que queremos que el método devuelva una excepción
        when(vehiculoRepository.findById(0L)).thenThrow(new AppException("Vehiculo not found", HttpStatus.NOT_FOUND));

        Throwable exception = assertThrows(AppException.class, () ->
                vehiculoService.getVehiculo(0L));

        assertEquals("Vehiculo not found", exception.getMessage());
    }
}
