package io.adana.infinite.user;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sakura
 * @version 1.0
 * @description the instruction：
 * <p>
 * the  launcher of the module user.
 * </p>
 * @date 2020/10/30 16:49
 */
@SpringBootApplication
@MapperScan("io.adana.infinite.user.dao")
@Slf4j
public class InfiniteUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfiniteUserApplication.class, args);
        log.info("************************************************************");
        log.info("*                                                          *");
        log.info("*                 User Application starting                *");
        log.info("*                                                          *");
        log.info("************************************************************");
    }
}
