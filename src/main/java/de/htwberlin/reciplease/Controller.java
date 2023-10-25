package de.htwberlin.reciplease;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String index() {
        return "Willkommen bei ReciPlease!";
    }

    @GetMapping("/user/{userid}/password/{password}")
    public String index2(@PathVariable int userid, @PathVariable String password) {

        return "Willkommen bei ReciPlease!, user " + userid + ", your password is " + password + "!" ;

    }
    //localhost:8080/user?userid=123&password=test
    //localhost:8080/user?password=test&userid=123&einweitererparam=blabla
    @GetMapping("/user")
    public String index3(@RequestParam int userid, @RequestParam String password) {
        return "Willkommen bei ReciPlease!, user" + userid + ", your password is " + password + "!";
   //// Variante 1 (als Pfadparameter in der URL)
        //    @GetMapping("/")
        //    public String bla() {
        //        return "Greetings from Webtech";
        //    }
        //
        //    @GetMapping("/user")
        //    public String bla2() {
        //        return "Greetings from Webtech";
        //    }
        //
        //    @GetMapping("/user/{userid}")
        //    public String bla3(@PathVariable int userid) {
        //        return "Greetings from Webtech, user " + userid + "!";
        //    }
        //
        //    @GetMapping("/user/{userid}/pw/{password}")
        //    public String bla4(@PathVariable int userid, @PathVariable String password) {
        //        return "Greetings from Webtech, user " + userid + ", your password is " + password + "!";
        //    }
        //
        //    // Variante 2 (als Queryparameter in der URL)
        //    @GetMapping("/user")
        //    public String bla5(@RequestParam int userid, @RequestParam String password) {
        //        return "Greetings from Webtech, user " + userid + ", your password is " + password + "!";
        //    }
    }

}