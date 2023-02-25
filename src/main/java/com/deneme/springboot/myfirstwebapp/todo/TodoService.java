package com.deneme.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;



@Service
public class TodoService {
    //For id
    private static int todosCount=0;
    private static List<TodoDatas> todos=new ArrayList<>();
    //For initialize
    static {
                todos.add(new TodoDatas(++todosCount,"Meric","Learn Spring 1 ", LocalDate.now().plusDays(5),false));
                todos.add(new TodoDatas(++todosCount,"Meric","Learn Full Stack 1", LocalDate.now().plusDays(10),false));
                todos.add(new TodoDatas(++todosCount,"Meric","Learn Hibernate 1", LocalDate.now().plusDays(15),false));
    }

    public static List<TodoDatas> findByUsername(String username) {
        Predicate<? super TodoDatas>predicate
                =todoDatas -> todoDatas.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }
    public void addTodo(String userName,String description,LocalDate targetDate,boolean done){
        TodoDatas todo=new TodoDatas(++todosCount,userName,description,targetDate,done);
        todos.add(todo);
    }
    public void deleteTodo(int id){
        Predicate<? super TodoDatas>predicate
                =todoDatas -> todoDatas.getId()==id;
        todos.removeIf(predicate);
    }


    public TodoDatas findById(int id) {
        Predicate<? super TodoDatas>predicate
                =todoDatas -> todoDatas.getId()==id;
        TodoDatas todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(@Valid TodoDatas todo) {
        deleteTodo(todo.getId());
        todos.add(todo);
    }
}
