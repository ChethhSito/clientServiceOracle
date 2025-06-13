package springboot_crud_partone.springboot_crud_partone.clients.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot_crud_partone.springboot_crud_partone.clients.model.clients;
import springboot_crud_partone.springboot_crud_partone.clients.repository.clientRepository;

@Service
public class clienteService {

    private final clientRepository clientRepository;

    public clienteService(clientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Transactional(readOnly = true)
    public List<clients> getAllClients() {
        return clientRepository.obtenerTodosClientes();
    }
    @Transactional(readOnly = true)
    public clients getClientById(Long id) {
        return clientRepository.obtenerClientePorId(id);
    }
    @Transactional(readOnly = true)
    public void inserClients(clients client) {
        //lamas al procedimiento de insert los parametros deben estar en el mismo orden que en la base de datos
        clientRepository.insertarCliente(
            client.getNombreCompleto(), 
            client.getDni(), 
            client.getCorreoElectronico(), 
            client.getTelefono(), 
            client.getEstado(), 
            client.getFechaRegistro());
    }
    @Transactional(readOnly = true)
    public void updateClient(Long id, clients clientDa){
        try{
            clients existing= clientRepository.obtenerClientePorId(id);
        if(existing != null) {
            //lamas al procedimiento de actualización los parametros deben estar en el mismo orden que en la base de datos
            clientRepository.actualizarCliente(id, 
                clientDa.getNombreCompleto(), 
                clientDa.getDni(), 
                clientDa.getCorreoElectronico(), 
                clientDa.getTelefono(), 
                clientDa.getEstado(), 
                clientDa.getFechaRegistro());
        }}catch (Exception e) {
            throw new RuntimeException("Error updating client with id: " + id, e);
        }
        
    }
    @Transactional(readOnly = true)
    public clients deleteClient(Long id){
        try{
            clients client= clientRepository.obtenerClientePorId(id);
            if(client != null) {
                clientRepository.eliminarCliente(id);
                return client;
        }
    }catch (Exception e) {
            throw new RuntimeException("Error deleting client with id: " + id, e);
        }
        return null;
    }
    
}
