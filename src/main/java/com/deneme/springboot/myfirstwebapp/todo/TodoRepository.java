package com.deneme.springboot.myfirstwebapp.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoDatas, Integer> {
    public List<TodoDatas> findByUsername(String username);
}
