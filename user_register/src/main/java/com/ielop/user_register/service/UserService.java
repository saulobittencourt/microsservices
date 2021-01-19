package com.ielop.user_register.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ielop.user_register.gateway.json.User;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class UserService {

    private final ReplyingKafkaTemplate<String, String, String> kafkaTemplate;

    public String salvar(User user) throws JsonProcessingException, ExecutionException, InterruptedException {
        String requestTopic = "requesttopic2";
        String requestReplyTopic = "replytopic2";

        // Convertendo objeto em string
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(user);

        // Montando o producer q será enviado pelo kafka
        ProducerRecord<String, String> record = new ProducerRecord<>(requestTopic, jsonString);
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));

        // Enviando
        RequestReplyFuture<String, String, String> sendAndReceive = kafkaTemplate.sendAndReceive(record);

        // Recebendo o retorno
        SendResult<String, String> sendResult = sendAndReceive.getSendFuture().get();
        sendResult.getProducerRecord().headers().forEach(header -> System.out.println(header.key() + ":"+header.value().toString()));

        ConsumerRecord<String, String> consumerRecord = sendAndReceive.get();

        User userReturn = mapper.readValue(consumerRecord.value(), User.class);

        return userReturn.getId();
    }
}
