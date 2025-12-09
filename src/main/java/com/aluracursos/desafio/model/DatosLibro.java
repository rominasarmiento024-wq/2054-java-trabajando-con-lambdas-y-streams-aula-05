package com.aluracursos.desafio.model;

import java.util.List;

public class DatosLibro {

    private String title;
    private List<DatosAutor> authors;
    private Integer download_count;

    public DatosLibro() {
    }

    public String getTitle() {
        return title;
    }

    public List<DatosAutor> getAuthors() {
        return authors;
    }

    public Integer getDownload_count() {
        return download_count;
    }
}
