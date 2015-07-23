package co.pimote.web.deserializer;

import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;
import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;

import co.pimote.model.Socket;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class SocketJsonDeserializer extends JsonDeserializer {
    @Override
    public Socket deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        if (parser.getCurrentToken() != START_OBJECT) {
            throw new IOException("invalid start marker");
        }

        Socket.SocketBuilder builder = Socket.builder();
        while (parser.nextToken() != END_OBJECT) {
            String name = parser.getCurrentName();
            parser.nextToken();
            if ("id".equals(name)) {
                int id = parser.getValueAsInt();
                if (id > 0) {
                    builder.id(id);
                }
            } else if ("active".equals(name)) {
                builder.active(parser.getValueAsBoolean());
            }
        }
        return builder.build();
    }
}
