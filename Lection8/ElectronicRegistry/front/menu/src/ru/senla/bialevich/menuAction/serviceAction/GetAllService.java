package ru.senla.bialevich.menuAction.serviceAction;

import org.apache.log4j.Logger;
import ru.senla.bialevich.DataPackage;
import ru.senla.bialevich.IRequestHandler;
import ru.senla.bialevich.api.IAction;
import ru.senla.bialevich.entity.UsedService;
import ru.senla.bialevich.menuAction.AbstractAction;
import ru.senla.bialevich.util.Printer;

import java.util.List;

public class GetAllService extends AbstractAction implements IAction {
    private static final Logger LOG = Logger.getLogger(GetAllService.class);

    @Override
    public void execute(IRequestHandler requestHandler) {
        Printer printer = new Printer();

        try {
            DataPackage dataPackage = new DataPackage("getAllService", null);
            List<UsedService> services = (List<UsedService>) requestHandler.sendRequest(dataPackage);

            if (services == null) {
                printer.print("Services not found.");
            } else {
                printer.print(services);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}