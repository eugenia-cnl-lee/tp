package seedu.interntrackr.command;

import org.junit.jupiter.api.BeforeEach;
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

/**
 * Contains unit tests for {@link ListArchiveCommand}.
 */
public class ListArchiveCommandTest {

    private ListArchiveCommand listArchiveCommand;
    private StubApplicationList stubAppList;
    private StubUi stubUi;
    private Storage dummyStorage;

    /**
     * Initializes the necessary components and stubs before each test execution.
     */
    @BeforeEach
    public void setUp() {
        listArchiveCommand = new ListArchiveCommand();
        stubAppList = new StubApplicationList();
        stubUi = new StubUi();
        dummyStorage = null;
    }

    /**
     * A stub implementation of {@link Ui} to intercept and capture messages
     * without printing to the standard output during testing.
     */
    private static class StubUi extends Ui {
        public final List<String> printedMessages = new ArrayList<>();

        @Override
        public void showMessage(String message) {
            printedMessages.add(message);
        }
    }

    /**
     * A stub implementation of {@link ApplicationList} to inject and manage
     * isolated test data independent of the actual storage or model logic.
     */
    private static class StubApplicationList extends ApplicationList {
        public final List<Application> applications = new ArrayList<>();

        @Override
        public int getSize() {
            return applications.size();
        }

        @Override
        public Application getApplication(int index) {
            return applications.get(index - 1);
        }
    }

    /**
     * Verifies that executing the list archive command on an empty application list
     * outputs the appropriate empty archive message.
     *
     * @throws InternTrackrException If an error occurs during execution.
     */
    @Test
    public void execute_emptyApplicationList_showsEmptyMessage() throws InternTrackrException {
        listArchiveCommand.execute(stubAppList, stubUi, dummyStorage);

        assertEquals(1, stubUi.printedMessages.size());
        assertEquals("No archived applications found.", stubUi.printedMessages.get(0));
    }

    /**
     * Verifies that only archived applications are shown when there is one archived entry.
     *
     * @throws InternTrackrException If an error occurs during execution.
     */
    @Test
    public void execute_singleArchivedApplication_showsOneApplication() throws InternTrackrException {
        Application archivedApp = new Application("Google", "SWE Intern", "Interview");
        archivedApp.setArchived(true);
        stubAppList.applications.add(archivedApp);

        listArchiveCommand.execute(stubAppList, stubUi, dummyStorage);

        assertEquals(2, stubUi.printedMessages.size());
        assertEquals("Here are your archived internship applications:", stubUi.printedMessages.get(0));
        assertTrue(stubUi.printedMessages.get(1).startsWith("1. "));
        assertTrue(stubUi.printedMessages.get(1).contains("Google"));
    }

    /**
     * Verifies that a non-archived application is not shown by list archive.
     *
     * @throws InternTrackrException If an error occurs during execution.
     */
    @Test
    public void execute_noArchivedApplications_showsEmptyMessage() throws InternTrackrException {
        stubAppList.applications.add(new Application("Shopee", "Backend Intern", "Applied"));
        stubAppList.applications.add(new Application("Grab", "Frontend Intern", "Offered"));

        listArchiveCommand.execute(stubAppList, stubUi, dummyStorage);

        assertEquals(1, stubUi.printedMessages.size());
        assertEquals("No archived applications found.", stubUi.printedMessages.get(0));
    }

    /**
     * Verifies that only archived applications are displayed when the list contains a mix
     * of archived and non-archived entries.
     *
     * @throws InternTrackrException If an error occurs during execution.
     */
    @Test
    public void execute_mixedList_showsOnlyArchivedApplications() throws InternTrackrException {
        stubAppList.applications.add(new Application("Shopee", "Backend Intern", "Applied"));
        Application archivedApp = new Application("TikTok", "Data Intern", "Rejected");
        archivedApp.setArchived(true);
        stubAppList.applications.add(archivedApp);
        stubAppList.applications.add(new Application("Grab", "Frontend Intern", "Offered"));

        listArchiveCommand.execute(stubAppList, stubUi, dummyStorage);

        assertEquals(2, stubUi.printedMessages.size());
        assertEquals("Here are your archived internship applications:", stubUi.printedMessages.get(0));
        assertTrue(stubUi.printedMessages.get(1).contains("TikTok"));
    }

    /**
     * Verifies that multiple archived applications are all displayed correctly.
     *
     * @throws InternTrackrException If an error occurs during execution.
     */
    @Test
    public void execute_multipleArchivedApplications_showsAllArchived() throws InternTrackrException {
        Application app1 = new Application("Apple", "iOS Intern", "Applied");
        app1.setArchived(true);
        Application app2 = new Application("Meta", "AR Intern", "Interview");
        app2.setArchived(true);
        stubAppList.applications.add(app1);
        stubAppList.applications.add(app2);

        listArchiveCommand.execute(stubAppList, stubUi, dummyStorage);

        assertEquals(3, stubUi.printedMessages.size());
        assertEquals("Here are your archived internship applications:", stubUi.printedMessages.get(0));
        assertTrue(stubUi.printedMessages.get(1).contains("Apple"));
        assertTrue(stubUi.printedMessages.get(2).contains("Meta"));
    }

    /**
     * Verifies that passing a null ApplicationList triggers an AssertionError.
     */
    @Test
    public void execute_nullApplicationList_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> listArchiveCommand.execute(null, stubUi, dummyStorage));
    }

    /**
     * Verifies that passing a null Ui triggers a NullPointerException when
     * the command attempts to display messages.
     */
    @Test
    public void execute_nullUi_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> listArchiveCommand.execute(stubAppList, null, dummyStorage));
    }

    /**
     * Verifies that a null Storage object does not affect the successful execution
     * of the list archive command, as the command does not require file I/O operations.
     *
     * @throws InternTrackrException If an error occurs during execution.
     */
    @Test
    public void execute_nullStorage_executesNormally() throws InternTrackrException {
        Application archivedApp = new Application("Netflix", "SWE", "Applied");
        archivedApp.setArchived(true);
        stubAppList.applications.add(archivedApp);

        listArchiveCommand.execute(stubAppList, stubUi, null);

        assertEquals(2, stubUi.printedMessages.size());
    }

    /**
     * Verifies that the {@link ListArchiveCommand} is correctly instantiated as a
     * subclass of the {@link Command} abstract base class.
     */
    @Test
    public void commandType_isInstanceOfCommand_true() {
        assertTrue(listArchiveCommand instanceof Command);
    }
}
