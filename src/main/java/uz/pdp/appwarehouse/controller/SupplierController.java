package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @PostMapping
    public Result addSupplierController(@RequestBody Supplier supplier) {
        return supplierService.addSupplierService(supplier);
    }

    @GetMapping
    public List<Supplier> getAllSupplierController() {
        return supplierService.getAllSupplierService();
    }

    @GetMapping("/getById/{id}")
    public Supplier getSupplierController(@PathVariable Integer id) {
        return supplierService.getSupplierService(id);
    }

    @DeleteMapping("/deleteSupplier/{id}")
    public Result deleteSupplierController(@PathVariable Integer id) {
        return supplierService.deleteSupplierService(id);
    }

    @PutMapping("/editSupplier/{id}")
    public Result editSupplierController(@PathVariable Integer id, @RequestBody Supplier supplier) {
        return supplierService.editSupplierService(id, supplier);
    }
}
