package com.example.task775.message.service;

import com.example.task775.message.model.Message;
import com.example.task775.message.model.MessageDTO;
import com.example.task775.message.repo.SenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageDtoConverter {
    @Autowired
    private SenderRepo senderRepo;
    public Message convert(MessageDTO messageDTO) {
        Message message = new Message();
        message.setMessage(messageDTO.getMessage());
        message.setSender(senderRepo.findByName(messageDTO.getName()));
        return message;
    }

    public MessageDTO convert(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(message.getMessage());
        messageDTO.setName(message.getSender().getName());
        return messageDTO;
    }

}
