package co.pimote.service;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import co.pimote.model.Socket;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DefaultManager implements SocketManager {
    private final Collection<Socket> sockets;

    public DefaultManager() {
        this.sockets = range(1, 5)
                .mapToObj(i -> Socket.builder().id(i).build())
                .collect(toList());
    }

    @Override
    public Collection<Socket> get() {
        return sockets;
    }

    @Override
    public Optional<Socket> get(Integer id) {
        return sockets.stream()
                .filter(socket -> socket.getId() == id)
                .findFirst();
    }
}
