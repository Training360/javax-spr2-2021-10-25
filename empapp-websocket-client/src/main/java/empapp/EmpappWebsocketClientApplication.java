package empapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class EmpappWebsocketClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmpappWebsocketClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World!");
		//WebSocketClient client = new StandardWebSocketClient();
		WebSocketClient client = new SockJsClient(
				Arrays.asList(new WebSocketTransport(new StandardWebSocketClient())));
		WebSocketStompClient stompClient = new WebSocketStompClient(client);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		StompSessionHandler sessionHandler = new MessageStompSessionHandler();
		StompSession stompSession = stompClient.connect("ws://localhost:8080/websocket-endpoint", sessionHandler).get();
		inputMessages(stompSession);
	}

	public void inputMessages(StompSession stompSession) {
		Scanner scanner = new Scanner(System.in);
		String input = null;
		while (input == null || !input.trim().equals("")) {
			input = scanner.nextLine();
			stompSession.send("/app/messages", new MessageCommandDto(input));
		}
	}
}
