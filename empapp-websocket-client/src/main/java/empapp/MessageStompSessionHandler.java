package empapp;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;

public class MessageStompSessionHandler implements StompSessionHandler {

    @Override
    public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
        System.out.println("Connected");
        stompSession.subscribe("/topic/employees", this);
    }

    @Override
    public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {
        throw new RuntimeException(throwable);
    }

    @Override
    public void handleTransportError(StompSession stompSession, Throwable throwable) {
        throw new RuntimeException(throwable);
    }

    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {
        return MessageDto.class;
    }

    @Override
    public void handleFrame(StompHeaders stompHeaders, Object o) {
        MessageDto messageDto = (MessageDto)o;
        System.out.println(messageDto.getText());
    }
}
