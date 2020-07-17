package com.sportcity.demo;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true);
        return mapper;
    }
    /*
    @Bean
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setLocations("db/migration");
        flyway.migrate();
        return flyway;
    }

    @Bean
    public DataSource dataSource() {
        var dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/sportcity");
        dataSource.setUser("root");
        dataSource.setPassword("1001");
        return dataSource;
    }

    @Bean @Autowired
    @DependsOn("flyway")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        var jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.show_sql", "true");
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        var entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan("com.sportcity.demo");
        entityManagerFactory.setJpaProperties(jpaProperties);
        entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
        return entityManagerFactory;
    }

    @Bean @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
    */
}
