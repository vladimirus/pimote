package co.pimote;

import co.pimote.dao.DummyGpioProvider;
import co.pimote.model.Socket;
import co.pimote.web.deserializer.SocketJsonDeserializer;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.impl.GpioControllerImpl;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

@Configuration
public class PimoteConfig extends WebMvcConfigurerAdapter {
    private Logger log = Logger.getLogger(PimoteConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webContentInterceptor());
    }

    @Bean
    public WebContentInterceptor webContentInterceptor() {
        WebContentInterceptor interceptor = new WebContentInterceptor();
        interceptor.setCacheSeconds(0);
        interceptor.setUseExpiresHeader(true);
        interceptor.setUseCacheControlHeader(true);
        interceptor.setUseCacheControlNoStore(true);
        return interceptor;
    }

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.deserializerByType(Socket.class, socketJsonDeserializer());
        return builder;
    }

    @Bean
    public SocketJsonDeserializer socketJsonDeserializer() {
        return new SocketJsonDeserializer();
    }

    @Bean
    public GpioController gpioController() {
        GpioController gpioController;
        try {
            gpioController = GpioFactory.getInstance();
        } catch (Throwable t) {
            log.error("Cannot create GpioController... Not Raspberry PI? Creating a dummy one instead", t);
            gpioController = new GpioControllerImpl(new DummyGpioProvider());
        }
        return gpioController;
    }
}
