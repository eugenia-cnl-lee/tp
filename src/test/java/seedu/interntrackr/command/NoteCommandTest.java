package seedu.interntrackr.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.interntrackr.exception.InternTrackrException;
import seedu.interntrackr.model.Application;
import seedu.interntrackr.model.ApplicationList;
import seedu.interntrackr.storage.Storage;
import seedu.interntrackr.ui.Ui;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Contains unit tests for {@link NoteCommand}.
 */
public class NoteCommandTest {

    @TempDir
    Path tempDir;

    private ApplicationList applications;
    private CapturingUi ui;
    private Storage storage;

    /**
     * Initializes the necessary components and stubs before each test execution.
     */
    @BeforeEach
    public void setUp() {
        applications = new ApplicationList();
        applications.addApplication(new Application("Google", "SWE Intern"));
        applications.addApplication(new Application("Meta", "Product Intern"));
        ui = new CapturingUi();
        storage = new Storage(tempDir.resolve("interntrackr.txt").toString());
    }

    /**
     * Verifies that executing the note command with a valid index successfully
     * updates the note of the specified application.
     *
     * @throws InternTrackrException If an error occurs during execution.
     */
    @Test
    public void execute_validIndexAndNote_setsNoteOnApplication() throws InternTrackrException {
        NoteCommand command = new NoteCommand(1, "Strong Java skills required");
        command.execute(applications, ui, storage);
        assertEquals("Strong Java skills required", applications.getApplication(1).getNote());
    }

    /**
     * Verifies that executing the note command only modifies the target application
     * and leaves other applications unchanged.
     *
     * @throws InternTrackrException If an error occurs during execution.
     */
    @Test
    public void execute_validIndex_doesNotModifyOtherApplications() throws InternTrackrException {
        NoteCommand command = new NoteCommand(1, "Strong Java skills required");
        command.execute(applications, ui, storage);
        assertNull(applications.getApplication(2).getNote());
    }

    /**
     * Verifies that executing the note command outputs the correct success messages
     * to the user interface.
     *
     * @throws InternTrackrException If an error occurs during execution.
     */
    @Test
    public void execute_validIndex_showsCorrectSuccessMessage() throws InternTrackrException {
        NoteCommand command = new NoteCommand(1, "Strong Java skills required");
        command.execute(applications, ui, storage);

        assertEquals(2, ui.printedMessages.size());
        assertEquals("Note updated for: Google | SWE Intern", ui.printedMessages.get(0));
        assertEquals("  Note: Strong Java skills required", ui.printedMessages.get(1));
    }

    /**
     * Verifies that executing the note command with an out-of-bounds index
     * throws an {@link InternTrackrException}.
     */
    @Test
    public void execute_indexOutOfRange_throwsInternTrackrException() {
        NoteCommand command = new NoteCommand(99, "Some note");
        assertThrows(InternTrackrException.class,
                () -> command.execute(applications, ui, storage));
    }

    /**
     * Verifies that notes containing special characters are correctly parsed
     * and saved to the application.
     *
     * @throws InternTrackrException If an error occurs during execution.
     */
    @Test
    public void execute_noteWithSpecialCharacters_setsNoteCorrectly() throws InternTrackrException {
        NoteCommand command = new NoteCommand(1, "Leetcode: Hard | Salary: $5000/month");
        command.execute(applications, ui, storage);
        assertEquals("Leetcode: Hard | Salary: $5000/month",
                applications.getApplication(1).getNote());
    }

    /**
     * Verifies that applying a new note to an application that already has a note
     * overwrites the previous note content.
     *
     * @throws InternTrackrException If an error occurs during execution.
     */
    @Test
    public void execute_updateExistingNote_overwritesPreviousNote() throws InternTrackrException {
        NoteCommand firstNote = new NoteCommand(1, "Original note");
        firstNote.execute(applications, ui, storage);

        NoteCommand secondNote = new NoteCommand(1, "Updated note");
        secondNote.execute(applications, ui, storage);

        assertEquals("Updated note", applications.getApplication(1).getNote());
    }

    /**
     * Verifies that passing a null {@link ApplicationList} triggers an {@link AssertionError}.
     */
    @Test
    public void execute_nullApplicationList_throwsAssertionError() {
        NoteCommand command = new NoteCommand(1, "Some note");
        assertThrows(AssertionError.class,
                () -> command.execute(null, ui, storage));
    }

    /**
     * Verifies that passing a null {@link Ui} triggers an {@link AssertionError}.
     */
    @Test
    public void execute_nullUi_throwsAssertionError() {
        NoteCommand command = new NoteCommand(1, "Some note");
        assertThrows(AssertionError.class,
                () -> command.execute(applications, null, storage));
    }

    /**
     * Verifies that passing a null {@link Storage} triggers an {@link AssertionError}.
     */
    @Test
    public void execute_nullStorage_throwsAssertionError() {
        NoteCommand command = new NoteCommand(1, "Some note");
        assertThrows(AssertionError.class,
                () -> command.execute(applications, ui, null));
    }

    /**
     * Verifies that instantiating a {@link NoteCommand} with an index of zero
     * or less triggers an {@link AssertionError}.
     */
    @Test
    public void constructor_indexZero_throwsAssertionError() {
        assertThrows(AssertionError.class,
                () -> new NoteCommand(0, "Some note"));
    }

    /**
     * Verifies that instantiating a {@link NoteCommand} with a null note string
     * triggers an {@link AssertionError}.
     */
    @Test
    public void constructor_nullNote_throwsAssertionError() {
        assertThrows(AssertionError.class,
                () -> new NoteCommand(1, null));
    }

    /**
     * A stub implementation of {@link Ui} to intercept and capture messages
     * without printing to the standard output during testing.
     */
    private static class CapturingUi extends Ui {
        public final List<String> printedMessages = new ArrayList<>();

        @Override
        public void showMessage(String message) {
            printedMessages.add(message);
        }
    }
}
