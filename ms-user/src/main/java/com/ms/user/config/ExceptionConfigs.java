package com.ms.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component  //component marca que esto es un bean y que puede usar utilizado transversalmente en toda la app (y se inyectan dep auto)
@ConfigurationProperties(prefix = "control")
public class ExceptionConfigs {
    private Map<String,String> exception; // Debe llamarse igual al que voy a mapear
    public static final String BUSINESS = "business";
    public static final String SYSTEM = "system";

    public String getTypeException(String typeException) {
        return exception.get(typeException);
    }
}
