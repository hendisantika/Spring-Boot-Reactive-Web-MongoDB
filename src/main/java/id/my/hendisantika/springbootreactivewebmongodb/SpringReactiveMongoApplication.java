package id.my.hendisantika.springbootreactivewebmongodb;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
public class SpringReactiveMongoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(SpringReactiveMongoApplication.class, args);

        val env = app.getEnvironment();
        System.out.println(env.getProperty("spring.data.mongodb.uri"));
    }

}
