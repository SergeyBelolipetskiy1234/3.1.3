package com.example.belolipetsckiy.belolipetsckiy_boot.controllers;

import com.example.belolipetsckiy.belolipetsckiy_boot.models.User;
import com.example.belolipetsckiy.belolipetsckiy_boot.service.RoleService;
import com.example.belolipetsckiy.belolipetsckiy_boot.service.UserRepository;
import com.example.belolipetsckiy.belolipetsckiy_boot.service.UserService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private RoleService roleService;
    private UserRepository repository;

    public UserController(UserService userService, RoleService roleService, UserRepository repository) {
        this.userService = userService;
        this.roleService = roleService;
        this.repository = repository;
    }

    @GetMapping()
    public String index (Model model) {
      //  model.addAttribute("users", user);
       // model.addAttribute("roles", user.getRoles());
        model.addAttribute("user", repository.findAll());
        return "user/index";
    }

  /*  @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", userService.show(id));
        model.addAttribute("roles", user.getRoles());
        return "user/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute User user, @ModelAttribute Role role) {
        List<Role> roles = roleService.getRoles();
        System.out.println("Get controller");
        roles.forEach(System.out::println);
        return "user/new";
    }

    @PostMapping
    public String create(@RequestParam(value = "ADMIN", required = false) String ADMIN,
                         @RequestParam(value = "USER", required = false) String USER, @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "user/new";

        Set<Role> roles = new HashSet<>();
        if(ADMIN != null){
            roles.add(new Role(2L,ADMIN));
        }
        if(USER != null){
            roles.add(new Role(1L,USER));
        }
        if(ADMIN == null && USER == null ){
            roles.add(new Role(1L,USER));
        }

        user.setRoles(roles);
        userService.save(user);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.show(id));
        model.addAttribute("roles", roleService.getRoles());
        return "user/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @RequestParam(value = "ADMIN", required = false) String ADMIN,
                         @RequestParam(value = "USER", required = false) String USER, @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "user/new";

        Set<Role> roles2 = new HashSet<>();
        if(ADMIN != null){
            roles2.add(new Role(2L,ADMIN));
        }
        if(USER != null){
            roles2.add(new Role(1L,USER));
        }
        if(ADMIN == null && USER == null ){
            roles2.add(new Role(1L,USER));
        }

        user.setRoles(roles2);
        userService.update(id, user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/user";
    } */
  @JsonManagedReference
    @GetMapping("/findOne/{id}")
    @ResponseBody
    public User findOne(@PathVariable("id") Integer id) {

        return repository.findById(id).get();
    }


}
