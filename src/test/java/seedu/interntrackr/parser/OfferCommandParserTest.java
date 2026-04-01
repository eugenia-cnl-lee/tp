//@@author N-SANJAI
package seedu.interntrackr.parser;

import org.junit.jupiter.api.Test;
import seedu.interntrackr.exception.InternTrackrException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class OfferCommandParserTest {

    @Test
    public void parse_validInput_returnsOfferCommand() {
        assertDoesNotThrow(() -> {
            OfferCommandParser.parse("1 s/5000");
        });
        assertDoesNotThrow(() -> {
            OfferCommandParser.parse("2 s/ 6500.75");
        });
    }

    @Test
    public void parse_emptyInput_throwsException() {
        assertThrows(InternTrackrException.class, () -> {
            OfferCommandParser.parse(""); // Completely empty
        });
        assertThrows(InternTrackrException.class, () -> {
            OfferCommandParser.parse("    "); // Blank spaces
        });
    }

    @Test
    public void parse_missingSalaryPrefix_throwsException() {
        assertThrows(InternTrackrException.class, () -> {
            OfferCommandParser.parse("1 5000");
        });
    }

    @Test
    public void parse_invalidSalaryFormat_throwsException() {
        assertThrows(InternTrackrException.class, () -> {
            OfferCommandParser.parse("1 s/five-thousand");
        });
    }

    @Test
    public void parse_negativeSalary_throwsException() {
        assertThrows(InternTrackrException.class, () -> {
            OfferCommandParser.parse("1 s/-500");
        });
    }

    @Test
    public void parse_missingIndex_throwsException() {
        assertThrows(InternTrackrException.class, () -> {
            OfferCommandParser.parse(" s/5000"); // No index provided
        });
    }

    @Test
    public void parse_invalidIndexFormat_throwsException() {
        assertThrows(InternTrackrException.class, () -> {
            OfferCommandParser.parse("one s/5000"); // Word instead of number
        });
        assertThrows(InternTrackrException.class, () -> {
            OfferCommandParser.parse("1.5 s/5000"); // Decimal instead of integer
        });
    }
}
