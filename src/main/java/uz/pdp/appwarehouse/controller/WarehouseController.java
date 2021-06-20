package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @PostMapping("/addWarehouse")
    public Result addWarehouseController(@RequestBody Warehouse warehouse) {
        return warehouseService.addWarehouseService(warehouse);
    }

    @GetMapping
    public List<Warehouse> getWarehouseController() {
        return warehouseService.getWarehousesService();
    }

    @GetMapping("/{id}")
    public Warehouse getWarehouseController(@PathVariable Integer id) {
        return warehouseService.getWarehouseService(id);
    }

    @DeleteMapping("/deleteWarehouse/{id}")
    public Result deleteWarehouseController(@PathVariable Integer id) {
        return warehouseService.deleteWarehouseService(id);
    }

    @PutMapping("/editWarehouse/{id}")
    public Result editWarehouseController(@PathVariable Integer id, @RequestBody Warehouse warehouse) {
        return warehouseService.editWarehouseService(id, warehouse);
    }
}
