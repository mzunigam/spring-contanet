package contanet.api.spring.api;

import contanet.api.spring.modelos.Carpeta;
import contanet.api.spring.modelos.CarpetaDTO;
import contanet.api.spring.repositorios.CarpetaRepository;
import contanet.api.spring.servicios.CarpetaService;

import java.util.List;

import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class CarpetaApi {

    final CarpetaRepository carpetaRepository;
    final CarpetaService carpetaService;

    public CarpetaApi(CarpetaRepository carpetaRepository, CarpetaService carpetaService) {
        this.carpetaRepository = carpetaRepository;
        this.carpetaService = carpetaService;
    }

    @GetMapping("/carpeta")
    public List<Carpeta> getCampos() {
       return carpetaRepository.findAll();
    }

    @GetMapping("/carpeta/get")
    public List<CarpetaDTO> getCamposDTO() {
        return carpetaService.getCarpetas();
    }

    @PostMapping("/carpeta")
    public Carpeta savePersonal(@RequestBody Carpeta carpeta) {
        return carpetaRepository.save(carpeta);
    }

    @GetMapping("/carpeta/{id}")
    public Carpeta getPersonal(@PathVariable("id") Integer id) {
        return carpetaRepository.findById(id).get();
    }

    @PutMapping("/carpeta")
    public Carpeta updatePersonal(@RequestBody Carpeta carpeta) {
        return carpetaRepository.save(carpeta);
    }

    @DeleteMapping("/carpeta/{id}")
    public String deletePersonal(@PathVariable("id") Integer id) {
        carpetaRepository.deleteById(id);
        return "Carpeta eliminada satisfactoriamente";
    }
    
}
