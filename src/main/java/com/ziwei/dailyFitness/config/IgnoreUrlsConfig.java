package com.ziwei.dailyFitness.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/5/1
 * @name DailyFitnessSpringboot
 */

@Getter
@Setter
@Configuration
public class IgnoreUrlsConfig {
    public List<String> urls = new ArrayList<>();
}
