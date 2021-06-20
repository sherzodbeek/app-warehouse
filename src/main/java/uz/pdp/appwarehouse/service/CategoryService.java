package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        if(categoryDto.getParentCategoryId()!=null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if(!optionalParentCategory.isPresent())
                return new Result("Bunday ota kategoriya mavjud emas!", false);
            category.setParentCategoryId(optionalParentCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Muvaffaqiyatli saqlandi!", true);
    }

    public List<Category> getAllCategoryService() {
        return categoryRepository.findAll();
    }

    public Category getCategoryService(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalStateException("Bunday ID li categoriya topilmadi!"));
    }

    public Result deleteCategoryService(Integer id) {
        boolean existsById = categoryRepository.existsById(id);
        if(!existsById)
            return new Result("Bunday ID li categoriya topilmadi!", false);
        categoryRepository.deleteById(id);
        return new Result("Categoriya o'chirildi", true);
    }


    public Result editCategoryService(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(!optionalCategory.isPresent())
            return new Result("Bunday ID li categoriya topilmadi!", false);
        Category editingCategory = optionalCategory.get();
        editingCategory.setName(categoryDto.getName());
        editingCategory.setActive(categoryDto.isActive());
        if(categoryDto.getParentCategoryId()!=null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if(!optionalParentCategory.isPresent())
                return new Result("Bunday ID li categoriya topilmadi!", false);
            Category parentCategory = optionalParentCategory.get();
            editingCategory.setParentCategoryId(parentCategory);
        }
        categoryRepository.save(editingCategory);
        return new Result("Kategoriya o'zgaritirildi!", true);
    }
}
