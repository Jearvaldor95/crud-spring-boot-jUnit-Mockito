package com.jearvaldor.carbackend.mappers;

import com.jearvaldor.carbackend.dtos.VehiculoDto;
import com.jearvaldor.carbackend.models.Vehiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehiculoMapper {

    @Mappings({
            @Mapping(target = "year", source = "anio")
    })
    Vehiculo toVehiculo(VehiculoDto vehiculoDto);

    @Mappings({
            @Mapping(target = "anio", source = "year")
    })
    VehiculoDto toVehiculoDto(Vehiculo vehiculo);

    List<VehiculoDto> toVehiculoDtos(List<Vehiculo> vehiculos);

    void updateVehiculo(@MappingTarget Vehiculo target, Vehiculo source);
}
