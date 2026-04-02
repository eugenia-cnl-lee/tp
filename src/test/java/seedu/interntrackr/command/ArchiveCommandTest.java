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
    public void execute_alreadyArchivedEntry_notReachableByDisplayIndex() throws InternTrackrException {
        // If an application is already archived it is invisible in `list`,
        // so display index 1 should resolve to the first *active* entry, not the archived one.
        ApplicationList applications = new ApplicationList();
        Application alreadyArchived = new Application("OldCo", "Role");
        alreadyArchived.setArchived(true);
        applications.addApplication(alreadyArchived);
        Application active = new Application("Google", "SWE");
        applications.addApplication(active);

        // Display index 1 must target "Google", not "OldCo"
        new ArchiveCommand(1).execute(applications, new Ui(), new StubStorage());

        assertTrue(active.isArchived());
        // The backing-list position of OldCo is 1; it must still be archived (unchanged)
        assertTrue(alreadyArchived.isArchived());
    }

    /**
     * Verifies that when the first slot in the backing list is archived, display index 1
     * correctly resolves to the second backing-list entry (the first active one).
     * This is the core regression test for the index-offset bug.
     */
    @Test
    public void execute_displayIndexWithOffsetFromArchivedSlots_archivesCorrectEntry()
            throws InternTrackrException {
        ApplicationList applications = new ApplicationList();
        Application archivedFirst = new Application("ArchivedCo", "OldRole");
        archivedFirst.setArchived(true);
        applications.addApplication(archivedFirst);           // backing index 1 — archived
        Application shouldBeArchived = new Application("ActiveCo", "NewRole");
        applications.addApplication(shouldBeArchived);        // backing index 2 — active, display index 1

        // User types `archive 1`: should target "ActiveCo" (display 1), not "ArchivedCo" (backing 1)
        new ArchiveCommand(1).execute(applications, new Ui(), new StubStorage());

        assertTrue(shouldBeArchived.isArchived(),
                "Display index 1 should archive the first active entry (ActiveCo)");
        // ArchivedCo must remain archived but not be double-touched
        assertTrue(archivedFirst.isArchived());
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
