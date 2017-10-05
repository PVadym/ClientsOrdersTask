package ua.com.cashup.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cashup.application.entity.Client;
import ua.com.cashup.application.service.ClientService;

import java.util.List;

/**
 * Created by Вадим on 05.10.2017.
 */
@RestController
public class ClientController {


    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "/clients/add")
    public ResponseEntity createClient(@RequestBody Client client){
        if (clientService.isExistClient(client)){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(String.format("Client with Taxpayer Identification Number %s already exist in DataBase",
                            client.getTIN()));
        }
        Client newClient = clientService.save(client);
        return ResponseEntity.ok(newClient);
    }

    @GetMapping(value = "/clients")
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping(value = "/clients/{TIN}")
    public ResponseEntity getClientById(@PathVariable int TIN){
        Client client = clientService.getClientByTIN(TIN);
        if (client == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("Client with Taxpayer Identification Number %d does not exist", TIN));
        }
        return ResponseEntity.ok(client);
    }
    @GetMapping(value = "/clients/find")
    public ResponseEntity getClientByNameAndSurname(
            @RequestParam (value = "name", required = false) String name,
            @RequestParam (value = "surname", required = false) String surname){
        Client client = clientService.getClientByNameAndSurname(name, surname);
        if (client == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("Client with name \"%s\" and surname \"%s\" does not exist", name, surname));
        }
        return ResponseEntity.ok(client);
    }

    @PostMapping(value = "clients/update")
    public ResponseEntity updateClient(@RequestBody Client client){
        Client clientToUpdate = clientService.getClientById(client.getId());
        if (clientToUpdate == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("Client with id %d does not exist", client.getId()));
        }
        clientService.edit(client);
        return ResponseEntity.ok(client);
    }
}
