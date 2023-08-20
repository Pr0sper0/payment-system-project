package org.val;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentApplicationRunner {

    public static void main(String[] args) {
        var context = SpringApplication.run(PaymentApplicationRunner.class, args);
    }
}
