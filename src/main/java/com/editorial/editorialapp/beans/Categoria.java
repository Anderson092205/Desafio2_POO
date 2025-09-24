package com.editorial.editorialapp.beans;

// Esta clase representa la entidad "Categoría" dentro del sistema editorial.
// Se utiliza para encapsular los datos de cada categoría y facilitar su gestión desde el DAO, el servlet y las vistas.
public class Categoria {

    // Identificador único de la categoría.
    // Se usa Integer (en vez de int) para permitir valores nulos en registros nuevos que aún no han sido insertados.
    private Integer id;

    // Nombre de la categoría. Es el campo principal que identifica el tipo de contenido editorial.
    private String nombre;

    // Constructor vacío. Es necesario para que frameworks como JSP, servlets o herramientas de persistencia puedan instanciar la clase sin argumentos.
    public Categoria() {
    }

    // Constructor completo. Permite crear una categoría con todos sus datos desde una sola línea.
    public Categoria(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getter del ID. Permite acceder al identificador de la categoría.
    public Integer getId() {
        return id;
    }

    // Setter del ID. Permite asignar el identificador de la categoría.
    public void setId(Integer id) {
        this.id = id;
    }

    // Getter del nombre. Permite obtener el nombre de la categoría.
    public String getNombre() {
        return nombre;
    }

    // Setter del nombre. Permite modificar el nombre de la categoría.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
