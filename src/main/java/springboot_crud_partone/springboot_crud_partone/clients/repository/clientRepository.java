package springboot_crud_partone.springboot_crud_partone.clients.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import springboot_crud_partone.springboot_crud_partone.clients.model.clients;

public interface  clientRepository extends  JpaRepository<clients, Long>{
    @Procedure(name = "Cliente.insertar")
    void insertarCliente(String p_nombre_completo, String p_dni, String p_correo_electronico, 
    String p_estado, String p_telefono, java.util.Date p_fecha_registro);

    @Procedure(name = "Cliente.actualizar")
    void actualizarCliente(Long p_cliente_id, String p_nombre_completo, String p_dni,
    String p_correo_electronico, String p_estado, String p_telefono, java.util.Date p_fecha_registro);

    @Procedure(name = "Cliente.eliminar")
    void eliminarCliente(Long p_cliente_id);

    @Procedure(name = "Cliente.obtener_todos_clientes")
    List<clients> obtenerTodosClientes();
    
    @Procedure(name = "Cliente.obtener_cliente_por_id")
    clients obtenerClientePorId(Long p_cliente_id);
}

