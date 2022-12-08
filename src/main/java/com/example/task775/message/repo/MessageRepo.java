package com.example.task775.message.repo;

import com.example.task775.message.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
    List<Message> findTop10BySenderNameOrderByIdDesc(String name);
}
