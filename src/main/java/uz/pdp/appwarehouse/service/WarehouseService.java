package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;


    public Result addWarehouseService(Warehouse warehouse) {
        Warehouse newWarehouse = new Warehouse();
        warehouse.setName(warehouse.getName());
        warehouseRepository.save(newWarehouse);
        return new Result("Warehouse qo'shildi!", true);
    }

    public List<Warehouse> getWarehousesService() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseService(Integer id) {
        return warehouseRepository.findById(id).orElseThrow(() -> new IllegalStateException("Bunday ID li Warehouse topilmadi!"));
    }

    public Result deleteWarehouseService(Integer id) {
        boolean existsById = warehouseRepository.existsById(id);
        if(!existsById)
            return new Result("Bunday ID li Warehouse topilmadi!", false);
        warehouseRepository.deleteById(id);
        return new Result("Warehouse o'chirildi", true);
    }

    public Result editWarehouseService(Integer id, Warehouse warehouse) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if(!optionalWarehouse.isPresent())
            return new Result("Warehouse o'chirildi", true);
        Warehouse newWarehouse = optionalWarehouse.get();
        newWarehouse.setName(warehouse.getName());
        newWarehouse.setActive(warehouse.isActive());
        warehouseRepository.save(newWarehouse);
        return new Result("Warehouse o'zgaritirldi!", true);
    }
}
