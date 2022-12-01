package exam.model.dtos;

import exam.util.WarrantyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportLaptopDto {

    @NotNull
    @Size(min = 9)
    private String macAddress;
    @NotNull
    @DecimalMin("0.01")
    private Double cpuSpeed;
    @NotNull
    @Min(8)
    @Max(128)
    private Integer ram;
    @Min(128)
    @Max(1024)
    @NotNull
    private Integer storage;
    @NotNull
    @Size(min = 10)
    private String description;
    @NotNull
    @DecimalMin("0.1")
    private BigDecimal price;
    @NotNull
    private WarrantyType warrantyType;
    private ShopJsonDto shop;
}
