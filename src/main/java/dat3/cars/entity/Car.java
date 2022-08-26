package dat3.cars.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Car {

  private String brand;
  @Id
  private String model;
  private double pricePrDay;
  private double bestDiscount;

  public Car() {
  }

  public Car(String brand, String model, double pricePrDay, double bestDiscount) {
    this.brand = brand;
    this.model = model;
    this.pricePrDay = pricePrDay;
    this.bestDiscount = bestDiscount;
  }
}
