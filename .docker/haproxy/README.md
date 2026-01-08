# Spring Boot + HAProxy with Docker Compose Scaling

This project demonstrates how to run a **Spring Boot application behind an HAProxy load balancer** using **Docker Compose** and the `--scale` command-line option — without duplicating service definitions in the `docker-compose.yml` file.

---

## Overview

- **HAProxy (`lb`)** acts as the entry point and load balancer
- **Spring Boot (`app`)** runs as multiple instances
- Horizontal scaling is performed **at runtime** using `docker compose --scale`
- All services communicate over an internal Docker bridge network

---

## Docker Compose Configuration

The Compose file defines:
- A **single Spring Boot service**
- A **single HAProxy service**

The Spring Boot service is defined **once** and scaled dynamically at runtime.

---

## Running the Stack

### Start the Stack (Single Instance)

```bash
docker compose up -d
```

This starts:

1 × HAProxy container

1 × Spring Boot container

## Scale the Spring Boot Application

To run multiple instances of the Spring Boot application, use the --scale flag:

```shell
docker compose up -d --scale app=3
```

This will start:

1 × HAProxy container

3 × Spring Boot containers:

```
app-1
app-2
app-3
```
No changes to docker-compose.yml are required scaling is done at runtime.

```shell
$ docker compose ps

NAME        SERVICE   STATUS
lb-1        lb        running
app-1       app       running
app-2       app       running
app-3       app       running
```

## Load Balancing Behavior

HAProxy listens on port 80

Spring Boot instances expose port 8080 internally

Docker’s internal DNS allows HAProxy to discover all app containers.

Incoming requests are distributed across all healthy app instances

## Key Takeaways

- docker compose --scale enables horizontal scaling without service duplication

- Keeps docker-compose.yml clean and DRY

- Ideal for:

Local load testing

Simulating production-like topologies

Running multiple stateless Spring Boot instances
