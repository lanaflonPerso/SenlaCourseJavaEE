package ru.senla.bialevich.entity;

import ru.senla.bialevich.annotations.ConfigProperty;
import ru.senla.bialevich.util.Printer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room extends BaseEntity implements Cloneable, Serializable {
    private static final long serialVersionUID = -4085288967304236682L;

    transient private Printer printer = new Printer();

    @ConfigProperty(configName = "annotation.properties", propertyName = "Room.id", type = Integer.class)
    private Integer id;

    @ConfigProperty(configName = "annotation.properties", propertyName = "Room.number", type = Integer.class)
    private Integer roomNumber;

    @ConfigProperty(configName = "annotation.properties", propertyName = "Room.countStars", type = Integer.class)
    private Integer countStars;

    @ConfigProperty(configName = "annotation.properties", propertyName = "Room.capacity", type = Integer.class)
    private Integer capacity;

    @ConfigProperty(configName = "annotation.properties", propertyName = "Room.free", type = Boolean.class)
    private boolean isFree = true;

    @ConfigProperty(configName = "annotation.properties", propertyName = "Room.price", type = Float.class)
    private Float price;
    private List<Guest> guestList = new ArrayList<>();
    private List<UsedService> usedServiceList = new ArrayList<>();
    public static final String ENTITY_TOKEN = "Room";

    public Room(Integer roomNumber, Integer countStars, Integer capacity, Float price) {
        this.roomNumber = roomNumber;
        this.countStars = countStars;
        this.capacity = capacity;
        this.price = price;
    }

    public Room() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<Guest> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<Guest> guestList) {
        this.guestList = guestList;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCountStars() {
        return countStars;
    }

    public void setCountStars(Integer countStars) {
        this.countStars = countStars;
    }

    public Float getPrice() {
        Float coast = 0.0f;

        for (UsedService usedService : usedServiceList) {
            if (usedService.getPrice() != null)
                coast += usedService.getPrice();
        }

        return coast + price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<UsedService> getUsedServiceList() {
        return usedServiceList;
    }

    public void setUsedServiceList(List<UsedService> usedServiceList) {
        this.usedServiceList = usedServiceList;
    }

    public void addService(UsedService usedService) {
        usedServiceList.add(usedService);
    }

    public void addGuest(Guest guest) {
        if (guest.getRoom() != null) {
            printer.print("Guest room is already");
        }
        if (guestList == null) {
            guestList = new ArrayList<>();
        }
        if (guestList.size() < capacity) {
            guestList.add(guest);
            isFree = false;
        } else {
            printer.print("Is full");
        }
    }

    public void removeGuest(Guest guest) {
        if (guestList == null) {
           printer.print("Guests is empty");
        }

        for (int i = 0; i < guestList.size(); i++) {
            if (guestList.get(i).getId() == guest.getId()) {
                guestList.remove(i);
                return;
            }

            printer.print("Guest not found");
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Room{");
        sb.append("id=").append(id);
        sb.append(", roomNumber=").append(roomNumber);
        sb.append(", countStars=").append(countStars);
        sb.append(", capacity=").append(capacity);
        sb.append(", isFree=").append(isFree);
        sb.append(", price=").append(price);
//        sb.append(", usedServiceList=").append(usedServiceList);
        sb.append('}');
        return sb.toString();
    }
}
