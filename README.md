## Bubbles4j

### Introduction

The purpose of "Bubbles4j" library is to give support in Java to a different way to process success and failure in our components, avoiding the traditional 
use of exception bubbling to handle failures. This is where its name comes from.
We've detected an extensive use of a way of handling failure scenarios that we consider unnecessary and wrong in different ways.
Therefore, we propose the use of monads to facilitate handling of failures and also improve the readability of the 
flow in our source code.

### Getting started

To be able to use our `Either` object, you will first have to import this dependency into your project.

For Gradle users this will be:

```groovy
implementation "com.theboreddev:bubbles4j:1.0.3"
```

For Maven users you will have to use the following:

```xml
<dependency>
    <groupId>com.theboreddev</groupId>
    <artifactId>bubbles4j</artifactId>
    <version>1.0.3</version>
</dependency>
```

You will also need JDK 17 to be able to use this library, at least for the time being.

### Usage

In order to introduce `Either` in your responses. This is quite simple.

First of all, define the result of your interface or component to something like this:

```java
interface CustomerService {
    Either<Exception, Customer> fetchById(String id);   
}
```

Once your component returns an `Either`, processing the result should be as simple as the following:

```java
    final Either<Exception, Customer> result = customerService.fetchById(id);
    switch (result) {
        case Success -> return result.entity();
        case Failure -> {
            switch (result.exception()){
                case JsonProcessingException -> ...
                case IllegalArgumentExcetion -> ...
            }
        }
        }
    }
```

If you have any queries or suggestions, please contact us in the below email address:

account@theboreddev.com

Copyright

This project is licensed under Apache 2.0 License
https://www.apache.org/licenses/LICENSE-2.0.txt


