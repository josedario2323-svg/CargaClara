/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicordoba.cargaclara.seguimiento;

import co.edu.unicordoba.cargaclara.modelo.Encomienda;
import co.edu.unicordoba.cargaclara.modelo.EstadoEncomienda;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author Josed
 */
public class HistorialArchivoObserver implements ObservadorEncomienda{
    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Path archivoHistorial;

    public HistorialArchivoObserver(String rutaArchivo) {
        Objects.requireNonNull(rutaArchivo, "La ruta del archivo es obligatoria");
        this.archivoHistorial = Path.of(rutaArchivo);
    }

    @Override
    public void onCambioEstado(
            Encomienda encomienda,
            EstadoEncomienda estadoAnterior,
            EstadoEncomienda estadoNuevo
    ) {
        String timestamp = LocalDateTime.now().format(FORMATO_FECHA);
        String linea = String.join(";",
                timestamp,
                encomienda.getCodigo(),
                encomienda.getOrigen(),
                encomienda.getDestino(),
                estadoAnterior.name(),
                estadoNuevo.name(),
                String.valueOf(encomienda.getValorFlete())
        ) + System.lineSeparator();

        try {
            Path carpetaPadre = archivoHistorial.getParent();
            if (carpetaPadre != null) {
                Files.createDirectories(carpetaPadre);
            }
            Files.writeString(
                    archivoHistorial,
                    linea,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.err.println("[HistorialArchivoObserver] Error al escribir historial: " + e.getMessage());
        }
    }

    public Path getArchivoHistorial() {
        return archivoHistorial;
    }
}
