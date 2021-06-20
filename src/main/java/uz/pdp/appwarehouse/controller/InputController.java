package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;

    @GetMapping
    public List<Input> getAllInputsController() {
        return inputService.getAllInputsService();
    }

    @GetMapping("/getInputById/{id}")
    public Input getInputByIdController(@PathVariable Integer id) {
        return inputService.getInputByIdService(id);
    }

    @DeleteMapping("/deleteInput/{id}")
    public Result deleteInputController(@PathVariable Integer id) {
        return inputService.deleteInputService(id);
    }

    @PostMapping
    public Result addInputController(@RequestBody InputDto inputDto) {
        return inputService.addInputService(inputDto);
    }

    @PutMapping("/editInput/{id}")
    public Result editInputController(@PathVariable Integer id, @RequestBody InputDto inputDto) {
        return inputService.editInputService(id, inputDto);
    }
}
