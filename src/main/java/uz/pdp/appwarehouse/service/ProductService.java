package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.AttachmentRepository;
import uz.pdp.appwarehouse.repository.CategoryRepository;
import uz.pdp.appwarehouse.repository.MeasurementRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addProduct(ProductDto productDto) {
        boolean existsByNameAndCategoryId = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if(existsByNameAndCategoryId)
            return new Result("Bunday maxsulot ushbu kategoriyada mavjud", false);

        // CATEGORIYANI TEKSHIRISH
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()) {
            return new Result("Bunday kategoriya mavjud emas!", false);
        }

        //PHOTO TEKSHIRISH
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if(!optionalAttachment.isPresent())
            return new Result("Bunday rasm mavjud emas", false);

        //MEASURMENTNI TEKSHIRISH
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if(!optionalMeasurement.isPresent())
            return new Result("Bunday o'lchov birligi mavjud emas!", false);

        //SAQLASH
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode(productRepository.getMaxId());//generatsiya
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new Result("Maxsulot saqlandi!", true);
    }


    public List<Product> getAllProductsService() {
        return productRepository.findAll();
    }

    public Product getProductService(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalStateException("Bunday ID li product topilmadi!"));
    }

    public Result deleteProductService(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(!optionalProduct.isPresent())
            return new Result("Bunday ID li product topilmadi!", false);
        productRepository.deleteById(id);
        return new Result("Product o'chirildi", true);
    }


    public Result editProductService(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(!optionalProduct.isPresent())
            return new Result("Bunday ID li product topilmadi!", false);
        Product editingProduct = optionalProduct.get();
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()) {
            return new Result("Bunday kategoriya mavjud emas!", false);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if(!optionalAttachment.isPresent())
            return new Result("Bunday rasm mavjud emas", false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if(!optionalMeasurement.isPresent())
            return new Result("Bunday o'lchov birligi mavjud emas!", false);
        editingProduct.setName(productDto.getName());
        editingProduct.setPhoto(optionalAttachment.get());
        editingProduct.setMeasurement(optionalMeasurement.get());
        editingProduct.setCategory(optionalCategory.get());
        editingProduct.setActive(productDto.isActive());
        productRepository.save(editingProduct);
        return new Result("Product o'zgardi!", true);
    }
}
