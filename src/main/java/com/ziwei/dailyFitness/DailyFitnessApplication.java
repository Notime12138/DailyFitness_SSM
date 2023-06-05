package com.ziwei.dailyFitness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class DailyFitnessApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyFitnessApplication.class, args);
    }

}
