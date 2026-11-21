/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicordoba.cargaclara.modelo;

import co.edu.unicordoba.cargaclara.seguimiento.ObservadorEncomienda;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 *
 * @author Josed
 */
public class Encomienda {
    private final String codigo;
    private final String remitente;
    private final String destinatario;
    private final String origen;
    private final String destino;
    private final double pesoKg;
    private double valorFlete;
    private EstadoEncomienda estado;
    private final List<ObservadorEncomienda> observadores = new ArrayList<>();

    public Encomienda(
            String codigo,
            String remitente,
            String destinatario,
            String origen,
            String destino,
            double pesoKg
    ) {
        this.codigo = Objects.requireNonNull(codigo, "El código es obligatorio");
        this.remitente = Objects.requireNonNull(remitente, "El remitente es obligatorio");
        this.destinatario = Objects.requireNonNull(destinatario, "El destinatario es obligatorio");
        this.origen = Objects.requireNonNull(origen, "El origen es obligatorio");
        this.destino = Objects.requireNonNull(destino, "El destino es obligatorio");
        if (pesoKg <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que 0 kg");
        }
        this.pesoKg = pesoKg;
        this.estado = EstadoEncomienda.REGISTRADA;
    }

    public void suscribir(ObservadorEncomienda observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void desuscribir(ObservadorEncomienda observador) {
        observadores.remove(observador);
    }

    public void cambiarEstado(EstadoEncomienda nuevoEstado) {
        Objects.requireNonNull(nuevoEstado, "El nuevo estado no puede ser nulo");
        EstadoEncomienda anterior = this.estado;
        if (anterior == nuevoEstado) {
            return;
        }
        this.estado = nuevoEstado;
        notificarObservadores(anterior, nuevoEstado);
    }

    private void notificarObservadores(EstadoEncomienda anterior, EstadoEncomienda nuevo) {
        for (ObservadorEncomienda observador : List.copyOf(observadores)) {
            observador.onCambioEstado(this, anterior, nuevo);
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getRemitente() {
        return remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public double getValorFlete() {
        return valorFlete;
    }

    public void setValorFlete(double valorFlete) {
        this.valorFlete = valorFlete;
    }

    public EstadoEncomienda getEstado() {
        return estado;
    }
}
