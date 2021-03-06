package ru.senla.bialevich.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.senla.bialevich.model.Registration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Date;

@WebServlet("/registrationCrud")
public class RegistrationCrudServlet extends AbstractServlet {
    private static final long serialVersionUID = -6134029897859982068L;

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        resp.setContentType(JSON);

        Integer guestId = Integer.parseInt(req.getParameter("guestId"));
        Integer roomId = Integer.parseInt(req.getParameter("roomId"));

        Date startDate = Date.from(Instant.parse(req.getParameter("yyyy-MM-dd")));
        Date endDate = Date.from(Instant.parse(req.getParameter("yyyy-MM-dd")));

        Registration registration = new Registration(guestId, roomId, startDate, endDate);

        hotel.addRegistration(registration);

        writer.println("Adding a registration is successful.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        resp.setContentType(JSON);

        Integer id = Integer.parseInt(req.getParameter("id"));
        Date startDate = Date.from(Instant.parse(req.getParameter("yyyy-MM-dd")));
        Date endDate = Date.from(Instant.parse(req.getParameter("yyyy-MM-dd")));

        Registration registration = hotel.getRegistration(id);

        if ((!req.getParameter(startDate.toString()).equals("")) || (req.getParameter(endDate.toString()) != null)) {
            registration.setStartDate(startDate);
        }
        if ((!req.getParameter(endDate.toString()).equals("")) || (req.getParameter(endDate.toString()) != null)) {
            registration.setFinalDate(endDate);
        }

        writer.println("Update registration is successful.");
    }
}
