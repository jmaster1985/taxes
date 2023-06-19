# CodeChallenge TaxCalculator

### Reference Documentation

This is a microservice exposing a REST API to calculate the sales tax, written by Javad M (Leon).

### Tech-Stack
* Spring boot 3.1.0
* Apache Maven 3.9.2
* Apache Tomcat 10
* Docker

### Quick Run

There is a ready-to-run docker container (tested with docker 24.0.2) built by CI/CD which you can pull and run directly:

```
docker run -it -p 8888:8080 --rm jmaster1985/taxes:latest
```

after doing so, navigate to your browser at the following URL, to get a Swagger-UI API Doc:

```
http://localhost:8888/swagger-ui/index.html
```

in case you wanted to call the microservice directly, you can use the CURL examples provided in the 'curl-examples' directory.
there you can find examples described by the task definition.

as you may notice in the payload of examples, a "taxClassKey" parameter is needed to identify to which tax-class the product belongs. To get a list of valid tax-classes, see the curl example provided in 'curl-examples/taxClasses' file

### Local Build

to build the project locally, add an execution permission to the file 'build-local.sh' and run this script.

### CI/CD

there are two pipelines configured on the push events to the 'development' and 'master' branches. These can be seen under the 'Actions' sections of the GitHub repository, or the following link:

```
https://github.com/jmaster1985/taxes/actions
```

### Git-Flow
All the feature branches as well as release branches, have kept open to demonstrate how Git-Flow in this project have been realized.

Feature branches begin with the term 'task-nn' to demonstrate the order of implementation.

### DockerHub
[Here](https://hub.docker.com/r/jmaster1985/taxes) you can find the build containers during development.

