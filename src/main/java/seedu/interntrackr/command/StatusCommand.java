package seedu.interntrackr.command;

import seedu.interntrackr.model.Application;
import seedu.interntrackr.model.ApplicationList;
import seedu.interntrackr.storage.Storage;
import seedu.interntrackr.ui.Ui;
import seedu.interntrackr.exception.InternTrackrException;

/**
 * Updates the status of an internship application.
 */
public class StatusCommand extends Command {
    private int index;
    private String status;

    public StatusCommand(int index, String status) {
        this.index = index;
        this.status = status;
    }

    @Override
    public void execute(ApplicationList applications, Ui ui, Storage storage) throws InternTrackrException {
        if (index < 1 || index > applications.getSize()) {
            throw new InternTrackrException("Invalid application index.");
        }

        // Adjust for 0-based indexing
        Application app = applications.getApplication(index - 1);
        app.setStatus(this.status);

        // Printing directly for now; Member 5 can abstract this into Ui.java later
        System.out.println("Status updated! " + app.getCompanyName() + " is now marked as [" + this.status + "]");

        storage.save(applications.getApplications());
    }
}
