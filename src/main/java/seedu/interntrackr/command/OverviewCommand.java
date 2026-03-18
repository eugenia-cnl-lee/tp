package seedu.interntrackr.command;

import seedu.interntrackr.model.ApplicationList;
import seedu.interntrackr.storage.Storage;
import seedu.interntrackr.ui.Ui;
import seedu.interntrackr.exception.InternTrackrException;

import java.util.logging.Logger;

/**
 * Displays a summary of all tracked internship applications.
 */
public class OverviewCommand extends Command {
    private static final Logger logger = Logger.getLogger(OverviewCommand.class.getName());

    @Override
    public void execute(ApplicationList applications, Ui ui, Storage storage) throws InternTrackrException {
        assert applications != null : "ApplicationList must not be null";
        assert ui != null : "Ui must not be null";

        if (applications == null) {
            logger.severe("execute() called with null ApplicationList.");
            throw new InternTrackrException("ApplicationList is null. Cannot display overview.");
        }

        if (ui == null) {
            logger.severe("execute() called with null Ui.");
            throw new InternTrackrException("Ui is null. Cannot display overview.");
        }

        int size = applications.getSize();
        assert size >= 0 : "Application count must be non-negative";

        logger.info("Executing OverviewCommand. Application count: " + size);

        ui.showMessage("Overview:");
        ui.showMessage("You are currently tracking " + size + " applications.");
        ui.showMessage("Keep up the momentum!");

        logger.fine("OverviewCommand executed successfully.");
    }
}
