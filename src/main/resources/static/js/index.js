var socket = new SockJS('/ws');
var stompClient = Stomp.over(socket);

document.addEventListener('DOMContentLoaded', () => {
    var appElement;
    var userRole;
    var senderUsername;
    var senderId;
    var receiverUsername;
    var chatId;

    initVariables()
    initStompClient()
    fetchChatMessages()
})


function fetchChatMessages() {
    fetch(`/api/chat/${chatId}/messages`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(dataArray => {
            dataArray.forEach(item => {
                document.getElementById('response').appendChild(buildMessageDiv(item.username, item.timestamp, item.content));
            });
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
            document.getElementById('response').innerText = 'Failed to load data.';
        });
}

function initVariables() {
    appElement = document.getElementById('app');
    userRole = appElement.getAttribute('data-user-role');
    senderUsername = appElement.getAttribute('data-sender-username');
    senderId = appElement.getAttribute('data-sender-id');
    receiverUsername = appElement.getAttribute('data-receiver-username');
    chatId = appElement.getAttribute('data-chat-id');
}

function initStompClient() {
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        // Subscribe to a topic
        stompClient.subscribe(`/topic/chat/${chatId}`, function (item) {
            const jsonObj = JSON.parse(item.body);
            document.getElementById('response').appendChild(buildMessageDiv(jsonObj.username, jsonObj.timestamp, jsonObj.content))
        });

        // Subscribe to error messages
        stompClient.subscribe('/user/queue/errors', function (errorMessage) {
            alert('Error: ' + errorMessage.body);
        });
    });
}

function sendMessage() {
    var messagePayload = JSON.stringify({
        content: document.getElementById("messageInput").value,
        userId: senderId,
        chatId: chatId
    });
    document.getElementById("messageInput").value = "";
    stompClient.send("/web/message/" + chatId, {}, messagePayload);
}

function buildMessageDiv(username, timestamp, content) {
    const msgContainer = document.createElement("div");
    const msgOrientation = (username === senderUsername) ? "sender" : "receiver"
    const msgDiv = Object.assign(document.createElement('div'), {className: 'chat-container ' + msgOrientation})
    msgContainer.appendChild(msgDiv);
    msgDiv.appendChild(Object.assign(document.createElement('div'), {className: 'username', textContent: username}));
    msgDiv.appendChild(Object.assign(document.createElement('div'), {className: 'timestamp', textContent: timestamp}));
    msgDiv.appendChild(Object.assign(document.createElement('div'), {className: 'content', textContent: content}));

    return msgContainer
}

