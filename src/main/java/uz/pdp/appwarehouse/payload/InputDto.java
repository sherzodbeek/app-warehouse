package uz.pdp.appwarehouse.payload;


import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
@Data
public class InputDto {
    private Timestamp date;
    private Integer warehouseId;
    private Integer supplierId;
    private Integer currencyId;
    private String factureNumber;
    List<InputProductDto> inputProducts;
}
