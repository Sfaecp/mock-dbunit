package com.lbx.mockDbunit;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
  
/**
 * dbunit配置
 * @author T18
 * @DbUnitConfiguration
 */
@Configuration
public class DBUnitConfiguration {
   
  /**
   * 允许预制表数据的字段为空
   * @return
   */
  @Bean
  public DatabaseConfigBean dbUnitDatabaseConfig() {
    DatabaseConfigBean bean = new DatabaseConfigBean();
    bean.setAllowEmptyFields(true);
    return bean;
  }
  
  @Bean
  public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection(
      DatabaseConfigBean dbUnitDatabaseConfig,
      DataSource dataSource) {
    DatabaseDataSourceConnectionFactoryBean bean = new DatabaseDataSourceConnectionFactoryBean(dataSource);
    bean.setDatabaseConfig(dbUnitDatabaseConfig);
     
    return bean;
  }
}
