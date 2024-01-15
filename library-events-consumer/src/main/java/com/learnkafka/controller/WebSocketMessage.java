package com.learnkafka.controller;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
//@CrossOrigin("*")
@RequiredArgsConstructor
public class WebSocketMessage {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/library-message")
//    @SendTo()
    public void sendMessage(ConsumerRecord<Integer, String> consumerRecord) {
        System.out.println("consumerRecord.value(): " + consumerRecord.value());
        try {
            messagingTemplate.convertAndSend("/topic/library-events", consumerRecord.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
