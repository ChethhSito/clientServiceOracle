package springboot_crud_partone.springboot_crud_partone.clients.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;

@NamedStoredProcedureQuery(
    name= "Cliente.insertar",
    procedureName= "CLIENTES_PKG.insertar_cliente",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre_completo", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dni", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_correo_electronico", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_estado", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_telefono", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fecha_registro", type = Date.class)
    }
)
@NamedStoredProcedureQuery(
    name = "Cliente.actualizar",
    procedureName = "CLIENTES_PKG.actualizar_cliente",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cliente_id", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre_completo", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dni", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_correo_electronico", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_estado", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_telefono", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fecha_registro", type = Date.class)
    }
)
@NamedStoredProcedureQuery(
    name = "Cliente.eliminar",
    procedureName = "CLIENTES_PKG.eliminar_cliente",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cliente_id", type = Long.class)
    }
)
@NamedStoredProcedureQuery(
    name = "Cliente.obtener_todos_clientes",
    procedureName = "CLIENTES_PKG.obtener_todos_clientes",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_resultado", type = void.class)
    },
    resultClasses = clients.class
)
@NamedStoredProcedureQuery(
    name = "Cliente.obtener_cliente_por_id",
    procedureName = "CLIENTES_PKG.obtener_cliente_por_id",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cliente_id", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_resultado", type = void.class)
    },
    resultClasses = clients.class
)


@Entity
@Table(name = "CLIENTES")
public class clients {
    
    @Id
    @Column(name = "cliente_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nombre_completo")
    private String nombreCompleto;
    @Column(name="dni")
    private String dni;
    @Column(name="correo_electronico")
    private String correoElectronico;
    @Column(name="telefono")
    private String telefono;
    @Column(name="fecha_registro")
    private Date fechaRegistro;
    @Column(name="estado")
    private String estado;

    public Long getId() {return id;}
    public void setId(Long id){this.id = id; }

    public String getNombreCompleto(){return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto){ this.nombreCompleto = nombreCompleto;}

    public String getDni(){return dni;}
    public void setDni(String dni){ this.dni=dni;}

    public String getCorreoElectronico(){return correoElectronico;}
    public void setCorreoElectronico(String correoElectronico){ this.correoElectronico=correoElectronico;}

    public String getTelefono(){return telefono;}
    public void setTelefono(String telefono){ this.telefono=telefono;}

    public Date getFechaRegistro(){return fechaRegistro;}
    public void setFechaRegistro(Date fechaRegistro){ this.fechaRegistro=fechaRegistro; }

    public String getEstado(){return estado;}
    public void setEstado(String estado){ this.estado=estado;}
}

