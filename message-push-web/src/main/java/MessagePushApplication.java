import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rhee
 * @date 2022/7/2 10:10 PM
 */
@RestController
@EnableAutoConfiguration
public class MessagePushApplication {

    @RequestMapping("/home")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(MessagePushApplication.class, args);
    }
}
