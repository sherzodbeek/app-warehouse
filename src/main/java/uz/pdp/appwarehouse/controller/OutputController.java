package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Output;
import uz.pdp.appwarehouse.entity.Output;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputService outputService;
    
    @GetMapping
    public List<Output> getAllOutputsController() {
        return outputService.getAllOutputsService();
    }

    @GetMapping("/getOutputById/{id}")
    public Output getOutputByIdController(@PathVariable Integer id) {
        return outputService.getOutputByIdService(id);
    }

    @DeleteMapping("/deleteOutput/{id}")
    public Result deleteOutputController(@PathVariable Integer id) {
        return outputService.deleteOutputService(id);
    }

    @PostMapping
    public Result addOutputController(@RequestBody OutputDto outputDto) {
        return outputService.addOutputService(outputDto);
    }

    @PutMapping("/editOutput/{id}")
    public Result editOutputController(@PathVariable Integer id, @RequestBody OutputDto outputDto) {
        return outputService.editOutputService(id, outputDto);
    }
}
