package com.adriano.sqsxmlmessaging.infrastructure.config;

import com.opengamma.elsql.ElSqlBundle;
import com.opengamma.elsql.ElSqlConfig;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ElsqlBundleFactory {

    public ElSqlBundle createElSqlBundle(Class<?> clazz) {
        String fileName = "sql/" + clazz.getSimpleName() + ".elsql";
        Resource elsqlResource = new ClassPathResource(fileName);
        return ElSqlBundle.parse(ElSqlConfig.POSTGRES, elsqlResource);
    }
}
