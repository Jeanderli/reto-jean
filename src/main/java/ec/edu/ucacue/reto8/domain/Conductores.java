/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ucacue.reto8.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="Conductores")
public class Conductores implements Serializable{
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long idConductores;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 60, message = "El nombre debe tener entre 3 y 60 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre solo puede contener letras y espacios")
    private String Nombre;

    @NotBlank(message = "El reconocimiento es obligatorio")
    @Size(min = 3, max = 30, message = "El reconocimiento debe tener entre 3 y 30 caracteres")
    private String reconocimiento;

    @NotBlank(message = "El KM inicial es obligatorio")
    @Pattern(regexp = "^\\d{1,9}$", message = "El KM inicial debe ser un número entero")
    private String kminicial;

    @NotBlank(message = "El número de combustible es obligatorio")
    @Pattern(regexp = "^\\d{1,9}$", message = "El número de combustible debe ser numérico")
    private String numCombustible;

    @NotBlank(message = "El día es obligatorio")
    @Size(min = 3, max = 20, message = "El día debe tener entre 3 y 20 caracteres")
    private String dia;

    @NotBlank(message = "La hora de ingreso es obligatoria")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "La hora de ingreso debe tener formato HH:mm")
    private String horaIngreso;

    @NotBlank(message = "La hora de salida es obligatoria")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "La hora de salida debe tener formato HH:mm")
    private String horaSalida;

    @NotBlank(message = "El valor de adelantos es obligatorio")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Adelantos debe ser un número válido (ej: 10 o 10.50)")
    private String Adelantos;

    @NotBlank(message = "Las novedades son obligatorias")
    @Size(min = 5, max = 255, message = "Novedades debe tener entre 5 y 255 caracteres")
    private String novedades;
}
