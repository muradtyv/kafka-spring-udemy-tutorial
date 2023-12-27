package com.learnkafka.domain;

//org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration

public record Book(
        Integer bookId,
        String bookName,
        String bookAuthor
) {
}
