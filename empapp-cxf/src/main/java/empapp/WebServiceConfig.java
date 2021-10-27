package empapp;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {

    @Bean
    public Endpoint employeeEndpoint(EmployeeWebService employeeWebService, Bus bus) {
        EndpointImpl endpoint = new EndpointImpl(bus, employeeWebService);
//        endpoint.setPublishedEndpointUrl("http://training.com/services/employees");
        endpoint.publish("/employees");
        return endpoint;
    }
}
