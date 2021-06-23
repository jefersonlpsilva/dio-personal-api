package dinamismo.app.personapi.config;

import dinamismo.app.personapi.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DbService dbService;

    @Bean
    public boolean instanceTestConfig(){

        dbService.inicializeTestDataBase();

        return true;
    }

}
