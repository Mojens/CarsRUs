package dat3.cars.service;

import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Member;
import dat3.cars.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
  MemberRepository memberRepository;
  public MemberService(MemberRepository memberRepository){
    this.memberRepository = memberRepository;
  }

  public List<MemberResponse> findMembers() {
    List<Member> members = memberRepository.findAll();
    List<MemberResponse> response = members.stream().map(member -> new MemberResponse(member,false)).toList();
    return response;
  }
}
