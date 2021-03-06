package org.svenehrke.checkout.jpaspringdataquerydsl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * See http://www.baeldung.com/2011/12/13/the-persistence-layer-with-spring-3-1-and-jpa/
 */
@Configuration
@EnableTransactionManagement
@ImportResource("classpath*:*spring-data-config.xml")
/**
 *
 * See also description (which can be found here: http://www.baeldung.com/2011/12/13/the-persistence-layer-with-spring-3-1-and-jpa/):
 *
 *  There is a relatively small difference between the way Spring is configured in
 *  XML and the new Java based configuration – in XML, a reference to another bean
 *  can point to either the bean or a bean factory for that bean. In Java however,
 *  since the types are different, the compiler doesn’t allow it, and so the
 *  EntityManagerFactory is first retrieved from it’s bean factory and then passed to the transaction manager:
 *
 *  txManager.setEntityManagerFactory( this.entityManagerFactoryBean().getObject() );
 */
public class PersistenceJPAConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSource());
//		EmbeddedDatabase derby = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.DERBY).build();
//		emfb.setDataSource(derby);
		emfb.setPackagesToScan(new String[]{"org.svenehrke.checkout.jpaspringdataquerydsl"});

		EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();

		emfb.setJpaVendorAdapter(vendorAdapter);
		emfb.setJpaProperties(additionalProperties());
		// see http://stackoverflow.com/questions/10769051/eclipselinkjpavendoradapter-instead-of-hibernatejpavendoradapter-issue
		emfb.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());

		return emfb;
	}

	Properties additionalProperties() {
		return new Properties() {
			{
				setProperty("eclipselink.ddl-generation", "create-tables");
				setProperty("eclipselink.ddl-generation.output-mode", "database");
			}
		};
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
		dataSource.setUrl("jdbc:derby:memory:myDB;create=true");
		dataSource.setUsername("test");
		dataSource.setPassword("test");
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(
			entityManagerFactoryBean().getObject());
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
