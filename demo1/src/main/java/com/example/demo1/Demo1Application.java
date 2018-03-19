package com.example.demo1;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.spring.autoconfigure.MeterRegistryCustomizer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.stream.Stream;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Demo1Application {

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> commonTags() {
        return (registry) -> registry.config()
                .commonTags("application", "demo")
                ;
    }

    @Bean
    CommandLineRunner commandLineRunner(CityRepository cityRepository) {

        return args -> {
            Stream.of("li", "lv", "yu", "lu").forEach(s -> {
                cityRepository.save(new City(s, "country"));
            });
        };

    }

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }
}
