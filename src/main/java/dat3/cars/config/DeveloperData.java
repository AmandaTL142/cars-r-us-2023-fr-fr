package dat3.cars.config;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repositories.CarRepository;
import dat3.cars.repositories.MemberRepository;
import dat3.cars.repositories.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class DeveloperData implements CommandLineRunner {

  CarRepository carRepository;
  MemberRepository memberRepository;
  ReservationRepository reservationRepository;

  public DeveloperData(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
    this.carRepository = carRepository;
    this.memberRepository = memberRepository;
    this.reservationRepository = reservationRepository;
  }

  void makeTestData() {
    Member m1 = new Member("member1", "test1", "m1@a.dk","xxx", "yyy", "Lyngbyvej 1", "Lyngby", "2800");
    Member m2 = new Member("member2", "test2", "m2@a.dk","yyy", "zzz", "Odensevej 2", "Odense", "5000");
    memberRepository.saveAll(List.of(m1, m2));
    memberRepository.save(new Member("kurt-w", "test12", "kw@a.dk","Kurt", "Wonnegut", "Lyngbyvej 34", "Lyngby", "2800"));
    memberRepository.save(new Member("hanne-w", "test12","hw@a.dk", "Hanne", "Wonnegut", "Lyngbyvej 34", "Lyngby", "2800"));
    testTheSimpleTypes();

    Car car1 = Car.builder().brand("Volvo").model("V70").pricePrDay(500).bestDiscount(10).build();
    Car car2 = Car.builder().brand("Tesla").model("X").pricePrDay(1000).bestDiscount(0).build();
    carRepository.saveAll(List.of(car1, car2));
    createCars();

    //Create a default reservation
    Reservation r = new Reservation(m1,car1, LocalDate.of(2023,12,12));
    reservationRepository.save(r);

    //Create test reservations

    //m1 lejer c2, m1 har nu lejet to forskellige biler
    Reservation r1 = new Reservation(m1,car2, LocalDate.of(2023,12,12));
    reservationRepository.save(r);

    //m1 kan leje car1 på flere dage
    Reservation r2 = new Reservation(m1,car1, LocalDate.of(2023,11,02));
    reservationRepository.save(r);

    //car1 lejes af m2, car1 er nu blevet lejet af to forskellige members
    Reservation r3 = new Reservation(m2,car1, LocalDate.of(2023,9,01));
    reservationRepository.save(r);



  }

  private void createCars(){
    //Obviously the cars below are created by chat-GPT :-)
    List<Car> newCars = new ArrayList<>(Arrays.asList(
            Car.builder().brand("Suzuki").model("Swift").pricePrDay(350).bestDiscount(6).build(),
            Car.builder().brand("Kia").model("Optima").pricePrDay(450).bestDiscount(18).build(),
            Car.builder().brand("WW").model("Wagon").pricePrDay(400).bestDiscount(20).build(),
            Car.builder().brand("Volvo").model("S80").pricePrDay(600).bestDiscount(12).build(),
            Car.builder().brand("Suzuki").model("SX4").pricePrDay(400).bestDiscount(16).build(),
            Car.builder().brand("Suzuki").model("SX4").pricePrDay(400).bestDiscount(16).build(),
            Car.builder().brand("Suzuki").model("SX4").pricePrDay(400).bestDiscount(16).build(),
            Car.builder().brand("Kia").model("Sorento").pricePrDay(500).bestDiscount(22).build(),
            Car.builder().brand("WW").model("Pickup").pricePrDay(450).bestDiscount(28).build(),
            Car.builder().brand("Volvo").model("V60").pricePrDay(700).bestDiscount(15).build(),
            Car.builder().brand("Suzuki").model("Grand Vitara").pricePrDay(450).bestDiscount(12).build(),
            Car.builder().brand("Kia").model("Sportage").pricePrDay(500).bestDiscount(20).build(),
            Car.builder().brand("WW").model("SUV").pricePrDay(400).bestDiscount(18).build(),
            Car.builder().brand("Volvo").model("XC90").pricePrDay(800).bestDiscount(25).build(),
            Car.builder().brand("Volvo").model("XC90").pricePrDay(800).bestDiscount(25).build(),
            Car.builder().brand("Volvo").model("XC90").pricePrDay(800).bestDiscount(25).build(),
            Car.builder().brand("Suzuki").model("Baleno").pricePrDay(450).bestDiscount(15).build(),
            Car.builder().brand("Kia").model("Stinger").pricePrDay(600).bestDiscount(12).build(),
            Car.builder().brand("WW").model("Sedan").pricePrDay(400).bestDiscount(20).build(),
            Car.builder().brand("Volvo").model("XC40").pricePrDay(700).bestDiscount(30).build(),
            Car.builder().brand("Volvo").model("XC40").pricePrDay(700).bestDiscount(30).build(),
            Car.builder().brand("Volvo").model("XC40").pricePrDay(700).bestDiscount(30).build(),
            Car.builder().brand("Suzuki").model("Ignis").pricePrDay(400).bestDiscount(14).build(),
            Car.builder().brand("Kia").model("Rio").pricePrDay(450).bestDiscount(12).build(),
            Car.builder().brand("WW").model("Hatchback").pricePrDay(450).bestDiscount(16).build()
    ));
    carRepository.saveAll(newCars);
  }

  private void testTheSimpleTypes(){
    //You can remove the following when we get to week2 if you like, they were only include to demonstrate
    //collections of basic type
    /*
    Member demoMember = new Member("demo",  "test12","demo@a.dk", "Demo", "Wonnegut", "Lyngbyvej 34", "Lyngby", "2800");
    demoMember.setFavoriteCarColors(new ArrayList<>(Arrays.asList("blue","silver","black")));
    Map<String,String> phoneNumbers = new HashMap<>();
    phoneNumbers.put("private","12345");
    phoneNumbers.put("work","54321");
    demoMember.setPhones(phoneNumbers);
    memberRepository.save(demoMember);
     */
  }

  @Override
  public void run(String... args) throws Exception {
    makeTestData();
  }
}

