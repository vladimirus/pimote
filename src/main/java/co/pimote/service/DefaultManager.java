package co.pimote.service;

import static java.util.Optional.of;
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
                .mapToObj(i -> Socket.builder().id(i).active(false).build())
                .collect(toList());
    }

    @Override
    public Collection<Socket> get() {
        return sockets;
    }

    @Override
    public Optional<Socket> get(Integer id) {
        return sockets.stream()
                .filter(socket -> socket.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Socket> update(Integer id, Socket socket) {
        Socket existing = sockets.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        Optional<Socket> result = Optional.empty();

        if (sockets.removeIf(s -> s.getId().equals(id))) {
            Socket newSocket = Socket.builder().id(id)
                    .active(socket.getActive() != null ? socket.getActive() : existing.getActive())
                    .build();
            sockets.add(newSocket);
            result = of(newSocket);
        }

        return result;
    }

}
