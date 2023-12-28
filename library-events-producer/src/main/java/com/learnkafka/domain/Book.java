package com.learnkafka.domain;

//org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration

import jakarta.validation.constraints.NotBlank;

public record Book(
        @NotBlank
        Integer bookId,
        @NotBlank
        String bookName,
        @NotBlank
        String bookAuthor
) {
}
