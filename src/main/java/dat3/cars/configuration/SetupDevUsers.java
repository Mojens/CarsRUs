package dat3.cars.configuration;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;
    String passwordUsedByAll;
    MemberRepository memberRepository;
    CarRepository carRepository;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository,
                         MemberRepository memberRepository,
                         CarRepository carRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
        passwordUsedByAll = "test12";
    }

    @Override
    public void run(ApplicationArguments args) {
        Member member1 = new Member("user4",passwordUsedByAll,"user1@kea.dk","Kasper","Hansen","Guldbergsgade","Kobenhavn",2200,"Yes","8,5/10");
        Member member2 = new Member("user5",passwordUsedByAll,"user2@kea.dk","Jakob","Petersen","Meuninegade","Kobenhavn",2200,"Yes","9,5/10");
        memberRepository.save(member1);
        memberRepository.save(member2);
        Car car1 = new Car("bmw","x5",10,5);
        Car car2 = new Car("opel","corsa",5,2.5);
        setupUserWithRoleUsers();
        carRepository.save(car1);
        carRepository.save(car2);

    }

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {
        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
    }
}
