package ru.senla.bialevich.menuAction.roomAction;

import org.apache.log4j.Logger;
import ru.senla.bialevich.api.IAction;
import ru.senla.bialevich.entity.Room;
import ru.senla.bialevich.menuAction.AbstractAction;
import ru.senla.bialevich.util.InputReader;
import ru.senla.bialevich.util.Printer;

import java.util.Scanner;

public class CloneRoom extends AbstractAction implements IAction {
    private static final Logger log = Logger.getLogger(CloneRoom.class);
    private Printer printer = new Printer();


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Integer roomId = InputReader.getInputInt(scanner, "Enter the id of the room you want to clone");

        if (hotel.getRoomById(roomId) == null) {
            printer.print("Room not found.");
        } else {
            Room room = hotel.cloneRoom(roomId);
            hotel.addRoom(room);
            String answer = InputReader.getInputString(scanner, "Do you want to change the number? - Y/N");

            if (answer.equals("y")) {

                room.setRoomNumber(InputReader.getInputInt(scanner, "Enter the room number"));
                room.setCountStars(InputReader.getInputInt(scanner, "Enter the count stars"));
                room.setCapacity(InputReader.getInputInt(scanner, "Enter the capacity"));
                room.setPrice(InputReader.getInputFloat(scanner, "Enter the price"));

                hotel.updateRoom(room);
            }
        }
    }
}
