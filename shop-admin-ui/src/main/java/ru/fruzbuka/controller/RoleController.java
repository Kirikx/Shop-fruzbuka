package ru.fruzbuka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.fruzbuka.exceptions.NotFoundException;
import ru.fruzbuka.persist.entity.Role;
import ru.fruzbuka.service.RoleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    private final static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @GetMapping
    public String allRoles(Model model) {
        model.addAttribute("activePage", "Roles");
        model.addAttribute("roles", roleService.getAll());
        return "roles";
    }

    @GetMapping("/create")
    public String createRole(Model model) {
        logger.info("Create role");
        Role role = new Role();
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", role);
        return "role";
    }

    @GetMapping("/{id}")
    public String editRole(@PathVariable("id") Long id, Model model) {
        Role role = roleService.getById(id).orElseThrow(() ->
                new NotFoundException("Role " + id + " not found", "Role")
        );
        logger.info("Edit role {} ", role);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", role);
        return "role";
    }

    @PostMapping("/update")
    public String updateRole(Model model, @Valid Role role, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "role";
        }
        logger.info("Update role {} ", role);
        model.addAttribute("activePage", "Roles");
        roleService.saveOrUpdate(role);
        return "redirect:/role";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteRole(Model model, @PathVariable("id") Long id) {
        logger.info("Delete role by id {} ", id);
        model.addAttribute("activePage", "Roles");
        roleService.deleteById(id);
        return "redirect:/role";
    }
}
