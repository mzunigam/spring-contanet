package contanet.api.spring.api;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class mainApi {

    @GetMapping("/")
    public String home(){
        return "Hello World! This is a test for Contanet";
    }

}