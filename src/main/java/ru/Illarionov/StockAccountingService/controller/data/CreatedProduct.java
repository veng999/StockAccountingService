package ru.Illarionov.StockAccountingService.controller.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@RequiredArgsConstructor
public class CreatedProduct {

    private String name;
    private String description;
    @JsonProperty(value="create_date")
    private Date createDate;
    @JsonProperty(value="place_storage")
    private Integer placeStorage;
    private boolean reserved;
}

