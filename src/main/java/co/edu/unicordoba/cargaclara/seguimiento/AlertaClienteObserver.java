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
public class AlertaClienteObserver implements ObservadorEncomienda{
    @Override
    public void onCambioEstado(
            Encomienda encomienda,
            EstadoEncomienda estadoAnterior,
            EstadoEncomienda estadoNuevo
    ) {
        String detalle = switch (estadoNuevo) {
            case REGISTRADA -> "Guía generada para " + encomienda.getDestinatario()
                    + " (" + encomienda.getOrigen() + " -> " + encomienda.getDestino() + ").";
            case EN_RUTA -> "Encomienda despachada en ruta hacia " + encomienda.getDestino() + ".";
            case ENTREGADA -> "Entregada satisfactoriamente a " + encomienda.getDestinatario() + ".";
            case NOVEDAD -> "¡ATENCIÓN! Novedad reportada en el envío hacia " + encomienda.getDestino() + ".";
        };

        System.out.printf("[AlertaClienteObserver] Guía %s | %s -> %s | %s%n",
                encomienda.getCodigo(),
                estadoAnterior,
                estadoNuevo,
                detalle);
    }
}
