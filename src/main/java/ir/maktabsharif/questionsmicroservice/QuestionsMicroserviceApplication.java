package ir.maktabsharif.questionsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class QuestionsMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionsMicroserviceApplication.class, args);
    }

}
