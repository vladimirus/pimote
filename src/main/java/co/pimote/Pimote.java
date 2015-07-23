package co.pimote;

import co.pimote.model.Socket;
import co.pimote.web.deserializer.SocketJsonDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication
public class Pimote {

    public static void main(String[] args) {
        SpringApplication.run(Pimote.class, args);
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
}
