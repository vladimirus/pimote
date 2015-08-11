package co.pimote.dao;

import static com.pi4j.io.gpio.PinState.LOW;
import static com.pi4j.io.gpio.RaspiPin.GPIO_00;
import static com.pi4j.io.gpio.RaspiPin.GPIO_02;
import static com.pi4j.io.gpio.RaspiPin.GPIO_03;
import static com.pi4j.io.gpio.RaspiPin.GPIO_04;
import static com.pi4j.io.gpio.RaspiPin.GPIO_05;
import static com.pi4j.io.gpio.RaspiPin.GPIO_06;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.lang.Thread.sleep;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * To clear the socket programming, press the green button for 5 seconds or more until the red light flashes slowly.
 * The socket is now in its learning mode and listening for a control code to be sent.
 * It will accept the following code pairs:
 *
 * 0011 and 1011 all ON and OFF"
 * 1111 and 0111 socket 1
 * 1110 and 0110 socket 2
 * 1101 and 0101 socket 3
 * 1100 and 0100 socket 4
 */
@Component
public class GpioTransmitter implements Transmitter {
    private Logger log = Logger.getLogger(GpioTransmitter.class);
    private SocketToBinaryConverter converter;

    private Map<Integer, GpioPinDigitalOutput> pins;
    private GpioPinDigitalOutput modulator;
    private GpioPinDigitalOutput askFsk;

    @Autowired
    public GpioTransmitter(GpioController gpio, SocketToBinaryConverter converter) {
        this.converter = converter;
        pins = new HashMap<>(4);
        pins.put(1, gpio.provisionDigitalOutputPin(GPIO_02, LOW)); //D3
        pins.put(2, gpio.provisionDigitalOutputPin(GPIO_04, LOW)); //D2
        pins.put(3, gpio.provisionDigitalOutputPin(GPIO_03, LOW)); //D1
        pins.put(4, gpio.provisionDigitalOutputPin(GPIO_00, LOW)); //D0

        askFsk = gpio.provisionDigitalOutputPin(GPIO_05, LOW);
        modulator = gpio.provisionDigitalOutputPin(GPIO_06, LOW);

        askFsk.low();
        modulator.low();
    }

    @SneakyThrows
    @Override
    public synchronized void transmit(Integer socket, Boolean state) {
        log.info("socket " + socket + ": changing to " + state);

        pins.entrySet().stream().forEach(e -> e.getValue().setState(converter.convert(socket, state, e.getKey())));

        sleep(500); // let it settle, encoder requires this

        log.info("socket " + socket + ": changing to " +
                pins.entrySet().stream()
                        .filter(e -> e.getValue() != null && e.getValue().getState() != null)
                        .map(e -> valueOf(e.getValue().getState().getValue()))
                        .reduce("", (a, b) -> format("%s %s", a, b)));

        modulator.high();

        sleep(500); // keep enabled for a period

        modulator.low();
    }
}
