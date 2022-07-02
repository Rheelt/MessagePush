import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rhee
 * @date 2022/7/2 9:57 PM
 */

@SpringBootApplication
public class MessagePushApplication {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(MessagePushApplication.class, args);
    }
}
