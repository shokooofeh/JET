package com.jet.gameofthree.controller;

import static com.jet.gameofthree.domain.GameModeEnum.AI;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import com.jet.gameofthree.ai.AiStompSessionHandler;
import com.jet.gameofthree.dto.GameParticipateRequest;

public class GameOfThreeHelper {

	public static boolean shouldAddAiPlayer(GameParticipateRequest gameParticipateRequest, String aiPlayerName) {
		return AI == gameParticipateRequest.getGameMode()
				&& !aiPlayerName.equals(gameParticipateRequest.getPlayerName());
	}

	public static void addAiParticipant(String aiPlayerName) {
		WebSocketClient client = new StandardWebSocketClient();
		WebSocketStompClient stompClient = new WebSocketStompClient(client);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		stompClient.connect("ws://localhost:8080/game-of-three-websocket", new AiStompSessionHandler(aiPlayerName));
	}

}
