package ru.Illarionov.StockAccountingService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Illarionov.StockAccountingService.repository.ProductsRepository;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class AddingProductToDataBaseServiceImpl implements AddingProductToDataBaseService {
    private final ProductsRepository repository;

    @Autowired
    public AddingProductToDataBaseServiceImpl(ProductsRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    @Override
    public void insertProduct(){
        repository.insertProduct((long)1, new Date(1561568357), "Эти кроссовки переосмысливают лаконичный \n" +
                " теннисный стиль. Мягкий кожаный верх с текстурными вставками из нубука создает модный многослойный силуэт.","КРОССОВКИ SUPERCOURT", 1, false);

        repository.insertProduct((long)2, new Date(1531568357), "Вдохновленные атлетическими шиповками из \n" +
                " 70-х кроссовки Haven привносят стиль ретро на современные городские улицы.","КРОССОВКИ HAVEN", 2, false);

        repository.insertProduct((long)3, new Date(1531568357), "Стильный минимализм, остающийся популярным \n" +
                "уже три десятилетия. Эти кроссовки — переиздание любимой версии Gazelle, впервые увидевшей свет в \n" +
                "1991 году. Те же материалы, цвета, текстуры и пропорции, что и у оригинала.","КРОССОВКИ GAZELLE", 3, false);

        repository.insertProduct((long)4, new Date(1531568357),"Эффектный и немного эксцентричный дизайн Yung \n" +
                "вдохновлен модой 90-х. Эта модель выполнена из нубука, искусственной замши и сетки." ,"КРОССОВКИ YUNG", 4, false);

        repository.insertProduct((long)5, new Date(1521568357), "Эффектные кроссовки Streetball сначала \n" +
                "покорили баскетбольные площадки, а затем — улицы города. Многослойный верх на этой модели выполнен \n" +
                "в духе 90-х.","КРОССОВКИ STREETBALL", 5, false);

    }
}

