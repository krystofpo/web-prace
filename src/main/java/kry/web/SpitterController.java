package kry.web;

import kry.bizlogic.Spitter;
import kry.data.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by polansky on 6.12.2017.
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String showRegistrationForm() {
        return "registerForm";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String register(@Valid Spitter spitter, Errors errors){
        if (errors.hasErrors()) {
           return "registerForm";
        }
        spitterRepository.save(spitter);
        return "redirect:/spitter/" + spitter.getLogin();
    }

    @RequestMapping(value="/{login}", method=RequestMethod.GET)
    public String showUser(@PathVariable("login") String login, Model model) {
        Spitter spitter = spitterRepository.findByLogin(login);
        model.addAttribute(spitter);
        return "profile";
    }

}
