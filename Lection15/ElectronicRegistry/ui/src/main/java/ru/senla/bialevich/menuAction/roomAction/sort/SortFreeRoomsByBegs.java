package ru.senla.bialevich.menuAction.roomAction.sort;

import org.apache.log4j.Logger;
import ru.senla.bialevich.model.Room;
import ru.senla.bialevich.menuAction.AbstractAction;
import ru.senla.bialevich.request.src.ru.senla.bialevich.DataPackage;
import ru.senla.bialevich.request.src.ru.senla.bialevich.IRequestHandler;
import ru.senla.bialevich.ui.IAction;

import java.util.List;

public class SortFreeRoomsByBegs extends AbstractAction implements IAction {
    private static final Logger log = Logger.getLogger(SortFreeRoomsByBegs.class);

    @Override
    public void execute(IRequestHandler requestHandler) {

        try {
            DataPackage dataPackage = new DataPackage("getSortedFreeRoomsByBegs", null);
            List<Room> rooms = (List<Room>) requestHandler.sendRequest(dataPackage);

            if (rooms == null) {
                System.out.println("Rooms not found.");
            } else {
                System.out.println(rooms);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
