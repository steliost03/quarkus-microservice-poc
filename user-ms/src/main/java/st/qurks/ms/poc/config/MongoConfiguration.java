package st.qurks.ms.poc.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "mongo")
public interface MongoConfiguration {

    @WithName("user.database")
    String userDatabase();

    @WithName("user.collection")
    String userCollection();

}
