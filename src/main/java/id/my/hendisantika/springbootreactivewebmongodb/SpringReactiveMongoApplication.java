package id.my.hendisantika.springbootreactivewebmongodb;

import id.my.hendisantika.springbootreactivewebmongodb.model.Car;
import id.my.hendisantika.springbootreactivewebmongodb.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-Boot-Reactive-Web-MongoDB
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/5/24
 * Time: 08:59
 * To change this template use File | Settings | File Templates.
 */
@SpringBootApplication
@Slf4j
public class SpringReactiveMongoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(SpringReactiveMongoApplication.class, args);

        val env = app.getEnvironment();
        log.info(env.getProperty("spring.data.mongodb.uri"));
    }

    @Bean
    public CommandLineRunner initData(CarRepository carRepository) {
        carRepository.deleteAll();
        return (args ->
                carRepository.saveAll(
                        List.of(
                                new Car("Civic", "Honda"),
                                new Car("Expander", "Mithsubishi"),
                                new Car("Livina", "Nissan"),
                                new Car("Innova", "Toyoya"),
                                new Car("Avanza", "Toyoya")
                        )));
    }


}
