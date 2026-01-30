/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ucacue.reto8.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;    
import lombok.Data;
/**
 *
 * @author Jorge Luis
 */

@Data
@Entity
@Table(name = "reporteVehiculo")
public class ReporteVehiculo implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporteVehiculo;
    
    @ManyToOne
    @JoinColumn(name = "id_conductor")
    @NotNull(message = "Debe seleccionar un conductor")
    private Conductores conductor;

    @NotBlank(message = "La placa es obligatoria")
    @Size(min = 5, max = 10, message = "La placa debe tener entre 5 y 10 caracteres")
    @Pattern(regexp = "^[A-Za-z0-9-]{5,10}$", message = "Formato de placa inválido")
    private String placa;

    @NotBlank(message = "La hora inicial es obligatoria")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "Hora inicial debe tener formato HH:mm")
    private String HoraInicial;

    @NotBlank(message = "El KM inicial es obligatorio")
    @Pattern(regexp = "^\\d{1,9}$", message = "KM inicial debe ser un número entero")
    private String KmInicial;

    @NotBlank(message = "El número de combustible inicial es obligatorio")
    @Pattern(regexp = "^\\d{1,9}$", message = "Número de combustible inicial debe ser numérico")
    private String numCombustibleI;

    @NotBlank(message = "El reconocimiento inicial es obligatorio")
    @Size(min = 3, max = 30, message = "Reconocimiento inicial debe tener entre 3 y 30 caracteres")
    private String reconocimientoI;

    @NotBlank(message = "La hora final es obligatoria")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "Hora final debe tener formato HH:mm")
    private String horaFinal;

    @NotBlank(message = "El número de combustible final es obligatorio")
    @Pattern(regexp = "^\\d{1,9}$", message = "Número de combustible final debe ser numérico")
    private String numCombustibleF;

    @NotBlank(message = "El reconocimiento final es obligatorio")
    @Size(min = 3, max = 30, message = "Reconocimiento final debe tener entre 3 y 30 caracteres")
    private String reconocimientoF;

    @NotBlank(message = "Total KM del día es obligatorio")
    @Pattern(regexp = "^\\d{1,9}$", message = "Total KM del día debe ser numérico")
    private String totalKm_Dia;

    @NotBlank(message = "Observaciones es obligatorio")
    @Size(min = 5, max = 255, message = "Observaciones debe tener entre 5 y 255 caracteres")
    private String observaciones;

    @NotBlank(message = "Reconocimiento del vehículo es obligatorio")
    @Size(min = 3, max = 255, message = "Reconocimiento del vehículo debe tener entre 3 y 255 caracteres")
    private String recoconocimiento_Vehiculo;
}
