package co.pimote.service;

import static co.pimote.TestFactory.aSocket;
import static java.util.Collections.singletonList;
import static java.util.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;

import co.pimote.dao.SocketDao;
import co.pimote.dao.Transmitter;
import co.pimote.model.Socket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class DefaultSocketManagerTest {
    @InjectMocks
    private DefaultSocketManager defaultSocketManager;
    @Mock
    private SocketDao socketDao;
    @Mock
    private Transmitter transmitter;

    @Test
    public void shouldGet() {
        // given
        given(socketDao.get()).willReturn(singletonList(aSocket()));

        // when
        Collection<Socket> actual = defaultSocketManager.get();

        // then
        verify(socketDao).get();
        assertThat(actual, hasSize(1));
    }

    @Test
    public void shouldGetById() {
        // given
        given(socketDao.get(1)).willReturn(of(aSocket(1)));

        // when
        Optional<Socket> actual = defaultSocketManager.get(1);

        // then
        verify(socketDao).get(1);
        assertThat(actual.isPresent(), is(true));
    }

    @Test
    public void shouldUpdate() {
        // given
        given(socketDao.get(1)).willReturn(of(aSocket(1)));
        given(socketDao.delete(1)).willReturn(true);
        given(socketDao.add(isA(Socket.class))).willReturn(of(aSocket(1)));

        // when
        Optional<Socket> actual = defaultSocketManager.update(1, aSocket());

        // then
        assertThat(actual.isPresent(), is(true));
        verify(transmitter).transmit(1, true);
    }

    @Test
    public void shouldDelete() {
        // given
        given(socketDao.delete(1)).willReturn(true);

        // when
        Boolean actual = defaultSocketManager.delete(1);

        // then
        assertThat(actual, is(true));
    }
}