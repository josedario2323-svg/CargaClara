/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicordoba.cargaclara.liquidacion;

import co.edu.unicordoba.cargaclara.modelo.Encomienda;
/**
 *
 * @author Josed
 */
public class LiquidacionExpress extends LiquidacionFleteTemplate{
    @Override
    protected double calcularAjusteModalidad(Encomienda encomienda, double tarifaBase) {
        return tarifaBase * 0.25 + 5000.0; // Recargo prioritario del 25% + manejo rápido
    }

    @Override
    public String getNombreModalidad() {
        return "Express";
    }
}
