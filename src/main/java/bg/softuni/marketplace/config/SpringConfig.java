package bg.softuni.marketplace.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableCaching
@EnableWebSecurity
@EnableAspectJAutoProxy
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringConfig {
}
