package co.pimote.web;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import co.pimote.model.Socket;
import co.pimote.service.SocketManager;
import co.pimote.web.exception.BadRequestException;
import co.pimote.web.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/{id}")
public class SocketController {
    @Autowired
    private SocketManager socketManager;

    public Socket socket(@PathVariable("id") Integer id) {
        return socketManager
                .get(id)
                .orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = PUT)
    public Socket update(@PathVariable("id") Integer id, @RequestBody Socket socket) {
        if (socket.getId() != null && !socket.getId().equals(id)) {
            throw new BadRequestException();
        }

        return socketManager.update(socket(id).getId(), socket).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = GET)
    public Collection<Socket> sockets(@PathVariable("id") Integer[] ids) {
        return stream(ids)
                .map(socketManager::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }

    @RequestMapping(value = "on", method = GET)
    public Collection<Socket> on(@PathVariable("id") Integer[] ids) {
        return shortcut(ids, true);
    }

    @RequestMapping(value = "off", method = GET)
    public Collection<Socket> off(@PathVariable("id") Integer[] ids) {
        return shortcut(ids, false);
    }

    private Collection<Socket> shortcut(Integer ids[], Boolean state) {
        return sockets(ids)
                .stream()
                .map(s -> socketManager.update(s.getId(), Socket.builder().active(state).id(s.getId()).build()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }
}
