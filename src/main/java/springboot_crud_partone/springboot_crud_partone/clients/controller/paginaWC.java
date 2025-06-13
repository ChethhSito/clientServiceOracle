package springboot_crud_partone.springboot_crud_partone.clients.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class paginaWC {
    @GetMapping("/clientes")
    public String PageClients() {
        return "paginas/clientes";
    }
     @GetMapping("/agregarCliente")
    public String AddCliente() {
        return "formularios/agregarCliente";
    }
    @GetMapping("/editarCliente")
    public String EditCliente() {
        return "formularios/editarCliente";
    }
    @GetMapping("/eliminarCliente")
    public String DeleteCliente() {
        return "formularios/eliminarCliente";
    }
    @GetMapping("/bienvenida")
    public String PageWelcome() {
        return "paginas/bienvenida";
    }
}
