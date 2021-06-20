package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping
    public List<Client> getAllClientsController() {
        return clientService.getAllClientsService();
    }

    @GetMapping("/getClientById/{id}")
    public Client getClientController(@PathVariable Integer id) {
        return clientService.getClientService(id);
    }

    @DeleteMapping("/deleteClient/{id}")
    public Result deleteClientController(@PathVariable Integer id) {
        return clientService.deleteClientService(id);
    }

    @PostMapping
    public Result addClientController(@RequestBody Client client) {
        return clientService.addClientService(client);
    }

    @PutMapping("/editClient/{id}")
    public Result editClientController(@PathVariable Integer id, @RequestBody Client client) {
        return clientService.editClientService(id, client);
    }
}
