package contanet.api.spring.repositorios;

import contanet.api.spring.modelos.Modulo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ModuloRepository extends MongoRepository<Modulo, Integer>{
}