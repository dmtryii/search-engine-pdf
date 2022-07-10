# Search Engine

🔍The implementation of a search engine that is able to quickly find the specified word among pdf-files, 
and rank the results by the number of occurrences.

### Description of classes and interfaces:
| Class        | Description                                                                                                                                                              |  
|--------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Main         | It contains a template for using the search engine.                                                                                                                      |
| PageEntry    | Class, describes one element of the result of one search. It consists of the name of the pdf file, the page number and the number of times this word was found on it.    |
| SearchEngine | An interface describing the search engine. All that a search engine should be able to do is respond to a query from a word with a list of elements of the search result. |
| Engine       | Search engine implementation                                                                                                                                             |

### Performing a search:
To specify a search, you need to replace the values in the `application.properties` file:
```properties
DIR=src/main/resources/docs
SEARCH-WORD=searchWord
```

`DIR` - directory with files </br>
`SEARCH-WORD` - the word we want to find

### Dependencies:
To work with PDF, the `com.itextpdf:itext7-core:7.1.7` library was used, connected via `pom.xml`:
```xml
<dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>itext7-core</artifactId>
    <version>7.1.7</version>
    <type>pom</type>
</dependency>
```
