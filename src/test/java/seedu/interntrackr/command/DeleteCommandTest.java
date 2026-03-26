package seedu.interntrackr.command;

import org.junit.jupiter.api.Test;
import seedu.interntrackr.model.Application;
import seedu.interntrackr.model.ApplicationList;
import seedu.interntrackr.storage.Storage;
import seedu.interntrackr.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {

    @Test
    public void execute_validIndex_removedFromList() {
        ApplicationList applications = new ApplicationList();
        applications.addApplication(new Application("Google", "SWE"));
        DeleteCommand command = new DeleteCommand(1);
        Storage storage = new Storage("data/test_delete.txt");

        command.execute(applications, new Ui(), storage);

        assertEquals(0, applications.getSize());
    }
}
