/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicordoba.cargaclara.seguimiento;

import co.edu.unicordoba.cargaclara.modelo.Encomienda;
import co.edu.unicordoba.cargaclara.modelo.EstadoEncomienda;

/**
 *
 * @author Josed
 */
public interface ObservadorEncomienda {
    void onCambioEstado(
            Encomienda encomienda,
            EstadoEncomienda estadoAnterior,
            EstadoEncomienda estadoNuevo
    );
}
