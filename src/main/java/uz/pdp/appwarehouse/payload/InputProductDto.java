package uz.pdp.appwarehouse.payload;

import lombok.Data;

import java.sql.Date;

@Data
public class InputProductDto {
    private Integer id;
    private Integer productId;
    private Double amount;
    private Double price;
    private Date expireDate;
}
