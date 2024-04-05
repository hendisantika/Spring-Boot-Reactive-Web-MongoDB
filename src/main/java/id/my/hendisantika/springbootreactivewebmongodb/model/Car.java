package id.my.hendisantika.springbootreactivewebmongodb.model;

import com.mongodb.lang.NonNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-Boot-Reactive-Web-MongoDB
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/5/24
 * Time: 09:00
 * To change this template use File | Settings | File Templates.
 */
@Data
@Document
@RequiredArgsConstructor
public class Car {
    private @Id String id = null;
    private @NonNull String brand;
    private @NonNull String model;

}
