# Test Automation Workshop

## Background

This is the material for my workshop at [GovTech STACK Conference 2024](https://govtechstack.sg).

This workshop will take folks on a learning adventure of how software engineers in GovTech use Automated Testing in building high-quality software products for public good. Attendees will be introduced to the different layers of the Testing Pyramid and the tools that software engineers in GovTech use to support that layer of automated testing.

Attendees will be given a number of self-paced hands-on labs to have a taster on the different kind of testing tools available. Through these, participants will learn about different kinds of test automation tools used in software engineering. Specifically for full-stack web development.

## Concept

Through this workshop, I aim to introduce the different testing tools used by modern full-stack software engineers in developing web applications.

We will cover the tools used in the [Test Pyramid](https://martinfowler.com/bliki/TestPyramid.html): **Unit Testing**, **Integration Testing** and **End-to-End Testing**.

![Test Pyramid](./docs/images/test-pyramid.jpg)

## Sample Application

We will use a To Do List app to help illustrate the concepts. The To Do List app will have these features:

- List of To Do Items
- Add new To Do Item
- Delete To Do Item
- Edit a To Do Item
- Mark Item as Done / Not Done
- Clear Completed To Do Items from the list (to be implemented as part of the exercises)

## Tools

- Front end SPA (JS + VueJS) - Introduce web frontend testing (Port 5173)
- Backend API - 3 flavors (Port 3000)
  - ExpressJS (JavaScript)
  - FastCGI (Python)
  - Spring Boot (Java)
- Playwright E2E - JS, Python & Java
- DB - SQLite3 / H2
- IDE - GitHub Codespace (via Devcontainers)
