package co.pimote.dao;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;

import co.pimote.model.Socket;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class DefaultSocketDao implements SocketDao{
    private Collection<Socket> sockets;

    public DefaultSocketDao() {
        this.sockets = range(1, 5)
                .mapToObj(i -> Socket.builder().id(i).active(false).build())
                .collect(toSet());
    }

    @Override
    public Collection<Socket> get() {
        return sockets;
    }

    @Override
    public Optional<Socket> get(Integer id) {
        return sockets.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Socket> add(Socket socket) {
        sockets.add(socket);
        return Optional.of(socket);
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
