package com.example.task775.message.repo;

import com.example.task775.message.model.Sender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SenderRepo extends JpaRepository<Sender, Long> {
    Sender findByName(String name);
}
