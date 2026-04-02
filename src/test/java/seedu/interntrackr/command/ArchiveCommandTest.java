package seedu.interntrackr.command;

import org.junit.jupiter.api.Test;
import seedu.interntrackr.exception.InternTrackrException;
import seedu.interntrackr.model.Application;
import seedu.interntrackr.model.ApplicationList;
import seedu.interntrackr.storage.Storage;
import seedu.interntrackr.ui.Ui;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ArchiveCommandTest {

    /**
     * A stub implementation of {@link Storage} that suppresses all file I/O,
     * keeping tests hermetic without writing to disk.
     */
    private static class StubStorage extends Storage {
        public StubStorage() {
            super("stub/path.txt");
        }

        @Override
        public void save(List<Application> applications) throws InternTrackrException {
            // No-op: suppress disk I/O during tests
        }
    }

    /**
     * A stub UI that captures messages for verification.
     */
    private static class StubUi extends Ui {
        public final List<String> printedMessages = new ArrayList<>();

        @Override
        public void showMessage(String message) {
            printedMessages.add(message);
        }
    }

    @Test
    public void execute_validIndex_applicationIsArchived() throws InternTrackrException {
        ApplicationList applications = new ApplicationList();
        applications.addApplication(new Application("Google", "SWE"));

        new ArchiveCommand(1).execute(applications, new Ui(), new StubStorage());

        assertTrue(applications.getApplication(1).isArchived());
    }

    @Test
    public void execute_archivedApplicationHiddenFromList() throws InternTrackrException {
        ApplicationList applications = new ApplicationList();
        applications.addApplication(new Application("Google", "SWE"));
        applications.addApplication(new Application("Meta", "Backend"));

        new ArchiveCommand(1).execute(applications, new Ui(), new StubStorage());

        assertFalse(applications.getApplication(2).isArchived());
        assertTrue(applications.getApplication(1).isArchived());
    }

    @Test
    public void execute_indexOutOfRange_throwsInternTrackrException() {
        ApplicationList applications = new ApplicationList();
        applications.addApplication(new Application("Google", "SWE"));

        assertThrows(InternTrackrException.class,
                () -> new ArchiveCommand(5).execute(applications, new Ui(), new StubStorage()));
    }

    @Test
    public void execute_alreadyArchived_throwsInternTrackrException() throws InternTrackrException {
        ApplicationList applications = new ApplicationList();
        Application app = new Application("Google", "SWE");
        app.setArchived(true);
        applications.addApplication(app);

        assertThrows(InternTrackrException.class,
                () -> new ArchiveCommand(1).execute(applications, new Ui(), new StubStorage()));
    }

    @Test
    public void execute_nullApplicationList_throwsAssertionError() {
        assertThrows(AssertionError.class,
                () -> new ArchiveCommand(1).execute(null, new Ui(), new StubStorage()));
    }

    @Test
    public void execute_nullUi_throwsAssertionError() {
        ApplicationList applications = new ApplicationList();
        applications.addApplication(new Application("Google", "SWE"));

        assertThrows(AssertionError.class,
                () -> new ArchiveCommand(1).execute(applications, null, new StubStorage()));
    }


    @Test
    public void execute_validIndex_showsSuccessMessage() throws InternTrackrException {
        ApplicationList applications = new ApplicationList();
        applications.addApplication(new Application("Google", "SWE"));
        StubUi ui = new StubUi();

        new ArchiveCommand(1).execute(applications, ui, new StubStorage());

        assertEquals(3, ui.printedMessages.size());
        assertEquals("Got it. I've archived this application:", ui.printedMessages.get(0));
        assertTrue(ui.printedMessages.get(1).contains("Google"));
        assertTrue(ui.printedMessages.get(2).contains("list archive"));
    }

    @Test
    public void execute_nullStorage_throwsAssertionError() {
        ApplicationList applications = new ApplicationList();
        applications.addApplication(new Application("Google", "SWE"));

        assertThrows(AssertionError.class,
                () -> new ArchiveCommand(1).execute(applications, new Ui(), null));
    }

    @Test
    public void constructor_zeroIndex_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ArchiveCommand(0));
    }

    @Test
    public void constructor_negativeIndex_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ArchiveCommand(-1));
    }
}
