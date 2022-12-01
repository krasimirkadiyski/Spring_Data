package exam.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExportLaptopDto {
    private String macAddress;
    private double cpuSpeed;
    private int ram;
    private int storage;
    private BigDecimal price;
    private String shopName;
    private String shopTownName;

    @Override
    public String toString() {
        return String.format("""
                Laptop - %s
                *Cpu speed - %.2f
                **Ram - %d
                ***Storage - %d
                ****Price - %.2f
                #Shop name - %s
                ##Town name - %s
                """,this.macAddress, this.cpuSpeed, this.ram,
                this.storage, this.price, this.shopName, this.shopTownName);
    }
}

