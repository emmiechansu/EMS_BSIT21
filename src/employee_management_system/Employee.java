package employee_management_system;

public class Employee {

    int id;
    String name;
    String position;
    String department;
    String contactNo;
    String performance;

    public Employee(int id,
                    String name,
                    String position,
                    String department,
                    String contactNo,
                    String performance) {

        this.id = id;
        this.name = name;
        this.position = position;
        this.department = department;
        this.contactNo = contactNo;
        this.performance = performance;
    }
}