package com.learnkafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learnkafka.domain.LibraryEvent;
import com.learnkafka.producer.LibraryEventsProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


@RestController
@RequiredArgsConstructor
@Slf4j
public class LibraryEventController {

    private final LibraryEventsProducer libraryEventsProducer;


    @PostMapping("/v1/libraryevent")
    public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {

        log.info("libraryEvent : {} ", libraryEvent);

//        libraryEventsProducer.sendLibraryEvent(libraryEvent);

//        libraryEventsProducer.sendlibraryEvent_aproach2(libraryEvent);

        libraryEventsProducer.sendlibraryEvent_aproach3(libraryEvent);

        log.info("After sending libraryEvent : ");

        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }
}
