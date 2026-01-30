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
 * @author jeand
 */
@Data
@Entity
@Table (name = "Informacion")
public class Informacion implements Serializable{
   private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInformacion;
    
    @ManyToOne
    @JoinColumn(name = "id_conductor")
    @NotNull(message = "Debe seleccionar un conductor")
    private Conductores conductor;
    
    @NotBlank(message = "La placa es obligatoria")
    @Size(min = 5, max = 10, message = "La placa debe tener entre 5 y 10 caracteres")
    @Pattern(regexp = "^[A-Za-z0-9-]{5,10}$", message = "Formato de placa inválido")
    private String placa;

    @NotBlank(message = "El reconocimiento es obligatorio")
    @Size(min = 3, max = 30, message = "El reconocimiento debe tener entre 3 y 30 caracteres")
    private String reconocimiento;

    @NotBlank(message = "La marca es obligatoria")
    @Size(min = 2, max = 40, message = "La marca debe tener entre 2 y 40 caracteres")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(min = 1, max = 40, message = "El modelo debe tener entre 1 y 40 caracteres")
    private String modelo;

    @NotBlank(message = "El año del modelo es obligatorio")
    @Pattern(regexp = "^\\d{4}$", message = "El año debe tener 4 dígitos (ej: 2024)")
    private String anioModelo;

    @NotBlank(message = "El valor a pagar al SRI es obligatorio")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Valor SRI debe ser un número válido (ej: 10 o 10.50)")
    private String valoresPagarSRI;
   
  }
