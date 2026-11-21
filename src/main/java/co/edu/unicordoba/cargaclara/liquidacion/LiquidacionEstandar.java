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
public class LiquidacionEstandar extends LiquidacionFleteTemplate{
    @Override
    protected double calcularAjusteModalidad(Encomienda encomienda, double tarifaBase) {
        if (encomienda.getPesoKg() >= 10.0) {
            return -tarifaBase * 0.05; // 5% de descuento por volumen estándar
        }
        return 0.0;
    }

    @Override
    public String getNombreModalidad() {
        return "Estándar";
    }
}
