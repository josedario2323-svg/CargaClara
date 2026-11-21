/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicordoba.cargaclara.servicio;

import co.edu.unicordoba.cargaclara.modelo.Encomienda;
import co.edu.unicordoba.cargaclara.modelo.EstadoEncomienda;
import co.edu.unicordoba.cargaclara.liquidacion.LiquidacionFleteTemplate;
import co.edu.unicordoba.cargaclara.seguimiento.ObservadorEncomienda;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/**
 *
 * @author Josed
 */
public class GestorEncomiendas {
    private final Map<String, Encomienda> encomiendasPorCodigo = new LinkedHashMap<>();
    private final List<ObservadorEncomienda> observadoresPorDefecto = new ArrayList<>();

    public void agregarObservadorPorDefecto(ObservadorEncomienda observador) {
        if (observador != null && !observadoresPorDefecto.contains(observador)) {
            observadoresPorDefecto.add(observador);
        }
    }

    public Encomienda registrarYLiquidar(
            Encomienda encomienda,
            LiquidacionFleteTemplate liquidacion
    ) {
        Objects.requireNonNull(encomienda, "La encomienda es obligatoria");
        Objects.requireNonNull(liquidacion, "La modalidad de liquidación es obligatoria");

        if (encomiendasPorCodigo.containsKey(encomienda.getCodigo())) {
            throw new IllegalArgumentException(
                    "Ya existe una encomienda registrada con el código: " + encomienda.getCodigo()
            );
        }

        for (ObservadorEncomienda observador : observadoresPorDefecto) {
            encomienda.suscribir(observador);
        }

        liquidacion.liquidarFlete(encomienda);
        encomiendasPorCodigo.put(encomienda.getCodigo(), encomienda);
        return encomienda;
    }

    public void cambiarEstado(String codigo, EstadoEncomienda nuevoEstado) {
        Encomienda encomienda = buscarPorCodigo(codigo);
        encomienda.cambiarEstado(nuevoEstado);
    }

    public Encomienda buscarPorCodigo(String codigo) {
        Objects.requireNonNull(codigo, "El código de búsqueda es obligatorio");
        Encomienda encomienda = encomiendasPorCodigo.get(codigo);
        if (encomienda == null) {
            throw new IllegalArgumentException("No se encontró la encomienda con código: " + codigo);
        }
        return encomienda;
    }

    public List<Encomienda> listarEncomiendas() {
        return Collections.unmodifiableList(new ArrayList<>(encomiendasPorCodigo.values()));
    }
}
