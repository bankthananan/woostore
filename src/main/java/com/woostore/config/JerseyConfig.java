package com.woostore.config;

import com.woostore.controller.ProductController;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("com.woostore.controller");
        register(MultiPartFeature.class);
    }
}
