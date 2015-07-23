package co.pimote;

import co.pimote.model.Socket;
import co.pimote.web.deserializer.SocketJsonDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class PimoteConfig {
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
}
