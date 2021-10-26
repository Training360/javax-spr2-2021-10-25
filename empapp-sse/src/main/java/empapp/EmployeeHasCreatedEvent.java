package empapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
@AllArgsConstructor
public class EmployeeHasCreatedEvent {

    private String message;
}
