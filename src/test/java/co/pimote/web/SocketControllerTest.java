package co.pimote.web;

import static co.pimote.TestFactory.aSocket;
import static java.util.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;

import co.pimote.model.Socket;
import co.pimote.service.SocketManager;
import co.pimote.web.exception.BadRequestException;
import co.pimote.web.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class SocketControllerTest {
    @InjectMocks
    private SocketController socketController;
    @Mock
    private SocketManager socketManager;

    @Test
    public void shouldGetSocket() {
        // given
        given(socketManager.get(1)).willReturn(of(aSocket()));

        // when
        Socket actual = socketController.socket(1);

        // then
        assertThat(actual.getId(), is(1));
    }

    @Test(expected = NotFoundException.class)
    public void shouldNotGet() {
        // given
        given(socketManager.get(1)).willReturn(Optional.empty());

        // when
        Socket actual = socketController.socket(1);

        // then
        // exception
    }

    @Test(expected = BadRequestException.class)
    public void shouldGetBadRequestWhenIdsDontMatch() {

        // when
        Result actual = socketController.update(1, aSocket(2));

        // then
        // exception
    }

    @Test(expected = NotFoundException.class)
    public void shouldGetNotFoundDuringUpdate() {
        // given
        given(socketManager.get(anyInt())).willReturn(Optional.empty());

        // when
        Result actual = socketController.update(5, aSocket(5));

        // then
        // exception
    }

    @Test
    public void shouldUpdateOk() {
        // given
        Optional<Socket> socket = of(aSocket(1));
        given(socketManager.get(anyInt())).willReturn(socket);
        given(socketManager.update(anyInt(), any(Socket.class))).willReturn(socket);

        // when
        Result actual = socketController.update(1, aSocket(1));

        // then
        assertThat(actual.getOk(), is(true));
    }

    @Test
    public void shouldSwitchOn() {
        // given
        Optional<Socket> socket = of(aSocket(1));
        given(socketManager.get(anyInt())).willReturn(socket);
        given(socketManager.update(anyInt(), any(Socket.class))).willReturn(socket);

        // when
        Result actual = socketController.on(1);

        // then
        assertThat(actual.getOk(), is(true));
    }

    @Test
    public void shouldSwitchOff() {
        // given
        Optional<Socket> socket = of(aSocket(1));
        given(socketManager.get(anyInt())).willReturn(socket);
        given(socketManager.update(anyInt(), any(Socket.class))).willReturn(socket);

        // when
        Result actual = socketController.off(1);

        // then
        assertThat(actual.getOk(), is(true));
    }
}