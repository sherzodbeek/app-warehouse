package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto) {
       return productService.addProduct(productDto);
    }

    @GetMapping
    public List<Product> getAllProductController(){
        return productService.getAllProductsService();
    }

    @GetMapping("/getProductById/{id}")
    public Product getProductController(@PathVariable Integer id) {
        return productService.getProductService(id);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public Result deleteProductController(@PathVariable Integer id) {
        return productService.deleteProductService(id);
    }

    @PutMapping("/editProduct/{id}")
    public Result editProductController(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        return productService.editProductService(id, productDto);
    }
}
