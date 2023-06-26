package contanet.api.spring.repositorios;

import contanet.api.spring.modelos.Campo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CampoRepository extends MongoRepository<Campo, Integer> {
}