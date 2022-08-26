package dat3.cars.entity;

import dat3.security.entity.UserWithRoles;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Member extends UserWithRoles {

  private String firstName;

  private String lastName;

  private String street;

  private String city;

  private int zip;

  private String approved;

  private String ranking;

  //Der skal laves ikke en created og update date, da den arver

  public Member() {}

  public Member(String user, String password, String email, String firstName, String lastName, String street, String city, int zip, String approved, String ranking) {
    super(user, password, email);
    this.firstName = firstName;
    this.lastName = lastName;
    this.street = street;
    this.city = city;
    this.zip = zip;
    this.approved = approved;
    this.ranking = ranking;
  }

  @Override
  public String toString() {
    return "Member{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", street='" + street + '\'' +
        ", city='" + city + '\'' +
        ", zip=" + zip +
        ", approved='" + approved + '\'' +
        ", ranking='" + ranking + '\'' +
        '}';
  }
}
