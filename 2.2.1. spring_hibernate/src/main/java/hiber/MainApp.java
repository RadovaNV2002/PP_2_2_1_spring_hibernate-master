package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Harry", "Potter", "potter@hogwarts.com");
      User user2 = new User("Hermione", "Granger", "granger@hogwarts.com");
      User user3 = new User("Ron", "Weasley", "weasley@hogwarts.com");
      User user4 = new User("Remus", "Lupin", "lupin@hogwarts.com");

      Car car1 = new Car("Lightning", 2021);
      Car car2 = new Car("Nimbus", 1001);
      Car car3 = new Car("Cleanliness", 7);
      Car car4 = new Car("Comet", 290);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
         System.out.println("1. _____________________________________________");
      }

      System.out.println(userService.getUserByCar("Nimbus", 1001));
      System.out.println("2. _____________________________________________");

      try {
         User notFoundUser = userService.getUserByCar("Broom", 90);
      } catch (Exception e) {
         System.out.println("User not found");
         System.out.println("3. _____________________________________________");
      }
      context.close();
   }
}
