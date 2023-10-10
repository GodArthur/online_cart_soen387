
package soen.online_store.java;

/**
 *
 * @author Kojo
 */
public class Employee extends User{
    
    private String employeeId;
    private String startDate;
    private String endDate;
    private String title;

    public Employee(String employeeId, String startDate, String endDate, String title, String userID, String username, String password, String role) {
        super(userID, username, password, role);
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
