package uz.pdp.appwarehouse.payload;

import lombok.Data;

import java.sql.Date;

@Data
public class OutputProductDto {
    private Integer id;
    private Integer productId;
    private Double amount;
    private Double price;
}
