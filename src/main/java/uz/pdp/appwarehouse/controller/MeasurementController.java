package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement) {
        return measurementService.addMeasurementService(measurement);
    }


    @GetMapping("/allMeasurement")
    public List<Measurement> getAllMeasurementsController() {
        return measurementService.getAllMeasurementsService();
    }

    @GetMapping("/measurementById/{id}")
    public Measurement getMeasurementByIdController(@PathVariable Integer id) {
        return measurementService.getMeasurementByIdService(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteMeasurementController(@PathVariable Integer id) {
        return measurementService.deleteMeasurementService(id);
    }

    @PutMapping("/editById/{id}")
    public Result editMeasurementController(@PathVariable Integer id, @RequestBody Measurement measurement) {
        return measurementService.editMeasurementService(id, measurement);
    }

}
