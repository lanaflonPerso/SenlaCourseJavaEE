package ru.senla.bialevich.dao;

import ru.senla.bialevich.api.dao.RoomDao;
import ru.senla.bialevich.entity.Room;
import ru.senla.bialevich.entity.UsedService;

import java.util.List;

public class RoomDaoImpl extends AbstractDaoImpl<Room> implements RoomDao {


    public RoomDaoImpl() {
        super(Room.class);
    }

    @Override
    public Room getRoomById(Integer id) {
        Room room = null;
        for (int i = 0; i < getTargetList().size(); i++) {
            if (getTargetList().get(i).getId().equals(id)) {
                room = getTargetList().get(i);
            }
        }

        return room;
    }

    @Override
    public Integer getRoomIndexById(Integer id) {
        for (int i = 0; i < getTargetList().size(); i++) {
            if (getTargetList().get(i).getId() == id) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void update(Room room) {
        getTargetList().set(getRoomIndexById(room.getId()), room);
    }

    @Override
    public void setServiceToRoom(Room room, UsedService service) {
        room.addService(service);
    }

    @Override
    List<Room> getTargetList() {
        return dataBase.getRoomList();
    }


}
