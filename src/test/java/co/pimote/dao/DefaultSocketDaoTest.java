package co.pimote.dao;

import static co.pimote.TestFactory.aSocket;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import co.pimote.model.Socket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class DefaultSocketDaoTest {
    @InjectMocks
    private DefaultSocketDao defaultSocketDao;

    @Test
    public void shouldGet() {

        // when
        Collection<Socket> actual = defaultSocketDao.get();

        // then
        assertThat(actual, hasSize(4));
    }

    @Test
    public void shouldGetById() {

        // when
        Optional<Socket> actual = defaultSocketDao.get(1);

        // then
        assertThat(actual.isPresent(), is(true));
    }

    @Test
    public void shouldUpdate() {

        // when
        Optional<Socket> actual = defaultSocketDao.add(aSocket(1));

        // then
        assertThat(actual.isPresent(), is(true));
    }

    @Test
    public void shouldDelete() {

        // when
        Boolean actual = defaultSocketDao.delete(1);

        // then
        assertThat(actual, is(true));
    }

    @Test
    public void shouldNotDelete() {

        // when
        Boolean actual = defaultSocketDao.delete(99);

        // then
        assertThat(actual, is(false));
    }
}