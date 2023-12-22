# DEPENDENCIAS DEL PROYECTO

# Para la persistencia de datos
* JPA
 
         <dependency><groupId>org.springframework.boot</groupId><artifactId>spring-boot-starter-data-jpa</artifactId></dependency>
 

# Para el amacenamiento de datos 
* MySql para entorno de desarrollo
  
     <dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
  
  configuración en el archivo aplication.properties
  pring.datasource.url=jdbc:mysql://localhost:3306/concesionario?useSSL=false
  spring.datasource.username=root
  spring.datasource.password=

  spring.jpa.properties.dialect=org.hibernate.dialect.MySQL5Dialect
  spring.jpa.hibernate.ddl-auto=update

  spring.profiles.active=test
  
* H2 para entorno de pruebas
  
    <dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
		</dependency>
  
  configuracion del entorno de pruebas en el aplication-test.properties
  
  spring.datasource.url=jdbc:h2:mem:testdb
  spring.datasource.driverClassName=org.h2.Driver
  spring.datasource.username=sa
  spring.datasource.password=password

  spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
  spring.h2.console.enabled= true

  spring.jpa.hibernate.ddl-auto=create
  spring.jpa.properties.hibernate.show_sql=true
  spring.jpa.properties.hibernate.format_sql=true
  spring.jpa.properties.hibernate.use_sql_comments=true
  spring.jpa.properties.hibernate.type=trace

# Para la transferencia de datos entre entidad y dto
* MapStruct
   <!-- https://mvnrepository.com/artifact/org.mapstruct/mapstruct -->
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
            <version>1.5.3.Final</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.mapstruct/mapstruct-processor -->
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct-processor</artifactId>
            <version>1.5.3.Final</version>
        </dependency>

# Para los test
* jUnit 5
  
      <dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-api</artifactId>
			<version>5.9.1</version>
			<scope>test</scope>
		</dependency>

* Mockito
  
      <dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-junit-jupiter</artifactId>
			<version>5.6.0</version>
			<scope>test</scope>
		</dependency>
  
* Para probar test con WebTestClient
  
     <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
			<scope>test</scope>
		</dependency>
