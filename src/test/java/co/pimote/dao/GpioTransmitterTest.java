package co.pimote.dao;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GpioTransmitterTest {
    private GpioTransmitter gpioTransmitter;
    @Mock
    private GpioController gpio;
    @Mock
    private SocketToBinaryConverter socketToBinaryConverter;

    @Before
    public void setup() {
        GpioPinDigitalOutput out = mock(GpioPinDigitalOutput.class);
        given(gpio.provisionDigitalOutputPin(any(Pin.class), any(PinState.class))).willReturn(out);
        gpioTransmitter = new GpioTransmitter(gpio, socketToBinaryConverter);
    }

    @Test
    public void shouldTransmit() {
        // given
        given(socketToBinaryConverter.convert(anyInt(), anyBoolean(), anyInt())).willReturn(true);

        // when
        gpioTransmitter.transmit(1, true);

        // then
    }
}