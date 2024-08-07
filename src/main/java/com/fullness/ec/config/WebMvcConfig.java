package com.fullness.ec.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fullness.ec.security.CustomExceptionHundler;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addArgumentResolvers(@SuppressWarnings("null") List<HandlerMethodArgumentResolver> resolvers) {
		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
		resolver.setOneIndexedParameters(true);
		resolvers.add(resolver);
	}

	@Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        //resolvers.add(new CustomExceptionHundler());
    }
}
