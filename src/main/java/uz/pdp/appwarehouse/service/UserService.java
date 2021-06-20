package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.entity.Warehouse;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.repository.UserRepository;
import uz.pdp.appwarehouse.repository.WarehouseRepository;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    public List<User> getAllUsersService() {
        return userRepository.findAll();
    }

    public User getUserService(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException("Bunday ID li User topilmadi!"));
    }

    public Result deleteUserService(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent())
            return new Result("Bunday ID li User topilmadi!", false);
        userRepository.deleteById(id);
        return new Result("User o'chirildi", true);
    }

    public Result addUserService(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setCode(userRepository.getMaxId()+1);
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        Set<Integer> warehousesId = userDto.getWarehousesId();
        Set<Warehouse> warehouses = new LinkedHashSet<>();
        for (Integer wId : warehousesId) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(wId);
            if(!optionalWarehouse.isPresent())
                return new Result("Warehouse topilmadi!", false);
            Warehouse warehouse = optionalWarehouse.get();
            warehouses.add(warehouse);
        }
        user.setWarehouses(warehouses);
        userRepository.save(user);
        return new Result("User qo'shildi!", true);
    }


    public Result editUserService(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent())
            return new Result("Bunday ID li User topilmadi!", false);
        User editingUser = optionalUser.get();
        editingUser.setLastName(userDto.getLastName());
        editingUser.setFirstName(userDto.getFirstName());
        editingUser.setPassword(userDto.getPassword());
        editingUser.setActive(userDto.isActive());
        editingUser.setPhoneNumber(userDto.getPhoneNumber());
        Set<Integer> warehousesId = userDto.getWarehousesId();
        Set<Warehouse> warehouses = new LinkedHashSet<>();
        for (Integer wId : warehousesId) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(wId);
            if(!optionalWarehouse.isPresent())
                return new Result("Warehouse topilmadi!", false);
            Warehouse warehouse = optionalWarehouse.get();
            warehouses.add(warehouse);
        }
        editingUser.setWarehouses(warehouses);
        userRepository.save(editingUser);
        return new Result("User o'zgartirildi!", true);
    }
}
