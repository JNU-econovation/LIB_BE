package com.lib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing//자동으로 값이 들어감
public class SpringbootDeveloperApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDeveloperApplication.class,args);
    }

}
