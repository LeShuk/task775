package com.example.task775.message.controller;

import com.example.task775.message.model.MessageDTO;
import com.example.task775.message.repo.MessageRepo;
import com.example.task775.message.service.MessageDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private MessageDtoConverter messageDtoConverter;

    private static final  String COMMAND_MESSAGE = "history 10";

    //По хорошему, нужно разделить эндпойнты.
    // Допустим, по GET-запросу выдавать список сообщений, с параметрами пагинации,
    // а POST-запрос использовать только для сохранения сообщений.
    // Более того, так как пользователь может сохранять сообщения только от своего имени,
    // можно избавиться от части "name": "..." в передаваемом json`e: по токену отправитель и так известен.
    @PostMapping("/messages")
    @ResponseBody
    List<MessageDTO> messageService(@RequestBody MessageDTO messageDTO, Principal principal) {
        List<MessageDTO> messageDTOList = new ArrayList<>();
        if ((messageDTO != null) && (messageDTO.getName().equals(principal.getName()))) {
            if (messageDTO.getMessage().equals(COMMAND_MESSAGE)) {
                messageDTOList = messageRepo.findTop10BySenderNameOrderByIdDesc(principal.getName())
                        .stream().map(message -> messageDtoConverter.convert(message)).toList();
            } else {
                messageRepo.saveAndFlush(messageDtoConverter.convert(messageDTO));
            }
        }
        return messageDTOList;
    }

}
