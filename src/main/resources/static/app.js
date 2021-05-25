var stompClient = null;

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
  } else {
    $("#conversation").hide();
  }
  $("#message").html("");
}

function connect() {
  var socket = new SockJS('/websocket');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/receive/messages', function (response) {
      const messages = JSON.parse(response.body)
      for (const index in messages) {
        showMessage(messages[index]);
      }
    });
  });
}

function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log("Disconnected");
}

function sendMessage() {
  stompClient.send("/send/message", {}, JSON.stringify(
      {'name': $("#name").val(), 'statement': $("#statement").val()}));
  $("#statement").val('');
}

function showMessage(message) {
  $("#message").append(
      "<tr><td>" + message.name + ": " + message.statement + "</td></tr>");
}

function setHeight() {
  $("chat").style.height = $("chat").contentWindow.document.body.scrollHeight
      + "px";
}

$(function () {
  $("form").on('submit', function (e) {
    e.preventDefault();
  });
  $("#connect").click(function () {
    connect();
  });
  $("#disconnect").click(function () {
    disconnect();
  });
  $("#send").click(function () {
    sendMessage();
  });
  $(window).on('load resize', function () {
    $("#chat").css('height', $(window).height());
  });
});

setTimeout("connect()", 3000);