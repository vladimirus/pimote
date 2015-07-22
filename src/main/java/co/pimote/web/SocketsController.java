package co.pimote.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import co.pimote.model.Socket;
import co.pimote.service.SocketManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/")
public class SocketsController {
    @Autowired
    private SocketManager socketManager;

    @RequestMapping(method = GET)
    public Collection<Socket> sockets() {
        return socketManager.get();
    }
}
