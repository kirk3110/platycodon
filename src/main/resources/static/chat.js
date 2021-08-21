var stompClient = null;
const roomId = location.href.split("/").pop();

function connect(roomId) {
  var socket = new SockJS('/websocket');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function () {
    stompClient.subscribe(`/receive/messages/${roomId}`, function (response) {
      const messages = JSON.parse(response.body)
      for (const index in messages) {
        showMessage(messages[index]);
      }
    });
  });
}

function sendMessage(roomId) {
  if ($("#statement").val() !== '') {
    stompClient.send(`/send/message/${roomId}`, {}, JSON.stringify(
        {
          'name': $("#name").val(),
          'statement': $("#statement").val(),
          'sentAt': new Date()
        }));
  }
  $("#statement").val('');
}

function showMessage(message) {
  $("#message").append(
      "<tr><td>" + message.name + "：" + message.statement + "</td></tr>");
  scrollMessagesToBottom();
}

function scrollMessagesToBottom() {
  $("#chat-messages").scrollTop($("#chat-messages")[0].scrollHeight);
}

setTimeout("connect(roomId)", 1000);

$(function () {
  scrollMessagesToBottom();
  $("form").on('submit', function (e) {
    e.preventDefault();
  });
  $("#send").click(function () {
    sendMessage(roomId);
  });
});

