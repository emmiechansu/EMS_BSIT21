package employee_management_system;

public class Request {
    public String type, date, amount, reason, status;

    public Request(String type, String date, String amount, String reason, String status) {
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
    }
}