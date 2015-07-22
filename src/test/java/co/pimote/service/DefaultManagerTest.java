package co.pimote.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import co.pimote.model.Socket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

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
}