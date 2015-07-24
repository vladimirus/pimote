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

@Configuration
public class PimoteConfig {
    private Logger log = Logger.getLogger(PimoteConfig.class);

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
