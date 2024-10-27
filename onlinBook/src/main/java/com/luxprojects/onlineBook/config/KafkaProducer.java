package com.luxprojects.onlineBook.config;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.luxprojects.onlineBook.dto.AddressRequestDTO;

/*import java.util.Properties;

import org.apache.kafka.clients.producer.Producer;*/

@Service
public class KafkaProducer {
	
	/*Properties prop = new Properties();
	prop.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
	prop.put("key.serializer", "org.apacha.kafka.common.serialization.StringSerializer");
	prop.put("value.serializer", "org.apacha.kafka.common.serialization.StringSerializer");
    Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(prop);*/
	@Autowired
	private KafkaTemplate<String, Object> template;
	
	public void sendMessageProducer(String message) {
		try {
			CompletableFuture<SendResult<String, Object>> future =  template.send("${topic.name}", message);
			future.whenComplete((result, ex) -> {
				if (ex == null) {
					System.out.println("send message=[" + message + "] with offset=["+result.getRecordMetadata().offset()+"]");
				} else  {
					System.out.println("unable to send message=["+message+"] due to : "+ex.getMessage());
				}
			});
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void publishBook(AddressRequestDTO address) {
		CompletableFuture<SendResult<String, Object>> future =  template.send("${topic.name}", address);
		future.whenComplete((result, ex) -> {
			if (ex == null) {
				System.out.println("send message=[" + address + "] with offset=["+result.getRecordMetadata().offset()+"]");
			} else  {
				System.out.println("unable to send message=["+address+"] due to : "+ex.getMessage());
			}
		});
	}
}
