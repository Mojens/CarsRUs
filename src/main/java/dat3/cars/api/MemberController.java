package dat3.cars.api;
import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {

  MemberService memberService;

  public MemberController(MemberService memberService){
    this.memberService = memberService;
  }

  //Security ADMIN
  @GetMapping
  List<MemberResponse> getMembers(){
    return memberService.findMembers();
  }

  //Security ADMIN
  @GetMapping(path = "/{username}")
  MemberResponse getMemberById(@PathVariable String username) throws Exception {
    return memberService.findMemberByUsername(username);}

  //Security --> USER
  //@PostMapping // same as above when you are using @RestController
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  MemberResponse addMember(@RequestBody MemberRequest body){
    return memberService.addMember(body);
  }
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.TEXT_HTML_VALUE)
  public String addMember2(@RequestBody MemberRequest body) {
    memberService.addMember(body);
    return  body.getUsername() + " was created";
  }

  //Security USER / ADMIN ???
  @PutMapping("/{username}")
  ResponseEntity<Boolean> editMember(@RequestBody MemberRequest body, @PathVariable String username){
    memberService.editMember(body,username);
    return new ResponseEntity<>(true, HttpStatus.OK);
  }

  //Security ADMIN
  @PatchMapping("/ranking/{username}/{value}")
  void setRankingForUser(@PathVariable String username, @PathVariable int value) {
    memberService.setRankingForUser(username,value);
  }

  // Security ADMIN / USER
  @DeleteMapping("/{username}")
  void deleteMemberByUsername(@PathVariable String username) {
    memberService.deleteByUsername(username);
  }



}
