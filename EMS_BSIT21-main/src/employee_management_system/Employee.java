package employee_management_system;

public class Employee {

    int id;
    String name;
    String position;
    String department;
    String contactNo;
    String performance;
    String remarks;

    public Employee(int id,
                    String name,
                    String position,
                    String department,
                    String contactNo,
                    String performance,
                    String remarks) {

        this.id = id;
        this.name = name;
        this.position = position;
        this.department = department;
        this.contactNo = contactNo;
        this.performance = performance;
        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getPerformance() {
        return performance;
    }

    public String getRemarks() {
        return remarks;
    }

}
