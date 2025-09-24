package com.editorial.editorialapp.beans;

// Esta clase representa la entidad Autor dentro del sistema editorial.
// Se utiliza para encapsular los datos de cada autor y facilitar su gestión desde el DAO, el servlet y las vistas.
public class Autor {

    // Identificador único del autor. Se usa Integer para permitir valores nulos cuando aún no ha sido insertado.
    private Integer id;

    // Nombre del autor. Campo obligatorio para identificarlo en el sistema.
    private String nombre;

    // Nacionalidad del autor. Permite clasificar y mostrar el origen del autor.
    private String nacionalidad;

    // Constructor vacío. Es necesario para que frameworks como JSP o servlets puedan instanciar la clase sin argumentos.
    public Autor() {}

    // Constructor completo. Permite crear un autor con todos sus datos desde una sola línea.
    public Autor(Integer id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    // Getter del ID. Permite acceder al identificador del autor.
    public Integer getId() {
        return id;
    }

    // Setter del ID. Permite asignar el identificador del autor.
    public void setId(Integer id) {
        this.id = id;
    }

    // Getter del nombre. Permite obtener el nombre del autor.
    public String getNombre() {
        return nombre;
    }

    // Setter del nombre. Permite modificar el nombre del autor.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter de la nacionalidad. Permite acceder al país de origen del autor.
    public String getNacionalidad() {
        return nacionalidad;
    }

    // Setter de la nacionalidad. Permite asignar o modificar el país de origen del autor.
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
