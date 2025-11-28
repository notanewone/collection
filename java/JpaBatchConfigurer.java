@TestConfiguration
public class JpaBatchConfigurer {

    @Autowired private EntityManagerFactory entityManagerFactory;

    @Bean
    public BatchConfigurer batchConfigurer(DataSource dataSource) {
        return new DefaultBatchConfigurer(dataSource) {
            @Override
            public PlatformTransactionManager getTransactionManager() {
                JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
                jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
                jpaTransactionManager.afterPropertiesSet();
                return jpaTransactionManager;
            }
        };
    }
}