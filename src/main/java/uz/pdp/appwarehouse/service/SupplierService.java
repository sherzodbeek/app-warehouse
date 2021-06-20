package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public Result addSupplierService(Supplier supplier) {
        Supplier newSupplier = new Supplier();
        newSupplier.setName(supplier.getName());
        newSupplier.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(newSupplier);
        return new Result("Supplier qo'shildi!", true);
    }

    public List<Supplier> getAllSupplierService() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierService(Integer id) {
        return supplierRepository.findById(id).orElseThrow(() -> new IllegalStateException("Bunday ID li supplier topilmadi"));
    }

    public Result deleteSupplierService(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if(!optionalSupplier.isPresent())
            return new Result("Bunday ID li supplier topilmadi", false);
        supplierRepository.deleteById(id);
        return new Result("Supllier o'chirildi", true);
    }

    public Result editSupplierService(Integer id, Supplier supplier) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if(!optionalSupplier.isPresent())
            return new Result("Bunday ID li supplier topilmadi", false);
        Supplier editingSupplier = optionalSupplier.get();
        editingSupplier.setName(supplier.getName());
        editingSupplier.setPhoneNumber(supplier.getPhoneNumber());
        editingSupplier.setActive(supplier.isActive());
        supplierRepository.save(editingSupplier);
        return new Result("Supplier o'zgartirildi!", true);
    }
}
