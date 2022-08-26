package dat3.cars.repository;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

  @Autowired
  MemberRepository memberRepository;
  static String passwordUsedByAll;
  static String member1Id;
  static String member2Id;

  @BeforeAll
  public static void setUpData(@Autowired MemberRepository memberRepository){
    passwordUsedByAll = "test12";

    Member member1 = new Member("user1",passwordUsedByAll,"user1@kea.dk","Kasper","Hansen","Guldbergsgade","Kobenhavn",2200,"Yes","8,5/10");
    Member member2 = new Member("user2",passwordUsedByAll,"user2@kea.dk","Jakob","Petersen","Meuninegade","Kobenhavn",2200,"Yes","9,5/10");

    memberRepository.save(member1);
    member1Id = member1.getUsername();
    memberRepository.save(member2);
    member2Id = member2.getUsername();
  }

  @Test
  public void testFindByID(){
    Member foundMember = memberRepository.findById("user1").get();
    assertEquals("user1",foundMember.getUsername());
  }

  @Test
  public void testCityExist(){
    boolean doesTheCityExist = memberRepository.existsByCity("Kobenhavn");
    assertTrue(doesTheCityExist);
  }
}