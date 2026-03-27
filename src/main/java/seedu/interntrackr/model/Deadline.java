//@@author eugenia-cnl-lee
package seedu.interntrackr.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a single deadline.
 */
public class Deadline {
    private final String deadlineType;
    private final LocalDate dueDate;
    private boolean isDone;

    /**
     * Constructs a Deadline with the given category, dueDate.
     *
     * @param deadlineType              The category of the deadline.
     * @param dueDate                   The due date of the deadline.
     * @throws NullPointerException     If deadlineType or dueDate is null.
     * @throws IllegalArgumentException If deadlineType is blank.
     */
    public Deadline(String deadlineType, LocalDate dueDate) {
        this(deadlineType, dueDate, false);
    }

    /**
     * Constructs a Deadline with the given deadline type, due date, and completion status.
     *
     * @param deadlineType              The category of the deadline.
     * @param dueDate                   The due date of the deadline.
     * @param isDone                    The completion status of the deadline.
     * @throws NullPointerException     If deadlineType or dueDate is null.
     * @throws IllegalArgumentException If deadlineType is blank.
     */
    public Deadline(String deadlineType, LocalDate dueDate, boolean isDone) {
        Objects.requireNonNull(deadlineType, "Deadline type cannot be null");
        Objects.requireNonNull(dueDate, "Due date cannot be null");

        String trimmedDeadlineType = deadlineType.trim();
        if (trimmedDeadlineType.isEmpty()) {
            throw new IllegalArgumentException("Deadline type cannot be empty");
        }

        this.deadlineType = trimmedDeadlineType;
        this.dueDate = dueDate;
        this.isDone = isDone;

        assert this.deadlineType != null : "Deadline type should not be null";
        assert !this.deadlineType.isEmpty() : "Deadline type should not be empty";
        assert this.dueDate != null : "Due date should not be null";
    }

    /**
     * Returns the category of this deadline.
     *
     * @return The deadline's deadlineType field.
     */
    public String getDeadlineType() {
        return this.deadlineType;
    }

    /**
     * Returns the due date of this deadline.
     *
     * @return The deadline's dueDate field.
     */
    public LocalDate getDueDate() {
        return this.dueDate;
    }

    /**
     * Returns the completeness of this deadline.
     *
     * @return The deadline's isDone field.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Sets the deadline as completed.
     */
    public void setDone() {
        this.isDone = true;
        assert this.isDone : "Deadline should be marked as done";
    }

    /**
     * Sets the deadline as not completed.
     */
    public void setNotDone() {
        this.isDone = false;
        assert !this.isDone : "Deadline should be marked as not done";
    }

    /**
     * Returns a formatted string representation of this deadline's completeness.
     *
     * @return A human-readable string of the deadline's isDone field.
     */
    private String markIsDone() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns a formatted string representation of this deadline.
     *
     * @return A human-readable string representation of this deadline.
     */
    @Override
    public String toString() {
        assert this.deadlineType != null : "Deadline type should not be null";
        assert this.dueDate != null : "Due date should not be null";

        return "Deadline Type: " + this.deadlineType + " | Due Date: " + this.dueDate + " | Done: " + markIsDone();
    }

    /**
     * Returns a pipe-delimited string for saving to the storage file.
     *
     * @return A storage-formatted string.
     */
    public String toStorageString() {
        assert this.deadlineType != null : "Deadline type should not be null";
        assert this.dueDate != null : "Due date should not be null";

        return this.deadlineType + " | " + this.dueDate + " | " + this.isDone;
    }
}
