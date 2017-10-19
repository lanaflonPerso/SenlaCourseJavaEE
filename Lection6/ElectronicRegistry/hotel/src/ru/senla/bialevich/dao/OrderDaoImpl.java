package ru.senla.bialevich.dao;

import ru.senla.bialevich.api.dao.OrderDao;
import ru.senla.bialevich.entity.Order;

import java.util.List;

public class OrderDaoImpl extends AbstractDaoImpl<Order> implements OrderDao {

    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    public Order getOrderById(Integer id) {
        Order order = null;
        for (int i = 0; i < getTargetList().size(); i++) {
            if (getTargetList().get(i).getId().equals(id)) {
                order = getTargetList().get(i);
            }
        }

        return order;
    }

    @Override
    List<Order> getTargetList() {
        return dataBase.getOrderList();
    }
}
