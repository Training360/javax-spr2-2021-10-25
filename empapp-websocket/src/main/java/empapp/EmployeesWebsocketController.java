package empapp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeesWebsocketController {

    @MessageMapping("/messages")
    @SendTo("/topic/employees")
    public MessageWebsocketDto sendMessage(MessageCommandWebsocketDto command) {
        return new MessageWebsocketDto("Reply: " + command.getContent());
    }
}
