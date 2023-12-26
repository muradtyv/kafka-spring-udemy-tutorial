package com.learnkafka.domain;

public record LibraryEvent(
        Integer libraryEvemtId,
        LibraryEventType libraryEventType,
        Book book
) {
}
