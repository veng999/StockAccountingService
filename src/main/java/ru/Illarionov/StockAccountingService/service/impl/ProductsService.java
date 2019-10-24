package ru.Illarionov.StockAccountingService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.Illarionov.StockAccountingService.controller.data.CreatedProduct;
import ru.Illarionov.StockAccountingService.exceptions.RecordNotFoundException;
import ru.Illarionov.StockAccountingService.model.Product;
import ru.Illarionov.StockAccountingService.repository.ProductsRepository;
import ru.Illarionov.StockAccountingService.service.MainService;
import java.util.List;
import java.util.Optional;


@Service
public class ProductsService implements MainService {
    private final ProductsRepository repository;

    @Autowired
    public ProductsService(ProductsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Product addProduct(CreatedProduct createdProduct) {
        Product product = new Product();
        product.setName(createdProduct.getName());
        product.setDescription(createdProduct.getDescription());
        product.setCreateDate(createdProduct.getCreateDate());
        product.setPlaceStorage(createdProduct.getPlaceStorage());
        product.setReserved(createdProduct.isReserved());
        Product saveProduct = repository.save(product);
        Long id = saveProduct.getId();
        return repository.findProductById(id);
    }

    @Override
    @Transactional
    public Long removeProduct(Long id) throws RecordNotFoundException {
        Optional<Product> existing = repository.findById(id);
        if (existing.isPresent()) {
             repository.deleteById(id);
            return existing.get().getId();
        } else throw new RecordNotFoundException("No such records to remove");
    }


    @Override
    public Product getProductByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Product findById(Long id) {
        return repository.findProductById(id);
    }

    @Override
    public Product updateProduct(Long id, CreatedProduct createdProduct) {
        Product existingProduct = repository.findProductById(id);
        if (existingProduct != null) {
            existingProduct.setName(createdProduct.getName());
            existingProduct.setDescription(createdProduct.getDescription());
            existingProduct.setCreateDate(createdProduct.getCreateDate());
            existingProduct.setPlaceStorage(createdProduct.getPlaceStorage());
            existingProduct.setReserved(createdProduct.isReserved());
            Product updatedProduct = repository.save(existingProduct);
            Long idUpdatedProduct = updatedProduct.getId();
            return repository.findProductById(idUpdatedProduct);
        } else return null;
    }
}




