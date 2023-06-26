package contanet.api.spring.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contanet.api.spring.modelos.Carpeta;
import contanet.api.spring.modelos.CarpetaDTO;
import contanet.api.spring.repositorios.CarpetaRepository;
import contanet.api.spring.repositorios.ModuloRepository;

@Service
public class CarpetaService {

    @Autowired
    CarpetaRepository carpetaRepository;
    @Autowired
    ModuloRepository moduloRepository;


    public List<CarpetaDTO> getCarpetas() {

        List<Carpeta> lista = carpetaRepository.findAll();

        if(lista.size() != 0){
            List<CarpetaDTO> listaDTO = lista.stream().map(carpeta -> {
                CarpetaDTO carpetaDTO = new CarpetaDTO();
                carpetaDTO.setId_carpeta(carpeta.getId_carpeta());
                carpetaDTO.setNomb_carpeta(carpeta.getNomb_carpeta());
                carpetaDTO.setDescripcion(carpeta.getDescripcion());
                carpetaDTO.setId_modulo(carpeta.getId_modulo());
                String nomb_modulo = moduloRepository.findById(carpeta.getId_modulo()).get().getNomb_modulo();
                carpetaDTO.setNomb_modulo(nomb_modulo);
                return carpetaDTO;
            }).collect(Collectors.toList());

            return listaDTO;
        }else{
            return null;
        }
    }
    
}
