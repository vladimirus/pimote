package co.pimote.dao;

import static com.pi4j.io.gpio.PinState.LOW;
import static com.pi4j.io.gpio.RaspiPin.GPIO_17;
import static com.pi4j.io.gpio.RaspiPin.GPIO_22;
import static com.pi4j.io.gpio.RaspiPin.GPIO_23;
import static com.pi4j.io.gpio.RaspiPin.GPIO_24;
import static com.pi4j.io.gpio.RaspiPin.GPIO_25;
import static com.pi4j.io.gpio.RaspiPin.GPIO_27;
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
        pins.put(1, gpio.provisionDigitalOutputPin(GPIO_17, LOW));
        pins.put(2, gpio.provisionDigitalOutputPin(GPIO_22, LOW));
        pins.put(3, gpio.provisionDigitalOutputPin(GPIO_23, LOW));
        pins.put(4, gpio.provisionDigitalOutputPin(GPIO_27, LOW));

        askFsk = gpio.provisionDigitalOutputPin(GPIO_24, LOW);
        modulator = gpio.provisionDigitalOutputPin(GPIO_25, LOW);

        askFsk.low();
        modulator.low();
    }

    @SneakyThrows
    @Override
    public synchronized void transmit(Integer socket, Boolean state) {
        log.info(socket + ": changing to " + state);

        pins.entrySet().stream().forEach(e -> e.getValue().setState(converter.convert(socket, state, e.getKey())));
        sleep(500); // let it settle, encoder requires this

        modulator.high();
        sleep(500); // keep enabled for a period

        modulator.low();
    }
}
