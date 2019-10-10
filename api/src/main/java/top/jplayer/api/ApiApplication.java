package top.jplayer.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import top.jplayer.common.dto.BaseResult;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        new BaseResult();
        SpringApplication.run(ApiApplication.class, args);
    }

}
