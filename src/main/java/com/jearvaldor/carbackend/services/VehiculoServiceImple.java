package com.jearvaldor.carbackend.services;

import com.jearvaldor.carbackend.dtos.VehiculoDto;
import com.jearvaldor.carbackend.exceptions.AppException;
import com.jearvaldor.carbackend.mappers.VehiculoMapper;
import com.jearvaldor.carbackend.models.Vehiculo;
import com.jearvaldor.carbackend.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VehiculoServiceImple implements VehiculoService{

    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private VehiculoMapper vehiculoMapper;

    @Override
    public List<VehiculoDto> allVehiculo() {
        return vehiculoMapper.toVehiculoDtos(vehiculoRepository.findAll());
    }

    @Override
    public VehiculoDto createVehiculo(VehiculoDto vehiculoDto) {
        Vehiculo vehiculo= vehiculoMapper.toVehiculo(vehiculoDto);
        return vehiculoMapper.toVehiculoDto(vehiculoRepository.save(vehiculo));
    }

    @Override
    public VehiculoDto updateVehiculo(Long id, VehiculoDto vehiculoDto) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new AppException("Vehiculo not found", HttpStatus.NOT_FOUND));

        vehiculoMapper.updateVehiculo(vehiculo, vehiculoMapper.toVehiculo(vehiculoDto));

        return vehiculoMapper.toVehiculoDto(vehiculoRepository.save(vehiculo));
    }

    @Override
    public VehiculoDto patchVehiculo(Long id, VehiculoDto vehiculoDto) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new AppException("Vehiculo not found", HttpStatus.NOT_FOUND));

        if(vehiculoDto.getMarca() != null){
            vehiculo.setMarca(vehiculoDto.getMarca());
        }
        if(vehiculoDto.getModelo() != null){
            vehiculo.setModelo(vehiculoDto.getModelo());
        }
        if(vehiculoDto.getColor() != null){
            vehiculo.setColor(vehiculoDto.getColor());
        }
        if(vehiculoDto.getAnio() != 0){
            vehiculo.setYear(vehiculoDto.getAnio());
        }
        return vehiculoMapper.toVehiculoDto(vehiculoRepository.save(vehiculo));
    }

    @Override
    public VehiculoDto deleteVehiculo(Long id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new AppException("Vehiculo not found", HttpStatus.NOT_FOUND));

        VehiculoDto vehiculoDto = vehiculoMapper.toVehiculoDto(vehiculo);
        vehiculoRepository.deleteById(id);
        return vehiculoDto;
    }

    @Override
    public VehiculoDto getVehiculo(Long id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new AppException("Vehiculo not found", HttpStatus.NOT_FOUND));
        return vehiculoMapper.toVehiculoDto(vehiculo);
    }
}
