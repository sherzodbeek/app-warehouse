package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;

    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ProductRepository productRepository;

    public List<Input> getAllInputsService() {
        return inputRepository.findAll();
    }

    public Input getInputByIdService(Integer id) {
        return inputRepository.findById(id).orElseThrow(() -> new IllegalStateException("Bunday ID li input topilmadi!"));
    }

    public Result deleteInputService(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if(!optionalInput.isPresent())
            return new Result("Bunday ID li Input topilmadi!", false);
        inputRepository.deleteById(id);
        return new Result("Input o'chirildi!", true);
    }

    public Result addInputService(InputDto inputDto) {
        Input input = new Input();
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if(!optionalWarehouse.isPresent())
            return new Result("Bunday ID li Warehouse topilmadi!", false);
        Warehouse warehouse = optionalWarehouse.get();
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if(!optionalSupplier.isPresent())
            return new Result("Bunday ID li Supplier topilmadi!", false);
        Supplier supplier = optionalSupplier.get();
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if(!optionalCurrency.isPresent())
            return new Result("Bunday ID li Currency topilmadi!", false);
        Currency currency = optionalCurrency.get();
        input.setWarehouse(warehouse);
        input.setCurrency(currency);
        input.setSupplier(supplier);
        input.setCode(inputRepository.getMaxId()+1);
        input.setDate(inputDto.getDate());
        input.setFactureNumber(inputDto.getFactureNumber());
        Input savedInput = inputRepository.save(input);
        List<InputProductDto> inputProducts = inputDto.getInputProducts();
        for (InputProductDto inputPr : inputProducts) {
            InputProduct inputProduct = new InputProduct();
            Optional<Product> optionalProduct = productRepository.findById(inputPr.getProductId());
            if(!optionalProduct.isPresent())
                return new Result("Bunday ID li Product topilmadi!", false);
            Product product = optionalProduct.get();
            inputProduct.setInput(savedInput);
            inputProduct.setProduct(product);
            inputProduct.setAmount(inputPr.getAmount());
            inputProduct.setPrice(inputPr.getPrice());
            inputProduct.setExpiredDate(inputPr.getExpireDate());
            inputProductRepository.save(inputProduct);
        }
        return new Result("Input qo'shildi!", true);
    }

    public Result editInputService(Integer id, InputDto inputDto) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if(!optionalInput.isPresent())
            return new Result("Bunday ID li Input topilmadi!", false);
        Input editingInput = optionalInput.get();
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if(!optionalWarehouse.isPresent())
            return new Result("Bunday ID li Warehouse topilmadi!", false);
        Warehouse warehouse = optionalWarehouse.get();
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if(!optionalSupplier.isPresent())
            return new Result("Bunday ID li Supplier topilmadi!", false);
        Supplier supplier = optionalSupplier.get();
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if(!optionalCurrency.isPresent())
            return new Result("Bunday ID li Currency topilmadi!", false);
        Currency currency = optionalCurrency.get();
        editingInput.setWarehouse(warehouse);
        editingInput.setCurrency(currency);
        editingInput.setSupplier(supplier);
        editingInput.setCode(inputRepository.getMaxId()+1);
        editingInput.setDate(inputDto.getDate());
        editingInput.setFactureNumber(inputDto.getFactureNumber());
        Input savedInput = inputRepository.save(editingInput);
        List<InputProductDto> inputProducts = inputDto.getInputProducts();
        for (InputProductDto inputPr : inputProducts) {
            if(inputPr.getId()==null) {
                InputProduct inputProduct = new InputProduct();
                Optional<Product> optionalProduct = productRepository.findById(inputPr.getProductId());
                if (!optionalProduct.isPresent())
                    return new Result("Bunday ID li Product topilmadi!", false);
                Product product = optionalProduct.get();
                inputProduct.setInput(savedInput);
                inputProduct.setProduct(product);
                inputProduct.setAmount(inputPr.getAmount());
                inputProduct.setPrice(inputPr.getPrice());
                inputProduct.setExpiredDate(inputPr.getExpireDate());
                inputProductRepository.save(inputProduct);
            } else {
                Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(inputPr.getId());
                if(!optionalInputProduct.isPresent())
                    return new Result("Bunday ID li InputProduct topilmadi!", false);
                InputProduct editingInputProduct = optionalInputProduct.get();
                Optional<Product> optionalProduct = productRepository.findById(inputPr.getProductId());
                if (!optionalProduct.isPresent())
                    return new Result("Bunday ID li Product topilmadi!", false);
                Product product = optionalProduct.get();
                editingInputProduct.setInput(savedInput);
                editingInputProduct.setProduct(product);
                editingInputProduct.setAmount(inputPr.getAmount());
                editingInputProduct.setPrice(inputPr.getPrice());
                editingInputProduct.setExpiredDate(inputPr.getExpireDate());
                inputProductRepository.save(editingInputProduct);
            }
        }
        return new Result("Input edited!", true);
    }
}
