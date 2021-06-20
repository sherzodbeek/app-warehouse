package uz.pdp.appwarehouse.payload;


import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OutputDto {
    private Timestamp date;
    private Integer warehouseId;
    private Integer currencyId;
    private String factureNumber;
    private Integer clientId;
    List<OutputProductDto> outputProducts;

}
