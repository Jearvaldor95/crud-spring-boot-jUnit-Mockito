package com.jearvaldor.carbackend.controllers;

import com.jearvaldor.carbackend.dtos.VehiculoDto;
import com.jearvaldor.carbackend.exceptions.AppException;
import com.jearvaldor.carbackend.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/vehiculo")
    public ResponseEntity<List<VehiculoDto>> allVehiculos(){
        return ResponseEntity.ok(vehiculoService.allVehiculo());
    }

    @PostMapping("/vehiculo")
    public  ResponseEntity<VehiculoDto> create(@Valid @RequestBody VehiculoDto vehiculoDto){
        //VehiculoDto createVehiculo = vehiculoService.createVehiculo(vehiculoDto);
        //return  ResponseEntity.created(URI.create("/vehiculo/" + vehiculoDto.getId())).body(createVehiculo);
        return new  ResponseEntity<VehiculoDto>(vehiculoService.createVehiculo(vehiculoDto),HttpStatus.CREATED);
    }

    @GetMapping("/vehiculo/{id}")
    public ResponseEntity<VehiculoDto> getVehiculo(@PathVariable Long id){
            return ResponseEntity.ok(vehiculoService.getVehiculo(id));
    }

    @PutMapping("/vehiculo/{id}")
    public ResponseEntity<VehiculoDto> update(@PathVariable Long id, @Valid @RequestBody VehiculoDto vehiculoDto){
        return ResponseEntity.ok(vehiculoService.updateVehiculo(id, vehiculoDto));
    }

    @PatchMapping("/vehiculo/{id}")
    public ResponseEntity<VehiculoDto> patch(@PathVariable Long id, @RequestBody VehiculoDto vehiculoDto){
        return ResponseEntity.ok(vehiculoService.patchVehiculo(id, vehiculoDto));
    }

    @DeleteMapping("/vehiculo/{id}")
    public ResponseEntity<VehiculoDto> delete(@PathVariable Long id){
        return ResponseEntity.ok(vehiculoService.deleteVehiculo(id));
    }
}
