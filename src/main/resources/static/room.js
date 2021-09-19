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
          'sentAt': new Date(),
          'color': $("#color").val()
        }));
  }
  $("#statement").val('');
}

function showMessage(message) {
  $("#chat-messages-table").append(
      "<tr style='color: " + message.color + ";'><td>"
      + message.name + "："
      + message.statement + "</td></tr>");
  scrollMessagesToBottom();
}

function scrollMessagesToBottom() {
  $("#chat-messages").scrollTop($("#chat-messages")[0].scrollHeight);
}

function sendCharacter(roomId) {
  let characterParams = {};
  for (let i = 0; i < $("#character-params-count").val(); i++) {
    characterParams[$(`#character-param-name${i+1}`).val()] = $(`#character-param-value${i+1}`).val();
  }

  stompClient.send(`/send/character/${roomId}`, {}, JSON.stringify(
      {
        'character_id': $("#character-id").val(),
        'name': $("#character-name").val(),
        'initiative': $("#character-initiative").val(),
        'params': JSON.stringify(characterParams)
      }
  ));

  $("#character-id").val('');
  $("#character-name").val('');
  $("#character-initiative").val('');
  for (let i = 0; i < $("#character-params-count").val(); i++) {
    $(`#character-param-value${i+1}`).val('');
  }
}

setTimeout("connect(roomId)", 1000);

$(function () {
  scrollMessagesToBottom();
  $("form").on('submit', function (e) {
    e.preventDefault();
  });
  $("#send-message").click(function () {
    sendMessage(roomId);
  });
  $("#send-character").click(function () {
    sendCharacter(roomId);
  });
});

