package VagasGaragem.Vagas.Controller;

import VagasGaragem.Vagas.Models.UserData;
import VagasGaragem.Vagas.Service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class RestControllerGeneral {

    @Autowired
    public UserDataService userDataService;

    @GetMapping("/{name}")
    public UserData getByname(@PathVariable String name){
        return userDataService.findByName(name);
    }

    @PostMapping()
    public UserData postUser(@RequestBody UserData userData){
        return userDataService.save(userData);
    }
}
