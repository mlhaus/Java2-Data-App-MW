## Java 2 Data Application

The purpose of this project is to retrieve data from a variety of sources
including XML, MySQL, JSON, and more.

### Setup

Clone this repository to your computer.
Create an `application.properties` file in 
the `/src/main/resources` folder.

Add these key value pairs:

```
datasource.type=XML
xml.apiURL=https://www.omdbapi.com/?type=movie&apikey=<your-omdbapi-key>&r=xml
mysql.connectionString=jdbc:mysql://<host-url>:3306/<db-name>?user=<db-user>&password=<db-password>&sslmode=disabled
```

You may replace `XML` with `MySQL`, `JSON`, or `MONGODB`.

Add your own API keys

- [OMDb API](https://www.omdbapi.com/apikey.aspx)

In class, we used a MySQL database from Siteground, but this can be substituted with a 
database from a different service like Microsoft Azure, Amazon Web Services, etc.