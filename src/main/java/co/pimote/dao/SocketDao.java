package co.pimote.dao;

import co.pimote.model.Socket;

import java.util.Collection;
import java.util.Optional;

public interface SocketDao {
    Collection<Socket> get();
    Optional<Socket> get(Integer id);
    Optional<Socket> add(Socket socket);
    Boolean delete(Integer id);
}