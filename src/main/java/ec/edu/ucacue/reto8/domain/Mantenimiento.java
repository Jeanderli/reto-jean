/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ucacue.reto8.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="Mantenimiento")
public class Mantenimiento implements Serializable{
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
   
    private long idMantenimiento;
    
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

    @NotBlank(message = "El documento es obligatorio")
    @Size(min = 3, max = 30, message = "El documento debe tener entre 3 y 30 caracteres")
    private String documento;

    @NotBlank(message = "El detalle es obligatorio")
    @Size(min = 5, max = 255, message = "El detalle debe tener entre 5 y 255 caracteres")
    private String Detalle;

    @NotBlank(message = "La cantidad es obligatoria")
    @Pattern(regexp = "^[1-9]\\d*$", message = "La cantidad debe ser un entero mayor a 0")
    private String Cantidad;

    @NotBlank(message = "El precio es obligatorio")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Precio debe ser un número válido (ej: 10 o 10.50)")
    private String precio;

    @NotBlank(message = "Recargas es obligatorio")
    @Pattern(regexp = "^\\d{1,9}$", message = "Recargas debe ser un número entero")
    private String Recargas;

    @NotBlank(message = "Total factura es obligatorio")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Total factura debe ser un número válido (ej: 10 o 10.50)")
    private String TotalFactura;

}