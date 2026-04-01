//@@author N-SANJAI
package seedu.interntrackr.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.interntrackr.exception.InternTrackrException;
import seedu.interntrackr.model.Application;
import seedu.interntrackr.model.ApplicationList;
import seedu.interntrackr.storage.Storage;
import seedu.interntrackr.ui.Ui;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OfferCommandTest {

    private ApplicationList applications;
    private Ui ui;
    private Storage storage;
    private final String testFilePath = "data/test_offer.txt";

    @BeforeEach
    public void setUp() {
        applications = new ApplicationList();
        applications.addApplication(new Application("Google", "Software Engineer", "Interview"));
        // Add an application that is ALREADY offered to test the conditional branch
        applications.addApplication(new Application("Meta", "Data Scientist", "Offered"));
        ui = new Ui();
        storage = new Storage(testFilePath);
    }

    @Test
    public void execute_validIndexAndSalary_updatesSalaryAndStatus() throws Exception {
        OfferCommand command = new OfferCommand(1, 8500.50);
        command.execute(applications, ui, storage);

        Application app = applications.getApplication(1);
        assertEquals(8500.50, app.getSalary());
        assertEquals("Offered", app.getStatus());

        // Clean up test file
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void execute_alreadyOfferedStatus_updatesSalaryOnly() throws Exception {
        OfferCommand command = new OfferCommand(2, 9000.00);
        command.execute(applications, ui, storage);

        Application app = applications.getApplication(2);
        assertEquals(9000.00, app.getSalary());
        // Status should remain unchanged, hitting the other branch of the if-statement
        assertEquals("Offered", app.getStatus());

        // Clean up test file
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void execute_indexTooLarge_throwsException() {
        OfferCommand command = new OfferCommand(5, 5000);
        assertThrows(InternTrackrException.class, () -> command.execute(applications, ui, storage));
    }

    @Test
    public void execute_indexTooSmall_throwsException() {
        OfferCommand command = new OfferCommand(0, 5000);
        assertThrows(InternTrackrException.class, () -> command.execute(applications, ui, storage));
    }

    @Test
    public void execute_storageSaveFails_throwsException() {
        // Create an anonymous subclass of Storage (a "Stub") that forces an exception
        Storage failingStorage = new Storage(testFilePath) {
            @Override
            public void save(List<Application> apps) throws InternTrackrException {
                throw new InternTrackrException("Simulated save failure for testing");
            }
        };

        OfferCommand command = new OfferCommand(1, 8500.50);

        InternTrackrException thrown = assertThrows(InternTrackrException.class,
                () -> command.execute(applications, ui, failingStorage));

        assertTrue(thrown.getMessage().contains("failed to save to disk"));
    }
}
