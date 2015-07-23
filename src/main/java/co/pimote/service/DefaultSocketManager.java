package co.pimote.service;

import co.pimote.dao.SocketDao;
import co.pimote.model.Socket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DefaultSocketManager implements SocketManager {
    @Autowired
    private SocketDao socketDao;

    @Override
    public Collection<Socket> get() {
        return socketDao.get();
    }

    @Override
    public Optional<Socket> get(Integer id) {
        return socketDao.get(id);
    }

    @Override
    public Optional<Socket> update(Integer id, Socket socket) {

        Socket existing = socketDao.get(id).orElseThrow(RuntimeException::new);
        Optional<Socket> result = Optional.empty();

        if (socketDao.delete(id)) {
            result = socketDao.add(Socket.builder()
                    .id(id)
                    .active(replace(existing.getActive(), socket.getActive()))
                    .build());
        }

        return result;
    }

    private <T> T replace(T oldOne, T newOne) {
        return newOne != null ? newOne : oldOne;
    }

}
