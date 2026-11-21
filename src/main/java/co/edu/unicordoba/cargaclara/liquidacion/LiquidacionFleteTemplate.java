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
public abstract class LiquidacionFleteTemplate {
    public final double liquidarFlete(Encomienda encomienda) {
        validarEncomienda(encomienda);
        double base = calcularTarifaBase(encomienda);
        double ajuste = calcularAjusteModalidad(encomienda, base);
        double total = redondearTotal(base + ajuste);
        encomienda.setValorFlete(total);
        return total;
    }

    protected void validarEncomienda(Encomienda encomienda) {
        if (encomienda == null) {
            throw new IllegalArgumentException("La encomienda no puede ser nula");
        }
        if (encomienda.getPesoKg() <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor a 0");
        }
    }

    protected double calcularTarifaBase(Encomienda encomienda) {
        double tarifaPorKg = 4500.0;
        double recargoRuta = encomienda.getOrigen().equalsIgnoreCase(encomienda.getDestino()) ? 0.0 : 3000.0;
        return 8000.0 + (encomienda.getPesoKg() * tarifaPorKg) + recargoRuta;
    }

    protected abstract double calcularAjusteModalidad(Encomienda encomienda, double tarifaBase);

    protected double redondearTotal(double monto) {
        return Math.round(monto);
    }

    public abstract String getNombreModalidad();
}
