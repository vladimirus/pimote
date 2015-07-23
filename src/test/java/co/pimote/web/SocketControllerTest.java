package co.pimote.web;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

import co.pimote.model.Socket;
import co.pimote.service.SocketManager;
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
        given(socketManager.get(1)).willReturn(Optional.of(Socket.builder().id(1).build()));

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
}