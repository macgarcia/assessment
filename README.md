# Software to assessment

## Proposal
In the context we have build an application to read a page _HTML_, this will be informed in a variable of environment on moment of building of container docker.

## Context
Was provided an environment with all dependencies to developement, then with this, need to read the page _HTML_ and find anywhere the evidence that refer a link, based in a term informed by user.

## Observations
We will have to validate the parameter entered by the user with:

**keyword** cannot have length longer than 32 characters or shorter than 4 caracters.

The archives _Dockerfile_ and _pom.xml_ cannot be changed.

## Command to build container and execute the image

```bash
$ docker build . -t axreng/backend

# BASE_URL is page HTML to find evidences from term informed.
docker run -e BASE_URL=http://hiring.axreng.com/ -p 4567:4567 --rm axreng/backend
```

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
Body: {"id": "30vbllyb"}
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

