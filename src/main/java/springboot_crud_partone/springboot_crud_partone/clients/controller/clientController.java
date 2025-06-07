package springboot_crud_partone.springboot_crud_partone.clients.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot_crud_partone.springboot_crud_partone.clients.model.clients;
import springboot_crud_partone.springboot_crud_partone.clients.service.clienteService;

@RestController
@RequestMapping("/api/clients")
public class clientController {

    private final clienteService clienteService;

    // Constructor de la clase que recibe un servicio de clientes
    public clientController(clienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Método para obtener todos los clientes, este será llamado desde el controlador
    @GetMapping
    public List<clients> getAllClients() {
        
        System.out.println("Fetching all clients");
        return clienteService.getAllClients();
    }
    @GetMapping("/get/{id}")
    public clients getClientId(@PathVariable Long id) {
        return clienteService.getClientById(id);
    }
    @PostMapping("/add")
    public void nuevoClient(@RequestBody clients client) {
        clienteService.inserClients(client);
    }
    @PutMapping("/update/{id}")
    public void actualizarClient(@PathVariable Long id,@RequestBody clients datos) {
         clienteService.updateClient(id, datos);
    }
    @DeleteMapping("/delete/{id}")
    public clients eliminarClient(@PathVariable Long id) {
        return clienteService.deleteClient(id);
    }

}
