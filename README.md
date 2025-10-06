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
```

You may replace `XML` with `MySQL`, `JSON`, or `MONGODB`.

Add your own API keys

- [OMDb API](https://www.omdbapi.com/apikey.aspx)