package employee_management_system;

public class Request {

    String employeeName;

    String type,date,amount,reason;

    String status;

    public Request(String employeeName, String type,String date,String amount,String reason,String status) {

        this.employeeName = employeeName;

        this.type = type;

        this.date = date;

        this.amount = amount;

        this.reason = reason;

        this.status = status;
    }
}