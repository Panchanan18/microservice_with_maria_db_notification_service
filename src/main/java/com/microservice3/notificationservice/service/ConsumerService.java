package com.microservice3.notificationservice.service;

import com.microservice3.notificationservice.constants.KafkaConstants;
import com.microservice3.notificationservice.event.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerService {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @KafkaListener(topics = KafkaConstants.TOPIC,groupId = KafkaConstants.GROUP_ID)
    public void consume(Student student){
        log.info("Notification for the newly added student with id "+ student.getId()+" is received" );
    }

}
