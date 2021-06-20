package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;

    @Autowired
    OutputProductRepository outputProductRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    ClientRepository clientRepository;

    public List<Output> getAllOutputsService() {
        return outputRepository.findAll();
    }

    public Output getOutputByIdService(Integer id) {
        return outputRepository.findById(id).orElseThrow(() -> new IllegalStateException("Bunday ID li Output topilmadi!"));
    }

    public Result deleteOutputService(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if(!optionalOutput.isPresent())
            return new Result("Bunday ID li Output topilmadi!", false);
        outputRepository.deleteById(id);
        return new Result("Output o'chirildi!", true);
    }

    public Result addOutputService(OutputDto outputDto) {
        Output output = new Output();
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if(!optionalWarehouse.isPresent())
            return new Result("Bunday ID li Warehouse topilmadi!", false);
        Warehouse warehouse = optionalWarehouse.get();
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if(!optionalCurrency.isPresent())
            return new Result("Bunday ID li Currency topilmadi!", false);
        Currency currency = optionalCurrency.get();
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if(!optionalClient.isPresent())
            return new Result("Bunday ID li Client topilmadi!", false);
        Client client = optionalClient.get();
        output.setClient(client);
        output.setWarehouse(warehouse);
        output.setCurrency(currency);
        output.setCode(outputRepository.getMaxId()+1);
        output.setDate(outputDto.getDate());
        output.setFactureNumber(outputDto.getFactureNumber());
        Output savedOutput = outputRepository.save(output);
        List<OutputProductDto> OutputProducts = outputDto.getOutputProducts();
        for (OutputProductDto outputPr : OutputProducts) {
            OutputProduct outputProduct = new OutputProduct();
            Optional<Product> optionalProduct = productRepository.findById(outputPr.getProductId());
            if(!optionalProduct.isPresent())
                return new Result("Bunday ID li Product topilmadi!", false);
            Product product = optionalProduct.get();
            outputProduct.setOutput(savedOutput);
            outputProduct.setProduct(product);
            outputProduct.setAmount(outputPr.getAmount());
            outputProduct.setPrice(outputPr.getPrice());
            outputProductRepository.save(outputProduct);
        }
        return new Result("Output qo'shildi!", true);
    }

    public Result editOutputService(Integer id, OutputDto outputDto) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if(!optionalOutput.isPresent())
            return new Result("Bunday ID li Output topilmadi!", false);
        Output editingOutput = optionalOutput.get();
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if(!optionalWarehouse.isPresent())
            return new Result("Bunday ID li Warehouse topilmadi!", false);
        Warehouse warehouse = optionalWarehouse.get();
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if(!optionalCurrency.isPresent())
            return new Result("Bunday ID li Currency topilmadi!", false);
        Currency currency = optionalCurrency.get();
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if(!optionalClient.isPresent())
            return new Result("Bunday ID li Client topilmadi!", false);
        Client client = optionalClient.get();
        editingOutput.setClient(client);
        editingOutput.setWarehouse(warehouse);
        editingOutput.setCurrency(currency);
        editingOutput.setCode(outputRepository.getMaxId()+1);
        editingOutput.setDate(outputDto.getDate());
        editingOutput.setFactureNumber(outputDto.getFactureNumber());
        Output savedOutput = outputRepository.save(editingOutput);
        List<OutputProductDto> OutputProducts = outputDto.getOutputProducts();
        for (OutputProductDto outputPr : OutputProducts) {
            if(outputPr.getId()==null) {
                OutputProduct outputProduct = new OutputProduct();
                Optional<Product> optionalProduct = productRepository.findById(outputPr.getProductId());
                if (!optionalProduct.isPresent())
                    return new Result("Bunday ID li Product topilmadi!", false);
                Product product = optionalProduct.get();
                outputProduct.setOutput(savedOutput);
                outputProduct.setProduct(product);
                outputProduct.setAmount(outputPr.getAmount());
                outputProduct.setPrice(outputPr.getPrice());
                outputProductRepository.save(outputProduct);
            } else {
                Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(outputPr.getId());
                if(!optionalOutputProduct.isPresent())
                    return new Result("Bunday ID li OutputProduct topilmadi!", false);
                OutputProduct editingOutputProduct = optionalOutputProduct.get();
                Optional<Product> optionalProduct = productRepository.findById(outputPr.getProductId());
                if (!optionalProduct.isPresent())
                    return new Result("Bunday ID li Product topilmadi!", false);
                Product product = optionalProduct.get();
                editingOutputProduct.setOutput(savedOutput);
                editingOutputProduct.setProduct(product);
                editingOutputProduct.setAmount(outputPr.getAmount());
                editingOutputProduct.setPrice(outputPr.getPrice());
                outputProductRepository.save(editingOutputProduct);
            }
        }
        return new Result("Output edited!", true);
    }
}
