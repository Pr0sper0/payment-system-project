package org.val.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.val.dto.RoleCreateEditDto;
import org.val.service.RoleService;

@Controller
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "roles";
    }

    @GetMapping("/{id}")
    public String findById(Model model, Integer id) {
        return roleService.findById(id)
                        .map(role -> {
                            model.addAttribute("role", role);
                            return "role/role";
                        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(RoleCreateEditDto role) {
        return "redirect:/roles/" + roleService.create(role).id();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, @ModelAttribute RoleCreateEditDto role) {
        return roleService.update(id, role)
                .map(roleReadDto -> "redirect:/roles/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!roleService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        };
        return "redirect:/roles";
    }
}
