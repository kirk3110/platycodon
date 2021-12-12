let stompClient = null;
const roomId = location.href.split("/").pop();

function connect(roomId) {
  const socket = new SockJS('/websocket');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function () {
    stompClient.subscribe(`/receive/messages/${roomId}`, function (response) {
      const messages = JSON.parse(response.body)
      for (const index in messages) {
        showMessage(messages[index]);
      }
    });
    stompClient.subscribe(`/receive/character/${roomId}`, function (response) {
      const character = JSON.parse(response.body)
      updateCharacter(character);
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
    const paramName = $(`#character-param-name${i + 1}`).val();
    const param = $(`#character-param-value${i + 1}`).val();
    if (paramName[0] !== "*") {
      characterParams[paramName] = param;
    } else {
      if (param === "on") {
        characterParams[paramName] = "1";
      } else {
        characterParams[paramName] = "0";
      }
    }
  }

  stompClient.send(`/send/character/${roomId}`, {}, JSON.stringify(
      {
        'characterId': $("#character-id").val(),
        'name': $("#character-name").val(),
        'initiative': $("#character-initiative").val(),
        'params': characterParams
      }
  ));

  $("#character-id").val('');
  $("#character-name").val('');
  $("#character-initiative").val('');
  for (let i = 0; i < $("#character-params-count").val(); i++) {
    $(`#character-param-value${i + 1}`).val('');
  }
}

function updateCharacter(character) {
  let characterRow = `<tr id="character${character.characterId}">`
      + "<td>" + character.initiative + "</td>"
      + "<td>" + character.name + "</td>";
  for (let i = 0; i < $("#character-params-count").val(); i++) {
    const paramName = $(`#character-param-name${i + 1}`).val();
    if (paramName[0] !== "*") {
      characterRow += "<td>" + character.params[paramName] + "</td>";
    } else {
      if (character.params[paramName] === "1") {
        characterRow += "<td>■</td>";
      } else {
        characterRow += "<td></td>";
      }
    }

  }
  characterRow += "</tr>";

  if (typeof $(`#character${character.characterId}`) !== "undefined") {
    $("#characters-table").remove($(`#character${character.characterId}`))
  }
  insertIntoCharacterTable(characterRow, character.initiative);
}

function insertIntoCharacterTable(characterRow, initiative) {
  let inserted = false;
  $("#characters-table tr").each(function (index, element) {
    if(Number(initiative) > Number($(element).find("td").eq(0).text())) {
      $(element).before(characterRow);
      inserted = true;
      return false;
    }
  });
  if (!inserted) {
    $("#characters-table").append(characterRow);
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

