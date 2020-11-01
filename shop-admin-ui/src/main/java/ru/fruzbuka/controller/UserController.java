package ru.fruzbuka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.fruzbuka.persist.entity.Role;
import ru.fruzbuka.persist.entity.User;
import ru.fruzbuka.service.RoleService;
import ru.fruzbuka.service.UserService;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String allUsers(Model model) {
        model.addAttribute("activePage", "Users");
        model.addAttribute("users", userService.getAll());
        System.out.println(userService.getAll());
        return "users";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        logger.info("Create user");
        User user = new User();
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAll());
        return "user";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.getById(id).orElseThrow(() ->
                new NotFoundException("User " + id + " not found", "User")
        );
        logger.info("Edit user {} ", user);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAll());
        return "user";
    }

    @PostMapping("/update")
    public String updateUser(Model model,  @Valid User user, @RequestParam Map<String, String> form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user";
        }
        if (!user.getPassword().equals(user.getMatchingPassword())) {
            bindingResult.rejectValue("matchingPassword", "password.no.match", "Введенные пароли не совпадают");
            return "user";
        }

        Set<String> roles = roleService.getAll().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(roleService.getByName(key));
            }
        }

        logger.info("Update user {} ", user);
        model.addAttribute("activePage", "Users");
        userService.saveOrUpdate(user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(Model model, @PathVariable("id") Long id) {
        logger.info("Delete user by id {} ", id);
        model.addAttribute("activePage", "Users");
        userService.deleteById(id);
        return "redirect:/user";
    }
}
