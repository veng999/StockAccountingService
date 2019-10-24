package ru.Illarionov.StockAccountingService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.Illarionov.StockAccountingService.controller.data.CreatedProduct;
import ru.Illarionov.StockAccountingService.exceptions.RecordNotFoundException;
import ru.Illarionov.StockAccountingService.model.Product;
import ru.Illarionov.StockAccountingService.service.impl.ProductsService;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@ControllerAdvice
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductsService service;

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public Product findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Product> list(){
        return service.findAll();
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public Long remove (@PathVariable("id") Long id){
        try {
            return service.removeProduct(id);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }
        return service.findById(id).getId();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public Product update(@PathVariable ("id")Long id, @RequestBody CreatedProduct createdProduct){
        return service.updateProduct(id, createdProduct);

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public Product save (@RequestBody CreatedProduct createdProduct){
         return service.addProduct(createdProduct);

    }

}





