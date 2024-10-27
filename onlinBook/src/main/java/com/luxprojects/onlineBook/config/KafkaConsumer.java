package com.luxprojects.onlineBook.config;

import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxprojects.onlineBook.dto.AddressRequestDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumer {
	
	//@RetryableTopic(attempts = "3", backoff = @Backoff(delay=300, multiplier=1.5,maxDelay=15000))
	//@KafkaListener(topics = "${topic.name}", groupId = "cons-4")
	public void consume(@Payload AddressRequestDTO addressRequestDTO, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
			@Header(KafkaHeaders.RECORD_METADATA) String offSet) throws JsonProcessingException {
		log.info("message consummer : {} ",new ObjectMapper().writeValueAsString(addressRequestDTO));
	}
	//offset explorer
	//@DltHandler
	public void listenDLT(AddressRequestDTO addressRequestDTO, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
			@Header(KafkaHeaders.RECORD_METADATA) String offSet) {
		log.info("DLT Receive : {}, from {}, offset {}", addressRequestDTO.getAddressName(), topic, offSet);
	}

}
