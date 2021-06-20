package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public List<Client> getAllClientsService() {
        return clientRepository.findAll();
    }

    public Client getClientService(Integer id) {
        return clientRepository.findById(id).orElseThrow(() -> new IllegalStateException("Bunday ID li CLient topilmadi!"));
    }


    public Result deleteClientService(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(!optionalClient.isPresent())
            return new Result("Bunday ID li CLient topilmadi!", false);
        clientRepository.deleteById(id);
        return new Result("Client o'chirilid", true);
    }

    public Result addClientService(Client client) {
        Client newClient = new Client();
        newClient.setPhoneNumber(client.getPhoneNumber());
        newClient.setName(client.getName());
        clientRepository.save(newClient);
        return new Result("Client qo'shildi", true);
    }

    public Result editClientService(Integer id, Client client) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(!optionalClient.isPresent())
            return new Result("Bunday ID li CLient topilmadi!", false);
        Client editingClient = optionalClient.get();
        editingClient.setName(client.getName());
        editingClient.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(editingClient);
        return new Result("Client o'zgaritrildi!", true);
    }
}
