package empapp;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.stream.Collectors;

@EmployeesWebService
@WebService(serviceName = "EmployeeWebService", targetNamespace = EmployeeWebService.EMPAPP_NAMESPACE)
@AllArgsConstructor
public class EmployeeWebService {

    public static final String EMPAPP_NAMESPACE = "http://training360.com/services/empapp";

    private EmployeeService employeeService;

    @WebMethod
    @XmlElementWrapper(name = "employees")
    @WebResult(name = "employee")
    public List<EmployeeWdto> listEmployees() {
        // Külön réteg
        ModelMapper modelMapper = new ModelMapper();
        return employeeService.listEmployees().stream()
                .map(e -> modelMapper.map(e, EmployeeWdto.class)).collect(Collectors.toList());
    }
}
