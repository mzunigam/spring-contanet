package contanet.api.spring.api;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import contanet.api.spring.modelos.Modulo;
import contanet.api.spring.repositorios.ModuloRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ModuloApi {

    @Autowired
    private ModuloRepository moduloRepository;

    @GetMapping("/modulo")
    public List<Modulo> getModulos() {
        return moduloRepository.findAll();
    }

    @PostMapping("/modulo")
    public Modulo saveModulo(@RequestBody Modulo modulo) {
        return moduloRepository.save(modulo);
    }

    @GetMapping("/modulo/{id}")
    public Modulo getModulo(@PathVariable("id") Integer id) {
        Optional<Modulo> modulo = moduloRepository.findById(id);
        if (modulo.isPresent()) {
            return modulo.get();
        } else {
            return null;
        }
    }

    @PutMapping("/modulo")
    public Modulo updateModulo(@RequestBody Modulo modulo) {
        return moduloRepository.save(modulo);
    }

    @DeleteMapping("/modulo/{id}")
    public String deleteModulo(@PathVariable("id") Integer id) {
        Optional<Modulo> modulo = moduloRepository.findById(id);
        if (modulo.isPresent()) {
            moduloRepository.delete(modulo.get());
            return new JSONObject().put("message", "Modulo eliminado satisfactoriamente").put("status", true).toString();
        } else {
            return new JSONObject().put("message", "Modulo no encontrado").put("status", false).toString();
        }
    }
    
}
