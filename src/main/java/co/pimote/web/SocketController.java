package co.pimote.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import co.pimote.model.Socket;
import co.pimote.service.SocketManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
}
