package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurementService(Measurement measurement) {
        boolean existByName = measurementRepository.existsByName(measurement.getName());
        if(existByName)
            return new Result("Bunday o'lchov birligi mavjud", false);
        measurementRepository.save(measurement);
        return new Result("Muvaffaqiyatli saqlandi!", true);
    }

    public List<Measurement> getAllMeasurementsService() {
        return measurementRepository.findAll();
    }


    public Measurement getMeasurementByIdService(Integer id) {
        return measurementRepository.findById(id).orElseThrow(() -> new IllegalStateException("Bunday id li measurement topilmadi!"));
    }

    public Result deleteMeasurementService(Integer id) {
        boolean existsById = measurementRepository.existsById(id);
        if(!existsById)
            return new Result("Bunday id li measurement topilmadi!", false);
        measurementRepository.deleteById(id);
        return new Result("Measurement o'chirildi!", true);
    }

    public Result editMeasurementService(Integer id, Measurement measurement) {
        Optional<Measurement> measurementOptional = measurementRepository.findById(id);
        if(!measurementOptional.isPresent())
            return new Result("Bunday id li measurement topilmadi!", false);
        Measurement editingMeasurement = measurementOptional.get();
        editingMeasurement.setName(measurement.getName());
        editingMeasurement.setActive(measurement.isActive());
        measurementRepository.save(editingMeasurement);
        return new Result("Measurement o'zgartirildi!", true);
    }
}
