package com.learnkafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learnkafka.controller.WebSocketMessage;
import com.learnkafka.service.LibraryEventsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class LibraryEventConsumer {

    private final LibraryEventsService libraryEventsService;

    private final WebSocketMessage webSocketMessage;

    @KafkaListener(topics = {"library-events"})
    public void onMessage(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException, IllegalAccessException {
        log.info("ConsumerREcord : {} ", consumerRecord);

        libraryEventsService.processLibraryEvents(consumerRecord);

        webSocketMessage.sendMessage(consumerRecord);
    }
}
