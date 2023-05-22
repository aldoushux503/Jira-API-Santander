# Jira-API-Santander

## Overview

This repository allows you to interact with the Jira REST API in order to retrieve and display information about project and their associated issues. The repository includes functionality to connect to a Jira Cloud, retrieve data using the Jira REST API, and display the key number and summary of the project's issues in the console.

## Prerequisites

Before using the project, ensure that you have the following prerequisites installed:

* JDK 8 or higher
* Maven
* Jira account

## Getting Started

To get started with this project, follow these steps:

1. Clone the repository:

```shell
git clone https://github.com/aldoushux503/Jira-REST-API.git
```
2. Navigate to the project directory:

```shell
cd Jira-REST-API
```

3. Сhange the properties to your own in the main file:

```java
String jiraUrl = <YOUR_JIRA_URL>
String projectKey = <YOUR_PROJECT_KEY>
String username = <YOUR_ACCOUNT_EMAIL>
String apiToken = <YOUR_API_TOKEN>
```

4. Run the project 

```shell
mvn exec:java          
```

## Usage

Once the application is running, it will connect to your Jira Cloud instance using the provided credentials and retrieve project and issue data. The key number and summary of each issue will be displayed in the console.
