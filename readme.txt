Hi 
Here is my attempt to create a Music Profile of artists as illustrated in the assignment document.
Here are the items that I have achieved:

•	All REST services are using Spring Boot to enable self hosting at http://localhost:8080/artist

•	REST services supports all http verbs required for CRUD operations. GetMapping, POSTMAPPING, PUTMAPPING and DELETEMAPPING annotations
	have been implemented. All verbs were tested using POSTMAN-like utility tool. List of URI's can be found in 
	URIFile along with this readme

•	Spring configuration should be done via code/annotations not xml. This task has been achieved.

•	All models must be marked up with JPA annotations. This task has been achieved.

•	The service/repository pattern must be used. 
    Each entities have been grouped into following layers: Model layer, Repositories layer, Service layer and 
    Controller layer. I have included notes file in model, and service layer for further reference on my implementation.
    
•	The Main database has not been provisioned yet exist so an in memory database can be used for persistence for this phase.
	For this project I have implemented an embedded H2 database which can also be accessed after it is initialized through the url:
	URL for H2 Console: http://localhost:8080/h2-console
	DB URL: Please use the following url: jdbc:h2:mem:testdb
	Username will be sa
	Password will be blank
	
•	All service and repository code should have good unit test coverage. I have created separate unit test layers for service layer and 
    repositories layer. Additional notes is included in each of the package for further reference. 
    For repositories layer I used another embedded test database H2 for testing. Application.Properties for test layer can be found separately 
    in src/test/resources.
    For service layer, I ended up using Mockito to mock JPA data.
    In addition, only unit test coverage have been coded using JUnit test. Please individually test each test package separately.

•	For this phase the database should be seeded with some test data for validation.
	I have used my own test data - data.sql and created my own schema schema.sql - schema was needed to auto-populate created_at and
	modified_at values into h2 database otherwise they were showing null.

•	Maven is the build tool of choice. Project is designed in Maven.


To access Artist list: Type http://localhost:8080/artist
To access all albums of artist ID 1: Type: http://localhost:8080/artist/1/albums NOTE the s in albums
To access all songs of album ID 1 from Artist 1: Type http://localhost:8080/artist/1/albums/1/songs NOTE the s in songs

Additional uri rules can be found in URIList text file.

	
	
	
	
	