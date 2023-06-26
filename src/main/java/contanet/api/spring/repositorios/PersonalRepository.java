package contanet.api.spring.repositorios;

import contanet.api.spring.modelos.Personal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends MongoRepository<Personal, Integer> {
    Personal findByUsuario(String usuario);
}