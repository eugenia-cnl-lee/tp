package seedu.interntrackr.model;

public class Application {
    private String companyName;
    private String role;
    private String status;

    public Application(String companyName, String role) {
        this.companyName = companyName;
        this.role = role;
        this.status = "Applied"; //set to applied by default upon addition
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[" + status + "] " + companyName + " - " + role;
    }
}
