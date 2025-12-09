package com.aluracursos.desafio.principal;

import com.aluracursos.desafio.model.DatosLibro;
import com.aluracursos.desafio.model.DatosResultado;
import com.aluracursos.desafio.service.ConsumoAPI;
import com.aluracursos.desafio.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {

    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();

    private final String URL_BASE = "https://gutendex.com/books/";

    public void muestraElMenu() {


        System.out.println("""
                ROMEO AND JULIET
                THE STRANGE CASE OF DR. JEKYLL AND MR. HYDE
                ALICE'S ADVENTURES IN WONDERLAND
                THE COMPLETE WORKS OF WILLIAM SHAKESPEARE
                A ROOM WITH A VIEW
                MIDDLEMARCH
                """);

        System.out.println("Ingrese el nombre del libro que desea buscar");
        var titulo = teclado.nextLine();

        // armo la URL para la API
        var url = URL_BASE + "?search=" + titulo.replace(" ", "+");

        // llamo a la API
        var json = consumoAPI.obtenerDatos(url);

        // convierto el JSON a nuestro objeto DatosResultado
        DatosResultado resultado = conversor.obtenerDatos(json, DatosResultado.class);

        System.out.println("\nLibros encontrados:\n");

        if (resultado.getResults() == null || resultado.getResults().isEmpty()) {
            System.out.println("No se encontraron libros para: " + titulo);
            return;
        }

        // uso streams para mostrar los primeros 10
        resultado.getResults().stream()
                .limit(10)
                .forEach(libro -> {
                    System.out.println("Título: " + libro.getTitle());

                    if (libro.getAuthors() != null && !libro.getAuthors().isEmpty()) {
                        System.out.println("Autor: " + libro.getAuthors().get(0).getName());
                    }

                    System.out.println("Descargas: " + libro.getDownload_count());
                    System.out.println("-----------------------------");
                });
    }
}
