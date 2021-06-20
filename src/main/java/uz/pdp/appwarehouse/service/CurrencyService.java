package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrencyService(Currency currency) {
        Currency newCurrency = new Currency();
        newCurrency.setName(currency.getName());
        currencyRepository.save(newCurrency);
        return new Result("Currency qo'shildi!", true);
    }

    public List<Currency> getCurrenciesService() {
        return currencyRepository.findAll();
    }

    public Currency getCurrencyService(Integer id) {
        return currencyRepository.findById(id).orElseThrow(() -> new IllegalStateException("Currency topilmadi"));
    }


    public Result deleteCurrencyService(Integer id) {
        boolean existsById = currencyRepository.existsById(id);
        if(!existsById)
            return new Result("Bundaty ID li currency topilmadi!", false);
        currencyRepository.deleteById(id);
        return new Result("Currency o'chirildi", true);
    }


    public Result editCurrencyService(Integer id, Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if(!optionalCurrency.isPresent())
            return new Result("Bunday ID li currency topilmadi!", false);
        Currency editingCurrency = optionalCurrency.get();
        editingCurrency.setName(currency.getName());
        editingCurrency.setActive(currency.isActive());
        currencyRepository.save(editingCurrency);
        return new Result("Currency o'zgartirildi!", true);
    }
}
