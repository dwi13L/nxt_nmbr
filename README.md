  > A REST API developed and unit tested in the span of 2 hours. 

<br>

# [Problem Statement:](https://github.com/dwi13L/nxt_nmbr/blob/main/problem-statement.pdf)

## Assumptions
1. Availablility of database

## Requirements
+ The API FetchNextNumber shall take a CategoryCode as parameter in JSON
+ Search a table where CategoryCode and value are stored and fetch value. [No result
shall be considered as 0 value]
+ Update the table with a number such that it is:
  + greater than the fetched Value
  + sum of the individual digits become 1 [for example if fetched number is 10, then
the next number should be 19 as 1+9 = 10 => 1+0 = 1]
  + the number is the smallest next available number
  + While calculating the next number, introduce a delay of 5 seconds overall to the
minimum.[simulating other processing]

+ The return value is in JSON with OldValue and NewValue
+ API shall be simultaneously executed by multi users and the outcome should be unique.

## Example System
API: http://localhost:8080/FetchNextNumber/ >> Fetches the available number and next number.

## Non-Functional Features to Incorporate
1. Database data persistence so that even if the application is restarted the number is permanently stored.
2. Unit Testing for a robust system.

## Git Usage
Project submission shall be done via pushing your codebase to Github.

Commit often with descriptive messages (we may look into your commit history).

## Mandatory files of your github repo’s root folder
1. Minimal usage guide and features implemented, as guide.txt file.
2. (Design/Architectural) (Patterns/decisions) used, as decisions.txt file.
   
## Extensibility?
Any additional features implemented, should be clearly stated in guide.txt and will be added as bonus points for your project.

## Checklist
- Diagrammed the database schema.
- Completed a minimal working model with documentation of the required features.
- Perform unit testing.
- Create and Push guide.txt
- Create and Push decisions.txt
- Upload and Push database_schema.png
- Work on adding more features if time permits.