package ru.senla.bialevich.menuAction.roomAction;

import org.apache.log4j.Logger;
import ru.senla.bialevich.api.IAction;
import ru.senla.bialevich.controller.ControllerHotelImpl;
import ru.senla.bialevich.util.Printer;

public class GetFreeRooms implements IAction {
    private static final Logger log = Logger.getLogger(GetFreeRooms.class);

    @Override
    public void execute() {
        Printer printer = new Printer();

        try {
            printer.print(ControllerHotelImpl.getInstance().getFreeRooms());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}