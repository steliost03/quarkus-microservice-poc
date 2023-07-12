package st.qurks.ms.poc.repository;

import st.qurks.ms.poc.model.mongodb.entity.UserEntityPanache;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMongoRepositoryPanache implements ReactivePanacheMongoRepository<UserEntityPanache> {
}
