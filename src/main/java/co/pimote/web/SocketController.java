package co.pimote.web;

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

@RestController
@RequestMapping("/{id}")
public class SocketController {
    @Autowired
    private SocketManager socketManager;

    @RequestMapping(method = GET)
    public Socket socket(@PathVariable("id") Integer id) {
        return socketManager
                .get(id)
                .orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = PUT)
    public Result update(@PathVariable("id") Integer id, @RequestBody Socket socket) {
        if (socket.getId() != null && !socket.getId().equals(id)) {
            throw new BadRequestException();
        }

        return Result.builder().ok(
                socketManager.update(socket(id).getId(), socket).isPresent()
        ).build();
    }

    @RequestMapping(value = "on", method = GET)
    public Result on(@PathVariable("id") Integer id) {
        return shortcut(id, true);
    }

    @RequestMapping(value = "off", method = GET)
    public Result off(@PathVariable("id") Integer id) {
        return shortcut(id, false);
    }

    private Result shortcut(Integer id, Boolean state) {
        return Result.builder().ok(
                socketManager.update(socket(id).getId(), Socket.builder().active(state).id(id).build()).isPresent()
        ).build();
    }
}
