package com.deneme.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;


@Controller
@SessionAttributes("name")
public class TodoControllerJpa {

    private TodoService todoService;
    private TodoRepository todoRepository;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public TodoControllerJpa(TodoService todoService, TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository = todoRepository;

    }


    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap modelMap) {
        String username = getLoggedInUsername(modelMap);
        List<TodoDatas> todos = todoRepository.findByUsername(username);

        modelMap.put("todos", todos);
        return "listTodos";
    }


    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodos(ModelMap model) {
        String name = getLoggedInUsername(model);
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
        String username = getLoggedInUsername(model);
        todo.setUsername(username);
        //
        todoRepository.save(todo);
//        todoService.addTodo(username, todo.getDescription(),
//                todo.getTargetDate(), false);
        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
//        todoService.deleteTodo(id);
        todoRepository.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        //TodoDatas todo = todoService.findById(id);
        TodoDatas todo = todoRepository.findById(id).get();
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
        String username = getLoggedInUsername(model);
        todo.setUsername(username);
        todoRepository.save(todo);
        //todoService.updateTodo(todo);
        return "redirect:list-todos";
    }

    private static String getLoggedInUsername(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
