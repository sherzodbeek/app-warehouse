package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @PostMapping("/addCurrency")
    public Result addCurrencyController(@RequestBody Currency currency) {
        return currencyService.addCurrencyService(currency);
    }

    @GetMapping
    public List<Currency> getCurrenciesController() {
        return currencyService.getCurrenciesService();
    }

    @GetMapping("/id")
    public Currency getCurrencyController(@PathVariable Integer id) {
        return currencyService.getCurrencyService(id);
    }

    @DeleteMapping("/deleteCurrency/{id}")
    public Result deleteCurrencyController(@PathVariable Integer id) {
        return currencyService.deleteCurrencyService(id);
    }

    @PostMapping("/editCurrency/{id}")
    public Result editCurrencyController(@PathVariable Integer id, @RequestBody Currency currency) {
        return currencyService.editCurrencyService(id, currency);
    }

}
