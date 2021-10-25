package empapp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
public class EmployeeJob extends QuartzJobBean {

    private EmployeeService employeeService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<EmployeeDto> employees = employeeService.listEmployees();
        log.info("Employees: {}", employees.stream().map(EmployeeDto::getName).collect(Collectors.joining(", ")));
    }
}
