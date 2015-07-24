package co.pimote.dao;

import com.pi4j.io.gpio.GpioProvider;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.PinListener;

public class DummyGpioProvider implements GpioProvider {

    @Override
    public String getName() {
        return this.getName();
    }

    @Override
    public boolean hasPin(Pin pin) {
        return false;
    }

    @Override
    public void export(Pin pin, PinMode mode, PinState defaultState) {

    }

    @Override
    public void export(Pin pin, PinMode mode) {

    }

    @Override
    public boolean isExported(Pin pin) {
        return false;
    }

    @Override
    public void unexport(Pin pin) {

    }

    @Override
    public void setMode(Pin pin, PinMode mode) {

    }

    @Override
    public PinMode getMode(Pin pin) {
        return PinMode.DIGITAL_OUTPUT;
    }

    @Override
    public void setPullResistance(Pin pin, PinPullResistance resistance) {

    }

    @Override
    public PinPullResistance getPullResistance(Pin pin) {
        return PinPullResistance.OFF;
    }

    @Override
    public void setState(Pin pin, PinState state) {

    }

    @Override
    public PinState getState(Pin pin) {
        return PinState.LOW;
    }

    @Override
    public void setValue(Pin pin, double value) {

    }

    @Override
    public double getValue(Pin pin) {
        return 0;
    }

    @Override
    public void setPwm(Pin pin, int value) {

    }

    @Override
    public int getPwm(Pin pin) {
        return 0;
    }

    @Override
    public void addListener(Pin pin, PinListener listener) {

    }

    @Override
    public void removeListener(Pin pin, PinListener listener) {

    }

    @Override
    public void removeAllListeners() {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public boolean isShutdown() {
        return false;
    }
}
