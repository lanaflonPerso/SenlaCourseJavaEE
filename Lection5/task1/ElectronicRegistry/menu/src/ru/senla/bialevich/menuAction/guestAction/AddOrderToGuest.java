package ru.senla.bialevich.menuAction.guestAction;

import ru.senla.bialevich.api.IAction;
import ru.senla.bialevich.api.controller.ControllerHotel;
import ru.senla.bialevich.controller.ControllerHotelImpl;
import ru.senla.bialevich.util.InputReader;

import java.util.Scanner;

public class AddOrderToGuest implements IAction {

    @Override
    public void execute() {
        ControllerHotel hotel = new ControllerHotelImpl();
        InputReader reader = new InputReader();
        Scanner scanner = new Scanner(System.in);

        Integer idGuest = reader.getInputInt(scanner, "Enter ID the guest.");
        Integer idOrder = reader.getInputInt(scanner, "Enter ID the order.");

        hotel.setOrderToGuest(hotel.getGuestById(idGuest), hotel.getOrderById(idOrder));
    }
}
