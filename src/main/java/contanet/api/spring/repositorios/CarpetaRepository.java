package contanet.api.spring.repositorios;

import contanet.api.spring.modelos.Carpeta;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarpetaRepository extends MongoRepository<Carpeta, Integer> {
}