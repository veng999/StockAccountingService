package ru.Illarionov.StockAccountingService.service;

import ru.Illarionov.StockAccountingService.controller.data.CreatedProduct;
import ru.Illarionov.StockAccountingService.exceptions.RecordNotFoundException;
import ru.Illarionov.StockAccountingService.model.Product;
import java.util.List;

public interface MainService {

    List <Product> findAll();
    Product addProduct (CreatedProduct createdProduct);
    Long removeProduct (Long id) throws RecordNotFoundException;
    Product getProductByName(String name);
    Product findById(Long id);
    Product updateProduct(Long id, CreatedProduct createdProduct);
}
