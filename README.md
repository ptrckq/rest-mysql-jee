# REST API + MySQL inside JBoss EAP

This maven project has a REST API to persist and retrieve data from MySQL database and it also uses JBoss EAP 7 as runtime environment.

# Prerequisites
- JBoss EAP 7+;
- MySQL
- HTTP request sw (Postman, cURL, etc);

# Setup 
 Create a database called 'restapp' inside your MySQL. (The configurations of persistence.xml will create the tables needed)
 Point your JBoss EAP Datasource that connects to MySQL inside the tag jta-data-source in persistence.xml
  
  ```<persistence-unit name="restapp" transaction-type="JTA">
		<jta-data-source>java:/MySqlDS</jta-data-source>
		<class>br.com.ptrck.model.Pessoa</class>
		<properties>
			<property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect"/>
			<property name="hibernate.hbm2ddl.auto" value="update"/>
			<property name="hibernate.transation.jta.platform" value="org.hibernate.service.jta.platform.internal.JBossAppServerJtaPlatform"/>
		</properties>
	</persistence-unit>
```
  
  
 Import this project and deploy it on your application server.
 
 # How to use
 If you are using default JBoss EAP setting you can access the application through
 http://localhost:8080/restapp/
 
 If the message is 'Forbidden', probably everything is ok.
 Now try adding some data
 
 Example POST Method:
 http://localhost:8080/restapp/rs/pessoa/dados?nome=Joseh&idade=28&rg=192332123
 WARNING: the RG value must be between 100000000 and 999999999, or else it will reject. (Check annotations inside br.com.ptrck.model/Pessoa.class)
 
 Example GET-one Method:
 
 XML  -   http://localhost:8080/restapp/rs/pessoa/dados/192332123/xml
 JSON -   http://localhost:8080/restapp/rs/pessoa/dados/192332123/json
 
 Example GET-all Method
 http://localhost:8080/restapp/rs/pessoa/dados/todos
 This will retrieve every data from db;
 
 
 
 




