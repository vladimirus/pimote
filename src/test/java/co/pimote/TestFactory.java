package co.pimote;

import co.pimote.model.Socket;

public class TestFactory {

    private TestFactory() {
        // do not instanciate
    }

    public static Socket aSocket() {
        return aSocket(1, true);
    }

    public static Socket aSocket(Integer id) {
        return aSocket(id, true);
    }

    public static Socket aSocket(Integer id, Boolean active) {
        return Socket.builder()
                .active(active)
                .id(id)
                .build();
    }
}
