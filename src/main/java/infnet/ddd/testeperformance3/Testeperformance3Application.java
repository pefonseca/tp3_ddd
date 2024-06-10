package infnet.ddd.testeperformance3;

import infnet.ddd.testeperformance3.infra.feign.CepClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {CepClient.class})
public class Testeperformance3Application {

    public static void main(String[] args) {
        SpringApplication.run(Testeperformance3Application.class, args);
    }

}
