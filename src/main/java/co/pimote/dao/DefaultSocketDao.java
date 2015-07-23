package co.pimote.dao;

import co.pimote.model.Socket;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class DefaultSocketDao implements SocketDao{

    @Override
    public Collection<Socket> get() {
        return null;
    }

    @Override
    public Optional<Socket> get(Integer id) {
        return null;
    }

    @Override
    public Optional<Socket> update(Socket socket) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
