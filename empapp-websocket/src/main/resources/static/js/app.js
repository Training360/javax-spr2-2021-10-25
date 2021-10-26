window.onload = function() {
    const socket = new SockJS('/websocket-endpoint');
    const stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/employees', function (message) {
            const text = JSON.parse(message.body).text;
            console.log(text);
            document.querySelector("#message-div").innerHTML +=
                "<p>" + text + "</p>";
        });
    });

    document.querySelector("#message-button").onclick = function() {
        let content = document.querySelector("#message-input").value;
        stompClient.send("/app/messages", {}, JSON.stringify({"content": content}));
        return false;
    };
}
