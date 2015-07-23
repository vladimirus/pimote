package co.pimote.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class GpioTransmitter implements Transmitter {
    private Logger log = Logger.getLogger(GpioTransmitter.class);

    @Override
    public void transmit(Integer id, Boolean state) {
        log.info(id + "changing to " + state);
    }
}
