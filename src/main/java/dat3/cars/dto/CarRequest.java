package dat3.cars.dto;

import dat3.cars.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class CarRequest {

  int id;
  String brand;
  String model;
  double pricePrDay;
  double bestDiscount;
  @CreationTimestamp
  LocalDateTime created;

  @UpdateTimestamp
  LocalDateTime edited;

  public static Car getCarEntity(CarRequest carRequest){
    return new Car(carRequest.id,carRequest.brand,carRequest.model,carRequest.pricePrDay,carRequest.bestDiscount,carRequest.created,carRequest.edited);
  }

  public CarRequest(Car c){
    this.id = c.getId();
    this.brand = c.getBrand();
    this.model = c.getModel();
    this.pricePrDay = c.getPricePrDay();
    this.bestDiscount = c.getBestDiscount();
    this.created = c.getCreated();
    this.edited = c.getEdited();
  }
}
