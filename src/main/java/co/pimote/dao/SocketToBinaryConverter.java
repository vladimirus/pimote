package co.pimote.dao;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SocketToBinaryConverter {
    private Map<String, String> sockets;

    public SocketToBinaryConverter() {
        sockets = new HashMap<>(5);
        sockets.put(0 + "" + true, "0011");
        sockets.put(0 + "" + false, "1011");
        sockets.put(1 + "" + true, "1111");
        sockets.put(1 + "" + false, "0111");
        sockets.put(2 + "" + true, "1110");
        sockets.put(2 + "" + false, "0110");
        sockets.put(3 + "" + true, "1101");
        sockets.put(3 + "" + false, "0101");
        sockets.put(4 + "" + true, "1100");
        sockets.put(5 + "" + false, "0100");
    }

    public Boolean convert(Integer socket, Boolean state, Integer pinNo) {
        return sockets.get(socket + "" + state).charAt(pinNo - 1) == '1';
    }
}
