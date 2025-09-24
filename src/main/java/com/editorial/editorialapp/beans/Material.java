package com.editorial.editorialapp.beans;

public class Material {
    private Integer id; // ← ahora puede ser null para nuevos registros
    private String titulo;
    private String tipo; // Libro o Revista
    private Categoria categoria;
    private Autor autor;

    public Material() {}

    public Material(Integer id, String titulo, String tipo, Categoria categoria, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.categoria = categoria;
        this.autor = autor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
