package top.jplayer.quickpay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.annotation.WebFilter;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@ServletComponentScan
@Slf4j
public class QuickpayApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickpayApplication.class, args);
        log.error("-----------");
    }

}
