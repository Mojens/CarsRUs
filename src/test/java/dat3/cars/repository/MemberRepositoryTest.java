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
    Member m1 = new Member("member1", passwordUsedByAll, "memb1@a.dk", "Kurt", "Wonnegut", "Lyngbyvej 2", "Lynbby", "2800");
    Member m2 = new Member("member2", passwordUsedByAll, "memb1@a.d", "Kut", "Wonnegt", "Lygbyvej 2", "Lybby", "200");

    memberRepository.save(m1);
    member1Id = m1.getUsername();
    memberRepository.save(m2);
    member2Id = m2.getUsername();
  }
}