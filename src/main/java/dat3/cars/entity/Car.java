package dat3.cars.entity;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Car {

  private String brand;
  @Id
  private String model;
  private double pricePrDay;
  private double bestDiscount;

  private LocalDateTime created;
  private LocalDateTime edited;

  public Car() {
  }

  public Car(String brand, String model, double pricePrDay, double bestDiscount) {
    this.brand = brand;
    this.model = model;
    this.pricePrDay = pricePrDay;
    this.bestDiscount = bestDiscount;
  }
}
