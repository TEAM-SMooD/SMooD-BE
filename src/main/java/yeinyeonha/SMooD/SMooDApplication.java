package yeinyeonha.SMooD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/aws.properties")
@PropertySource("classpath:/kakao.properties")
public class SMooDApplication {

	public static void main(String[] args) {
		SpringApplication.run(SMooDApplication.class, args);
	}

}