package ru.senla.bialevich.service;

import org.apache.log4j.Logger;
import ru.senla.bialevich.connectordb.HibernateUtil;
import ru.senla.bialevich.dao.GuestDao;
import ru.senla.bialevich.dependency.DependencyInjection;
import ru.senla.bialevich.enums.SortType;
import ru.senla.bialevich.model.Guest;

import java.util.List;

public class GuestServiceImpl extends AbstractService implements GuestService {

    private static final Logger LOG = Logger.getLogger(GuestServiceImpl.class);

    private GuestDao guestDao = (GuestDao) DependencyInjection.getInjection().getInstance(GuestDao.class);

    public GuestServiceImpl() {
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void addGuest(Guest guest) {
        session = HibernateUtil.getCurrentSession();

        try {
            session.beginTransaction();
            guestDao.add(session, guest);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error(e.getMessage());
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Guest getGuest(int id) {

        session = HibernateUtil.getCurrentSession();

        Guest guest = null;
        try {
            session.beginTransaction();
            guest = guestDao.getById(session, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error(e.getMessage());
        }

        return guest;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void update(Guest guest) {

        session = HibernateUtil.getCurrentSession();

        try {
            session.beginTransaction();
            guestDao.update(session, guest);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Guest guest) {

        session = HibernateUtil.getCurrentSession();

        try {
            session.beginTransaction();
            guestDao.remove(session, guest);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    @SuppressWarnings("Duplicates")
    public List<Guest> getAll(SortType type) {

        session = HibernateUtil.getCurrentSession();

        List<Guest> guests = null;

        try {
            session.beginTransaction();
            guests = guestDao.getAll(session, type, null);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error(e.getMessage());
        }

        return guests;
    }

    @Override
    public int getCount() {

        session = HibernateUtil.getCurrentSession();

        int count = 0;

        try {
            session.beginTransaction();
            count = guestDao.getCount(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error(e.getMessage());
        }

        return count;
    }
}
