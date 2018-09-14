Hi 
Here is my attempt to create a Music Profile of artists as illustrated in the assignment document.
Here are the items that I have achieved:

•	All REST services are using Spring Boot to enable self hosting at http://localhost:8080/artist
•	REST services supports all http verbs required for CRUD operations. GetMapping, POSTMAPPING, PUTMAPPING and DELETEMAPPING annotations
	have been implemented. All verbs were tested using Mozilla Browser's REST extension tool.
•	Spring configuration should be done via code/annotations not xml. This task has been achieved.
•	All models must be marked up with JPA annotations. Usually I use CRUDRepository as my go to respository. For this project I have
	implemented JPARepository.
•	The service/repository pattern must be used. Each Entity is using its own repository and is packaged under repository single
	package. I didn't bother to implement service package felt that it was bit overkill for the project. However, I usually do
	create Service entities separately. However, I do feel that it might be a good idea to create a separate service entity for
	the Song class. Reasons are explained in Disclaimer.
•	All service and repository code should have good unit test coverage. I am still looking into this and will try to come up with
	couple of individual unit testing against Artist Get Method.
	
•	The database has not been provisioned yet exist so an in memory database can be used for persistence for this phase.
	For this project I have implemented an embedded H2 database which can also be accessed after it is initialized through the url:
	URL for H2 Console: http://localhost:8080/h2-console
	DB URL: Please use the following url: jdbc:h2:mem:testdb
	Username will be sa
	Password will be blank
•	For this phase the database should be seeded with some test data for validation.
	I have used my own test data - data.sql and created my own schema schema.sql

•	Maven is the build tool of choice. Project is designed in Maven.


To access Artist list: Type http://localhost:8080/artist
To access all albums of artist ID 1: Type: http://localhost:8080/artist/1/albums NOTE the s in albums
To access all songs of album ID 1 from Artist 1: Type http://localhost:8080/artist/1/albums/1/songs NOTE the s in songs (ALSO please
see the disclaimer as this feature isn't running properly).

DISCLAIMER: 
	- I was able to successfully join artist table to album table using their id's. I attempted to use the same methodology to join
	Album table to Song table by using join ManyToOne from song table.
	Although the song table does get created, but I am unable to associate the table with Album id successfully within hibernate.
	
	
	
	
	