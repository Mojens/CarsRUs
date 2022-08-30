package dat3.cars.api;

import dat3.cars.entity.Member;
import dat3.cars.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {
  @Autowired  //Deliberately added via Autowired, remove this endpoint when you know why it's bad
  MemberRepository memberRepository;
  @GetMapping("/bad")
  public List<Member> getMembersBad(){
    return memberRepository.findAll();
  }


}
