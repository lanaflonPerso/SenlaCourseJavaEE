package ru.senla.bialevich.menuAction.importExportAction.importer;

import org.apache.log4j.Logger;
import ru.senla.bialevich.DataPackage;
import ru.senla.bialevich.IRequestHandler;
import ru.senla.bialevich.api.IAction;
import ru.senla.bialevich.menuAction.AbstractAction;
import ru.senla.bialevich.util.Printer;

public class ImportOrders extends AbstractAction implements IAction {
    private static final Logger LOG = Logger.getLogger(ImportOrders.class);
    private Printer printer = new Printer();

    @Override
    public void execute(IRequestHandler requestHandler) {
        try {
            DataPackage dataPackage = new DataPackage("importOrder", null);
            requestHandler.sendRequest(dataPackage);
            printer.print("Rooms have successfully imported.");
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
