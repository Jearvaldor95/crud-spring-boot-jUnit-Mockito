package com.jearvaldor.carbackend.models;

import javax.persistence.*;

@Entity
@Table(name="vehiculos")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;

    private String modelo;

    private String color;

    @Column(name = "anio")
    private int year;

    public Vehiculo(Long id, String marca, String modelo, String color, int year) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.year = year;
    }

    public Vehiculo(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
