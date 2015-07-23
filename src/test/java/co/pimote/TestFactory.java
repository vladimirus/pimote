package co.pimote;

import co.pimote.model.Socket;

public class TestFactory {

    private TestFactory() {
        // do not instanciate
    }

    public static Socket aSocket() {
        return aSocket(1);
    }

    public static Socket aSocket(Integer id) {
        return Socket.builder()
                .active(true)
                .id(id)
                .build();
    }
}
