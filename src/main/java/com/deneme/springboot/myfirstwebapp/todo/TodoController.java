package com.deneme.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
@SessionAttributes("name")
public class TodoController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        super();
        this.todoService = todoService;
    }


    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap modelMap) {
        List<TodoDatas> todos = todoService.findByUsername("Meric");
        modelMap.put("todos", todos);
        return "listTodos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodos(ModelMap model) {
        String name = (String) model.get("name");
        TodoDatas todo = new TodoDatas(0, name, "", LocalDate.now().plusYears(1), false);
        //ModelAttributes in jsp file
        model.put("todo", todo);
        return "addTodos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid TodoDatas todo, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("todo", todo);
            return "addTodos";
        }
        String username = (String) model.get("name");
        todoService.addTodo(username, todo.getDescription(),
                todo.getTargetDate(), false);
        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteTodo(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        TodoDatas todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "addTodos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid TodoDatas todo, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("todo", todo);
            return "addTodos";
        }
        String username = (String) model.get("name");
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }
}
