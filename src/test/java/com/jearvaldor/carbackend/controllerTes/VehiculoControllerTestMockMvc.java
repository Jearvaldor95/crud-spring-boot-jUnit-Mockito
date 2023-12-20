package com.jearvaldor.carbackend.controllerTes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jearvaldor.carbackend.dtos.VehiculoDto;
import com.jearvaldor.carbackend.services.VehiculoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class VehiculoControllerTestMockMvc {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehiculoService vehiculoService;

    @Autowired
    private ObjectMapper objectMapper;

    private VehiculoDto vehiculoDto;

    @BeforeEach
    public void init(){
        vehiculoDto = new VehiculoDto(1L, "Toyota", "Carrolla", "Blanco", 2023);

    }

    @Test
    public void saveVehiculo() throws Exception{

        when(vehiculoService.createVehiculo(any(VehiculoDto.class))).thenReturn(vehiculoDto);

        ResultActions resultActions = mockMvc.perform(post("/api/vehiculo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(objectMapper.writeValueAsString(vehiculoDto)))
        );
        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.modelo",is(vehiculoDto.getModelo())))
                .andExpect(jsonPath("$.marca",is(vehiculoDto.getMarca())))
                .andExpect(jsonPath("$.color",is(vehiculoDto.getColor())));

    }
    @Test
    public void findVehiculos() throws Exception{
        List<VehiculoDto> vehiculoDtos = new ArrayList<>();
        vehiculoDtos.add(vehiculoDto);

        when(vehiculoService.allVehiculo()).thenReturn(vehiculoDtos);

        ResultActions resultActions = mockMvc.perform(get("/api/vehiculo"));

        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(vehiculoDtos.size())));

    }

    @Test
    public void getVehiculo() throws Exception{
        Long vehiculoId = 1L;
        when(vehiculoService.getVehiculo(vehiculoId)).thenReturn(vehiculoDto);

        ResultActions resultActions = mockMvc.perform(get("/api/vehiculo/{id}",vehiculoId));

        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca",is(vehiculoDto.getMarca())))
                .andExpect(jsonPath("$.modelo",is(vehiculoDto.getModelo())))
                .andExpect(jsonPath("$.color",is(vehiculoDto.getColor())));
    }

    @Test
    public void update() throws Exception{
        VehiculoDto vehiculoDtoActual = new VehiculoDto(1L, "Toyota", "Carrolla", "Blanco", 2023);
        Long vehiculoId = 1L;
        VehiculoDto vehiculoDtoUpdate = new VehiculoDto(1L, "Toyota", "Mazda", "Negro", 2023);
        when(vehiculoService.getVehiculo(vehiculoId)).thenReturn(vehiculoDtoActual);

        when(vehiculoService.updateVehiculo(vehiculoId,vehiculoDtoUpdate)).thenReturn(vehiculoDtoActual);


        ResultActions resultActions = mockMvc.perform(put("/api/vehiculo/{id}",vehiculoId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(objectMapper.writeValueAsString(vehiculoDtoUpdate))));

        resultActions.andDo(print())
                .andExpect(status().isOk());
                /*.andExpect(jsonPath("$.marca").value("Toyota"))
                .andExpect(jsonPath("$.modelo").value("Mazda"))
                .andExpect(jsonPath("$.color").value("Negro"));*/
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void deleteVehiculo() throws Exception{
        Long vehiculoId = 1L;

        when(vehiculoService.getVehiculo(vehiculoId)).thenReturn(vehiculoDto);
        when(vehiculoService.deleteVehiculo(vehiculoId)).thenReturn(vehiculoDto);

        mockMvc.perform(delete("/api/vehiculo/{id}",vehiculoId))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
