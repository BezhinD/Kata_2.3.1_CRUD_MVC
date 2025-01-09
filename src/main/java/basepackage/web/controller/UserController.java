package basepackage.web.controller;

import basepackage.dao.UserDAO;
import basepackage.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userDAO.getUsers());

        return "/user/index";
    }

    @GetMapping("/")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userDAO.show(id));
        return "user/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "user/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/new";
        }
        userDAO.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute("user", userDAO.show(id));
        return "user/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @RequestParam("id") int id) {
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }
        userDAO.update(id, user);
        return "redirect:/users";
    }

    @PostMapping("/")
    public String delete(@RequestParam("id") int id) {
        userDAO.delete(id);
        return "redirect:/users";
    }
}
