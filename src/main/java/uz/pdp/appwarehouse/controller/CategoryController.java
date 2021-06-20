package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto) {
       return categoryService.addCategory(categoryDto);
    }

    @GetMapping
    public List<Category> getAllCategoryController() {
        return categoryService.getAllCategoryService();
    }

    @GetMapping("/getById/{id}")
    public Category getCategoryController(@PathVariable Integer id) {
        return categoryService.getCategoryService(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteCategoryController(@PathVariable Integer id) {
        return categoryService.deleteCategoryService(id);
    }

    @PutMapping("/editCategory/{id}")
    public Result editCategoryController(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        return categoryService.editCategoryService(id, categoryDto);
    }

}
