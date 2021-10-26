const evtSource = new EventSource("api/employees/messages");
evtSource.addEventListener("created-message",
    function(event) {
        const text = JSON.parse(event.data).message;
        console.log(text);
        document.querySelector("#message-div").innerHTML +=
            `<p>${text}</p>`;
    });