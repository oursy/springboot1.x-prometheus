package com.example.demo;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.spring.autoconfigure.MeterRegistryCustomizer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Clock;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> commonTags() {
        return (registry) -> registry.config()
                .commonTags("application", "demo");
    }

//    @Bean
//    Clock clock() {
//
//        return Clock.systemDefaultZone();
//    }

    @Bean
    CommandLineRunner commandLineRunner(CityRepository cityRepository) {

        return args -> {
            System.out.println(Clock.systemDefaultZone());
            Stream.of("li", "lv", "yu", "lu").forEach(s -> {
                cityRepository.save(new City(s, "country"));
            });
        };

    }

}
