package co.pimote.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/*
 * 0011 and 1011 all ON and OFF"
 * 1111 and 0111 socket 1
 * 1110 and 0110 socket 2
 * 1101 and 0101 socket 3
 * 1100 and 0100 socket 4
 */

@RunWith(MockitoJUnitRunner.class)
public class SocketToBinaryConverterTest {
    @InjectMocks
    private SocketToBinaryConverter socketToBinaryConverter;

    @Test
    public void shouldConvertToTrue() {

        // when
        Boolean actual = socketToBinaryConverter.convert(1, true, 1);

        // then
        assertThat(actual, is(true));
    }

    @Test
    public void shouldConvertToFalse() {

        // when
        Boolean actual = socketToBinaryConverter.convert(1, false, 1);

        // then
        assertThat(actual, is(false));
    }
}