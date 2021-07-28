# tomee-jakartaee9-demo
Apache Tomee - Jakarta EE 9 Demo

This is a simple Rest app with DataBase using Apache TomEE 9.0.0-M8 certified for Jakarta EE 9.1 .

Stack: 

* Java 11
* Docker
* H2 DataBase
* Microprofile 
* Hibernate 
* Mapstruct

# Run 

First edit the file 'run.sh' and insert the path of JDK and Maven :

```console
export JAVA_HOME=$HOME/YOU_PATH_JDK_11
export PATH=${JAVA_HOME}/bin:${PATH}
export MAVEN_HOME=$HOME/YOU_PATH_MAVEN
export PATH=${MAVEN_HOME}/bin:${PATH}
```

and then import the `Collections` to `postman` .

# POST: http://localhost:8080/api/books

```json
{
	"title":"How to Java Programming",
	"author":"Deitel & Deitel"
}
```

# Response 201:

```json
{
    "status": 201,
    "data": {
        "author": "Deitel & Deitel",
        "title": "How to Java Programming"
    },
    "message": "saved!"
}
```
# Response 400 - Validation:

```json
{
    "status": 400,
    "erros": [
        {
            "messageErro": "must not be empty",
            "propertyPath": "BookStoreController.saveBooks.arg0.author"
        }
    ]
}
```

# GET: http://localhost:8080/api/books

```json
{
    "status": 200,
    "dataCollection": [
        {
            "author": "Deitel & Deitel",
            "title": "How to Java Programming"
        }
    ]
}
```

# Change Banner 

For default the app starting with the banner "Apache TomEE" . But your can change using the properties `banner.file` in JVM Args : 

```console
export CATALINA_OPTS="-Dbanner.file=PATH_OF_BANNER"
```

to disabled the banner your can change using the properties `enable.banner` in JVM Args:

```console
export CATALINA_OPTS="-Denable.banner=false"
```