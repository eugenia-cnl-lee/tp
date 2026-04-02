package seedu.interntrackr.parser;

import org.junit.jupiter.api.Test;

import seedu.interntrackr.command.NoteCommand;
import seedu.interntrackr.exception.InternTrackrException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Tests for {@link NoteCommandParser}.
 */
public class NoteCommandParserTest {

    // ===== Valid inputs (positive test cases) =====

    @Test
    public void parse_validIndexAndNote_returnsNoteCommand() throws InternTrackrException {
        NoteCommand command = NoteCommandParser.parse("1 n/Strong Java skills required");
        assertNotNull(command);
        assertInstanceOf(NoteCommand.class, command);
    }

    @Test
    public void parse_noteWithSpaces_returnsNoteCommand() throws InternTrackrException {
        NoteCommand command = NoteCommandParser.parse("2 n/Good culture, remote friendly");
        assertNotNull(command);
    }

    @Test
    public void parse_noteWithSpecialCharacters_returnsNoteCommand() throws InternTrackrException {
        NoteCommand command = NoteCommandParser.parse("1 n/Salary: $5000 | Tech: Java/Python");
        assertNotNull(command);
    }

    // ===== Invalid inputs (negative test cases) =====

    @Test
    public void parse_nullArguments_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> NoteCommandParser.parse(null));
    }

    @Test
    public void parse_blankArguments_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> NoteCommandParser.parse("   "));
    }

    @Test
    public void parse_missingNotePrefix_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> NoteCommandParser.parse("1 Strong Java skills"));
    }

    @Test
    public void parse_emptyNoteContent_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> NoteCommandParser.parse("1 n/"));
    }

    @Test
    public void parse_blankNoteContent_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> NoteCommandParser.parse("1 n/   "));
    }

    @Test
    public void parse_nonNumericIndex_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> NoteCommandParser.parse("abc n/Some note"));
    }

    @Test
    public void parse_zeroIndex_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> NoteCommandParser.parse("0 n/Some note"));
    }

    @Test
    public void parse_negativeIndex_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> NoteCommandParser.parse("-1 n/Some note"));
    }

    @Test
    public void parse_missingIndex_throwsInternTrackrException() {
        assertThrows(InternTrackrException.class,
                () -> NoteCommandParser.parse("n/Some note"));
    }
}
