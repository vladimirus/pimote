package co.pimote.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import co.pimote.model.Socket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class DefaultManagerTest {
    @InjectMocks
    private DefaultManager defaultManager;

    @Test
    public void shouldGet() {

        // when
        Collection<Socket> actual = defaultManager.get();

        // then
        assertThat(actual, hasSize(4));
    }

    @Test
    public void shouldGetById() {

        // when
        Optional<Socket> actual = defaultManager.get(1);

        // then
        assertThat(actual.isPresent(), is(true));
    }

    @Test
    public void shouldUpdate() {

        // when
        Optional<Socket> actual = defaultManager.update(1, Socket.builder().id(1).active(true).build());

        assertThat(actual.isPresent(), is(true));
    }
}