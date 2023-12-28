package com.learnkafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnkafka.entity.LibraryEvent;
import com.learnkafka.jpa.LibraryEventsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LibraryEventsService {

    private final ObjectMapper objectMapper;

    private final LibraryEventsRepository libraryEventsRepository;
    public void processLibraryEvents(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException, IllegalAccessException {
       LibraryEvent libraryEvent=  objectMapper.readValue(consumerRecord.value(), LibraryEvent.class);

        log.info("libraryEvent : {} ", libraryEvent);

        save(libraryEvent);

        switch (libraryEvent.getLibraryEventType()) {
            case NEW ->{
                save(libraryEvent);
                break;
            }
            case UPDATE -> {
                validateLibraryEvent(libraryEvent);
//                save(libraryEvent);
                break;
            }
            default -> log.info("invalid library event type ");
        }
    }

    private void validateLibraryEvent(LibraryEvent libraryEvent) throws IllegalAccessException {

        if(libraryEvent.getLibraryEventId() == null) {
            throw new IllegalAccessException("Library Event id is missing");
        }

        Optional<LibraryEvent> libraryEventOptional = libraryEventsRepository.findById(libraryEvent.getLibraryEventId());

        if(!libraryEventOptional.isPresent()) {
            throw new IllegalArgumentException("Not a valid library event");
        }
        save(libraryEventOptional.get());

        log.info("Validation is successfly for the library event : {} ", libraryEventOptional.get());

    }

    private void save(LibraryEvent libraryEvent) {
        libraryEvent.getBook().setLibraryEvent(libraryEvent);
        libraryEventsRepository.save(libraryEvent);
        log.info(" Successfully persisted the library event : {} ", libraryEvent);
    }
}
