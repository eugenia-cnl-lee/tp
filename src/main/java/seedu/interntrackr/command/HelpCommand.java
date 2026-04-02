package seedu.interntrackr.command;

import seedu.interntrackr.model.ApplicationList;
import seedu.interntrackr.storage.Storage;
import seedu.interntrackr.ui.Ui;

import java.util.logging.Logger;

/**
 * Displays the help message containing a link to the User Guide.
 */
public class HelpCommand extends Command {
    private static final Logger logger = Logger.getLogger(HelpCommand.class.getName());

    /**
     * Executes the help command by printing the User Guide link.
     *
     * @param applications The current list of applications.
     * @param ui           The UI used to display output.
     * @param storage      The storage object.
     */
    @Override
    public void execute(ApplicationList applications, Ui ui, Storage storage) {
        assert ui != null : "Ui must not be null";
        logger.info("Executing HelpCommand.");

        ui.showMessage("Need help? You can view the full User Guide with all commands here:");
        ui.showMessage("https://ay2526s2-cs2113-t14-1.github.io/tp/UserGuide.html");

        logger.fine("HelpCommand executed successfully.");
    }
}
