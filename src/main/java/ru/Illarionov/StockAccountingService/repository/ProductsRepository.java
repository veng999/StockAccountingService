package ru.Illarionov.StockAccountingService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.Illarionov.StockAccountingService.model.Product;
import java.util.Date;

public interface ProductsRepository extends JpaRepository <Product,Long> {

    @Query("select p from Product p where p.name = :name")
    Product findByName(@Param("name") String name);

    @Query("select p from Product p where p.id = :id")
    Product findProductById (@Param("id") Long id);

    @Modifying
    @Query("update Product p set p.id =:id, p.createDate =:createDate, p.description =:description,p.name =:name, p.placeStorage = :placeStorage, p.reserved =:reserved where p.id = :id")
    @Transactional
    Integer updateProduct (@Param("id") Long id, @Param("createDate") Date createDate, @Param("description") String description, @Param("name") String name, @Param("placeStorage") Integer placeStorage, @Param("reserved") Boolean reserved);

    @Modifying
    @Query(value = "insert into Product values (:id, :createDate, :description, :name, :placeStorage, :reserved)", nativeQuery = true)
    @Transactional
    void insertProduct(@Param("id") Long id, @Param("createDate") Date createDate, @Param("description") String description, @Param("name") String name, @Param("placeStorage") Integer placeStorage, @Param("reserved") Boolean reserved);

}
