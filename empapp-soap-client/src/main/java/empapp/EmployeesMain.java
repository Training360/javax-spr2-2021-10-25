package empapp;

import com.training360.services.empapp.EmployeeWdto;
import com.training360.services.empapp.EmployeeWebService_Service;
import com.training360.services.empapp.ListEmployees;

import java.net.URL;

public class EmployeesMain {

    public static void main(String[] args) throws Exception {
        var service = new EmployeeWebService_Service(new URL("http://localhost:8080/services/employees?wsdl"));
        var port = service.getEmployeeWebServicePort();
        var employees = port.listEmployees();
        employees.getEmployee().stream().map(EmployeeWdto::getName)
                .forEach(System.out::println);

    }
}
