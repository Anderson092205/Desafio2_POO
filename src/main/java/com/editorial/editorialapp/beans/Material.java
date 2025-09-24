package com.editorial.editorialapp.beans;

// Esta clase representa la entidad "Material" dentro del sistema editorial.
// Un material puede ser un libro o una revista, y está asociado a una categoría y un autor.
public class Material {

    // Identificador único del material.
    // Se usa Integer para permitir valores nulos en registros nuevos que aún no han sido insertados.
    private Integer id;

    // Título del material. Es el nombre que lo identifica dentro del sistema.
    private String titulo;

    // Tipo de material. Puede ser "Libro" o "Revista", según lo que seleccione el usuario.
    private String tipo;

    // Categoría a la que pertenece el material. Se representa como un objeto de tipo Categoria.
    private Categoria categoria;

    // Autor del material. Se representa como un objeto de tipo Autor.
    private Autor autor;

    // Constructor vacío. Es necesario para que frameworks como JSP, servlets o herramientas de persistencia puedan instanciar la clase sin argumentos.
    public Material() {}

    // Constructor completo. Permite crear un material con todos sus datos desde una sola línea.
    public Material(Integer id, String titulo, String tipo, Categoria categoria, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.categoria = categoria;
        this.autor = autor;
    }

    // Getter del ID. Permite acceder al identificador del material.
    public Integer getId() {
        return id;
    }

    // Setter del ID. Permite asignar el identificador del material.
    public void setId(Integer id) {
        this.id = id;
    }

    // Getter del título. Permite obtener el nombre del material.
    public String getTitulo() {
        return titulo;
    }

    // Setter del título. Permite modificar el nombre del material.
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Getter del tipo. Permite acceder al tipo de material ("Libro" o "Revista").
    public String getTipo() {
        return tipo;
    }

    // Setter del tipo. Permite asignar el tipo de material.
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Getter de la categoría. Permite acceder al objeto Categoria asociado al material.
    public Categoria getCategoria() {
        return categoria;
    }

    // Setter de la categoría. Permite asignar una categoría al material.
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // Getter del autor. Permite acceder al objeto Autor asociado al material.
    public Autor getAutor() {
        return autor;
    }

    // Setter del autor. Permite asignar un autor al material.
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}