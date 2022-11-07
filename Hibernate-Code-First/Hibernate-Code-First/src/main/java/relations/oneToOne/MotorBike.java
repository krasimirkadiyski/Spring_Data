package relations.oneToOne;

import javax.persistence.*;

@Entity

public class MotorBike {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    @OneToOne()
    @JoinColumn(name = "plate_id",
    referencedColumnName = "id")
    private PlateNumber plateNumber;
    public MotorBike() {}

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public PlateNumber getPlateNumber() {
        return plateNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPlateNumber(PlateNumber plateNumber) {
        this.plateNumber = plateNumber;
    }
}
