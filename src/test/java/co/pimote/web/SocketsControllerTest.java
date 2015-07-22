package co.pimote.web;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;

import co.pimote.model.Socket;
import co.pimote.service.SocketManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class SocketsControllerTest {
    @InjectMocks
    private SocketsController socketsController;
    @Mock
    private SocketManager socketManager;

    @Test
    public void sockets() {
        // given
        given(socketManager.get()).willReturn(asList(
                Socket.builder().id(1).active(false).build(),
                Socket.builder().id(2).active(true).build(),
                Socket.builder().id(3).active(true).build()
        ));

        // when
        Collection<Socket> actual = socketsController.sockets();

        // then
        assertThat(actual, hasSize(3));
    }
}