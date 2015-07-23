package co.pimote.service;

import co.pimote.model.Socket;

import java.util.Collection;
import java.util.Optional;

public interface SocketManager {
    Collection<Socket> get();
    Optional<Socket> get(Integer id);
    Optional<Socket> update(Integer id, Socket socket);
}
