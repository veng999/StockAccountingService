package ru.Illarionov.StockAccountingService.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "product")
@Data
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 512)
    private String name;
    @Column(name = "description", length = 1024)
    private String description;
    @Column(name = "create_data")
    private Date createDate;
    @Column(name = "place_storage")
    private Integer placeStorage;
    private Boolean reserved;

}
