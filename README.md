# Software to assessment

## Summary
1. [Proposal](#Proposal)
1. [Technology](#Technology)
1. [Context](#Context)
1. [Rules](#Rules)
1. [Command to build](#Command_to_build)
1. [The application must be a API the recive two requests](#two_requests)
1. [Process flow](#Process_flow)
1. [Test](#Test)
*******

<div id='Proposal' />

## Proposal
In the context we have build an application to read a page _HTML_, this will be informed in a variable of environment on moment of building of container docker.

<div id='Technology' />

## Technology

    Java 11
    Docker
    Eclipse
    Maven

<div id='Context' />

## Context
Was provided an environment with all dependencies to developement, then with this, need to read the page _HTML_ and find anywhere the evidence that refer a link, based in a term informed by user.

<div id='Rules' />

## Rules
We will have to validate the parameter entered by the user with:

**keyword** cannot have length longer than 32 characters or shorter than 4 caracters. If that happen, will be throw an exception with value 500, as an internal server error.

    HTTP ERROR 500

The archives _Dockerfile_ and _pom.xml_ cannot be changed.

<div id='Command_to_build' />

## Command to build container and execute the image

```bash
$ docker build . -t axreng/backend

# BASE_URL is page HTML to find evidences from term informed.
docker run -e BASE_URL=http://hiring.axreng.com/ -p 4567:4567 --rm axreng/backend
```

<div id='two_requests' />

## The application must be a API the recive two requests.

- First: A requisition with verb HTTP POST.
- Second: A requisition with verb HTTP GET.

Requisitin POST:

    POST /crawl
    Host: localhost:4567
    Content-Type: application/json
    Body: {"keyword": "security"}

Response:

```json
200 OK
Content-Type: application/json
{"id": "30vbllyb"}
```

Requisition GET:

    GET /crawl/id
    Host: localhost:4567

Response:

```json
200 OK
Content-Type: application/json
{
    "id": "30vbllyb",
    "status": "active",
    "urls": [
    "url/one",
    "url/two"
    ]
}
```

<div id='Process_flow' />

## Process flow

Path to each step that the data pass.

![](https://ik.imagekit.io/macgarcia/assessment/process-flow.png?updatedAt=1695923859249)

<div id='Test' />

## Test

If you want to run all the application tests, access the project directory and execute the commands below.

**Tests observation:**
 - These tests will be run automatically when the container is in building, if you try run on your machine, must configure a variable of environment with name 'BASE_URL'.
  - There is a test that validade this condition, one time, that variable is very important to application.

```bash
$ mvn clean

$ mvn verify
```
