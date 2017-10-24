package ru.senla.bialevich.controller;

import ru.senla.bialevich.ClassSetting;
import ru.senla.bialevich.api.controller.ControllerHotel;
import ru.senla.bialevich.api.service.GuestService;
import ru.senla.bialevich.api.service.OrderService;
import ru.senla.bialevich.api.service.RoomService;
import ru.senla.bialevich.api.service.UsedServiceService;
import ru.senla.bialevich.entity.Guest;
import ru.senla.bialevich.entity.Order;
import ru.senla.bialevich.entity.Room;
import ru.senla.bialevich.entity.UsedService;
import ru.senla.bialevich.service.GuestServiceImpl;
import ru.senla.bialevich.service.OrderServiceImpl;
import ru.senla.bialevich.service.RoomServiceImpl;
import ru.senla.bialevich.service.UsedServiceServiceImpl;
import ru.senla.bialevich.util.exporter.Exporter;
import ru.senla.bialevich.util.importer.Importer;
import ru.senla.bialevich.util.initializer.Initializer;
import ru.senla.bialevich.util.service.WriteObject;

import java.util.List;

public class ControllerHotelImpl implements ControllerHotel {

    private GuestService guestService;
    private OrderService orderService;
    private RoomService roomService;
    private UsedServiceService usedService;

    private Initializer initializer;

    private Importer importer;
    private Exporter exporter;

    private WriteObject writeObject;
    private ClassSetting setting;

    private static ControllerHotel hotel;

    public static ControllerHotel getInstance() {
        if (hotel == null) {
            hotel = new ControllerHotelImpl();
        }

        return hotel;
    }

    @Override
    public void init() {
        this.setting = new ClassSetting();
        this.writeObject = new WriteObject();
        this.initializer = new Initializer();
        this.fillServicesFromInitializer();
        this.importer = new Importer(this.orderService.getAll(), this.roomService.getAll());
        this.exporter = new Exporter(writeObject);
    }

    @Override
    public String getProperty(String key) {

        return setting.getProperty(key);
    }

    private void fillServicesFromInitializer() {
        this.guestService = this.initializer.getGuestService();
        this.roomService = this.initializer.getRoomService();
        this.orderService = this.initializer.getOrderService();
        this.usedService = this.initializer.getServiceService();
    }

    @Override
    public void addGuest(Guest guest) {
        guestService.add(guest);
    }

    @Override
    public List<Guest> getAllGuest() {
        return guestService.getAll();
    }

    @Override
    public Integer getTotalNumberOfGuests() {
        return guestService.getTotalNumberOfGuests();
    }

    @Override
    public List<Guest> sortedGuestsBySurname() {
        return guestService.sortedGuestsBySurname();
    }

    @Override
    public List<Guest> sortedGuestsByDateOfDeparture() {
        return guestService.sortedGuestsByDateOfDeparture();
    }

    @Override
    public List<UsedService> getServiceByGuest(Guest guest) {
        return guestService.getServiceByGuest(guest);
    }

    @Override
    public List<Guest> getListGuests() {
        return guestService.getListGuests();
    }

    public void setRoomToGuest(Guest guest, Room room) {
        guestService.setRoomToGuest(guest, room);
    }

    @Override
    public void setOrderToGuest(Guest guest, Order order) {
        guestService.setOrderToGuest(guest, order);
    }

    @Override
    public void addOrder(Order order) {
        orderService.add(order);
    }

    @Override
    public List<Order> getListOrders() {
        return orderService.getListOrders();
    }

    @Override
    public void updateOrder(Order order) {
        orderService.update(order);
    }

    @Override
    public void addRoom(Room room) {
        roomService.add(room);
    }

    @Override
    public void registerGuestInRoom(Guest guest, Room room) {
        roomService.registerGuestInRoom(guest, room);
    }

    @Override
    public boolean changeRoomStatus(Room room) {
        boolean isBlock = Boolean.parseBoolean(this.getProperty("block.status"));

        if (isBlock) {
            room.setFree(false);
            roomService.update(room);
            return true;
        }
        return false;
    }

    @Override
    public void changeRoomPrice(Room room, Float value) {
        room.setPrice(value);
        roomService.update(room);
    }

    @Override
    public void updateRoom(Room room) {
        roomService.update(room);
    }

    public Room cloneRoom(Integer id) {
        return roomService.cloneRoom(id);
    }

    @Override
    public List<Room> getFreeRooms() {
        return roomService.getFreeRooms();
    }

    @Override
    public List<Room> sortedRoomsByPrice() {
        return roomService.sortedRoomsByPrice();
    }

    @Override
    public List<Room> sortedRoomsByCountBegs() {
        return roomService.sortedRoomsByCountBegs();
    }

    @Override
    public List<Room> sortedRoomsByCategory() {
        return roomService.sortedRoomsByCategory();
    }

    @Override
    public List<Room> sortedFreeRoomsByPrice() {
        return roomService.sortedFreeRoomsByPrice();
    }

    @Override
    public List<Room> sortedFreeRoomsByCountBegs() {
        return roomService.sortedFreeRoomsByCountBegs();
    }

    @Override
    public List<Room> sortedFreeRoomsByCategory() {
        return roomService.sortedFreeRoomsByCategory();
    }

    @Override
    public List<Room> getTotalFreeNumberOfRooms() {
        return roomService.getFreeRooms();
    }

    @Override
    public Float getRoomTotalPrice(Room room) {
        return roomService.getTotalPrice(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomService.getAll();
    }

    @Override
    public List<Room> getListRooms() {
        return roomService.getListRooms();
    }

    @Override
    public void setServiceToRoom(Room room, UsedService service) {
        roomService.setServiceToRoom(room, service);
    }

    @Override
    public void addUsedService(UsedService service) {
        usedService.add(service);
    }

    @Override
    public void updateService(UsedService service) {
        usedService.update(service);
    }

    @Override
    public List<UsedService> sortUsedServicesByPrice() {
        return usedService.sortUsedServicesByPrice();
    }

    @Override
    public List<UsedService> getListUsedServices() {
        return usedService.getListUsedServices();
    }

    @Override
    public Guest getGuestById(Integer id) {
        return guestService.getGuestById(id);
    }

    @Override
    public Room getRoomById(Integer id) {
        return roomService.getRoomById(id);
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderService.getOrderById(id);
    }

    @Override
    public UsedService getServiceById(Integer id) {
        return usedService.getUsedServiceById(id);
    }

    public void importGuests() {
        importer.importGuests(this.guestService.getAll());
    }

    public void importOrder() {
        importer.importOrders(this.orderService.getAll());
    }

    @Override
    public void importRooms() {
        importer.importRooms(this.roomService.getAll());
    }

    @Override
    public void exportGuests() {
        exporter.exportGuests(guestService.getAll());
    }

    @Override
    public void exportOrders() {
        exporter.exportOrders(this.orderService.getAll());
    }

    @Override
    public void exportRooms() {
        exporter.exportRooms(this.roomService.getAll());
    }

    @Override
    public void exportServices() {
        exporter.exportServices(this.usedService.getAll());
    }

    @Override
    public void exportAll() {
        exporter.exportAll(this.getAllGuest(), this.getListOrders(), this.getAllRooms(), this.getListUsedServices());
    }

    @Override
    public void exportModel() {
        exporter.exportModel();
    }
}
