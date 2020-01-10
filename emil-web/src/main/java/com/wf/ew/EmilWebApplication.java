package com.wf.ew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.wf.jwtp.configuration.EnableJwtPermission;

/**
 * @author emil
 */
@EnableJwtPermission
@SpringBootApplication
public class EmilWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmilWebApplication.class, args);
    }
}
