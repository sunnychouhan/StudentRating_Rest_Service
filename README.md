# Student Ratings Rest Service

## Description

This is a REST service which provides detail about student enrollments and provide average score for each student and also for each Subject
All the Data specific to enrollment is stored in H2 DB which in memory Database.(Request Respose is in Json Format)

It covers following Scenario:

1) Compute & display student average score per assignment category &overall rating for assigned subject(s).
2) Compute & Display subject average score per assignment category &overall rating for assigned student(s).
3) Basic features.

    1) Display assignment category with weights to an existing list.
    2) CRUD Operation on assignment category
    3) CRUD operations for student enrollment to subject(s)with Assignment Category

### Running Application Steps

1) mvn clean install -U
2) java -jar ./target/SU20343138_WebService-0.0.1-SNAPSHOT.jar
3) Open http://localhost:8080/average/score/student/Ananth.

```json
{
  "name": "Ananth",
  "subjects": [
    {
      "test": "34.4",
      "lab": "NA",
      "quiz": "NA",
      "project": "NA",
      "overall": "34.4",
      "subject": "Computing Techniques"
    },
    {
      "test": "40.0",
      "lab": "10.0",
      "quiz": "20.0",
      "project": "30.0",
      "overall": "100.0",
      "subject": "Electro Fields"
    }
  ]
}

```

###NOTE: There is no validation on input text or data due to time constraint. Application is built for running Happy Scenarios Only.


