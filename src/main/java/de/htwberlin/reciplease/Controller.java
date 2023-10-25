package de.htwberlin.reciplease;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

    // Variante 1 (als Pfadparameter in der URL)
    @GetMapping("/")
    public String willkommen1() {
        return "Willkommen bei ReciPlease";
    }

    @GetMapping("/user")
    public String willkommen1_v1() {
        return "Willkommen bei ReciPlease";
    }

    @GetMapping("/user/{userid}")
    public String willkommen1_v2(@PathVariable int userid) {
        return "Willkommen bei ReciPlease, User " + userid + "!";
    }

    @GetMapping("/user/{userid}/password/{password}")
    public String willkommen1_v3(@PathVariable int userid, @PathVariable String password) {
        return "Willkommen bei ReciPlease, User " + userid + ", dein Passwort ist " + password + "!" ;
    }

    // Variante 2 (als Queryparameter in der URL)
    // localhost:8080/user2?userid=123&password=test
    // localhost:8080/user2?password=test&userid=123&einweitererparam=blabla
    @GetMapping("/user2")
    public String willkommen2(@RequestParam int userid, @RequestParam String password) {
        return "Willkommen bei ReciPlease!, User" + userid + ", dein Passwort ist " + password + "!";
    }

}
