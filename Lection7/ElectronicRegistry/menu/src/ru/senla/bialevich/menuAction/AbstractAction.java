package ru.senla.bialevich.menuAction;

import ru.senla.bialevich.api.controller.ControllerHotel;
import ru.senla.bialevich.controller.ControllerHotelImpl;

public abstract class AbstractAction {

    protected ControllerHotel hotel = ControllerHotelImpl.getInstance();
}
