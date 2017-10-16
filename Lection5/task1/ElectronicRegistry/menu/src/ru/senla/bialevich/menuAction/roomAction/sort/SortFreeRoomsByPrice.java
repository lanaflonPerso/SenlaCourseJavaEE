package ru.senla.bialevich.menuAction.roomAction.sort;

import ru.senla.bialevich.api.IAction;
import ru.senla.bialevich.api.controller.ControllerHotel;
import ru.senla.bialevich.controller.ControllerHotelImpl;
import ru.senla.bialevich.util.Printer;

public class SortFreeRoomsByPrice implements IAction {

    @Override
    public void execute() {
        ControllerHotel hotel = new ControllerHotelImpl();
        Printer printer = new Printer();

        printer.print(hotel.sortedFreeRoomsByCategory());
    }
}
