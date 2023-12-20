package com.jearvaldor.carbackend.services;

import com.jearvaldor.carbackend.dtos.VehiculoDto;

import java.util.List;

public interface VehiculoService {

    public List<VehiculoDto> allVehiculo();

    public VehiculoDto createVehiculo(VehiculoDto vehiculoDto);

    public VehiculoDto updateVehiculo(Long id, VehiculoDto vehiculoDto);

    public VehiculoDto patchVehiculo(Long id, VehiculoDto vehiculoDto);

    public VehiculoDto deleteVehiculo(Long id);

    public VehiculoDto getVehiculo(Long id);
}
