package dat3.cars.entity;

import dat3.security.entity.UserWithRoles;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Member extends UserWithRoles {

  private String firstName;

  public Member() {}

  public Member(String user, String password, String email, String firstName) {
    super(user, password, email);
    this.firstName = firstName;
  }
}
