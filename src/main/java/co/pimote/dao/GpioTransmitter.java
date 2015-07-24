package co.pimote.dao;

import com.pi4j.io.gpio.GpioController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GpioTransmitter implements Transmitter {
    private Logger log = Logger.getLogger(GpioTransmitter.class);
    @Autowired
    private GpioController gpio;


    @Override
    public void transmit(Integer id, Boolean state) {
        log.info(id + "changing to " + state);
    }
}
