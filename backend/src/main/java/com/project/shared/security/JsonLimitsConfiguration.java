package com.project.shared.security;
import org.springframework.context.annotation.*;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import com.fasterxml.jackson.core.StreamReadConstraints;
@Configuration(proxyBeanMethods=false)
public class JsonLimitsConfiguration {
 @Bean Jackson2ObjectMapperBuilderCustomizer jsonLimits(){return builder->builder.postConfigurer(mapper->mapper.getFactory().setStreamReadConstraints(
  StreamReadConstraints.builder().maxNestingDepth(256).maxStringLength(4096).maxNumberLength(100).build()));}
}
