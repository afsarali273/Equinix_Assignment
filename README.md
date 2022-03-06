# Equinix Assignment

**How to Run**

```cmd
1. git clone "https://github.com/afsarali273/Equinix_Assignment.git"

2. cd /path/to/project/Equinix_Assignment

3. mvn clean install
4. mvn test 

5. Then you can see Html report at 
/target/pretty-cucumber/cucumber-html-reports/

```

or you can also run test from feature file

*Go to /Equinix_Assignment/src/test/resources/features/
Run manually by right clicking and Run feature file.*

```html

Assignment:
Create a BDD framework using Java/Python to automate the following test scenarios.

Scenario 1

Navigate to https://en.wikipedia.org/wiki/Main_Page
Select 10 different languages and verify if the language is rendered
The last language should be back to English
 

Scenario 2

Navigate to https://en.wikipedia.org/wiki/Main_Page
Search for any 10 Hollywood celebrities
Log their  birthdates and spouse details
 

Expectation:

Provide steps to run the scripts
Try to use  celebrities whose birthdates or spouse details are not available
For submission, upload the project in github and provide us with the URL to review
```



```html
Below Tests are covered:

Scenarios :
1.Verification of localization in wikipedia
2.Search for celebrities birthday and spouse details in wiki

```

```gherkin
Scenario: Verification of localization in wikipedia
Given I am on the wiki main page
When I search text Equinix - Digital Infrastructure Company in wikipedia
Then I shall see below results for various locale
| locale    | text_to_verify            |
| English   | Search results            |
| বাংলা      | অনুসন্ধানের ফলাফল           |
| Bosanski  | Rezultati pretrage        |
| Deutsch   | Suchergebnisse            |
| Ελληνικά  | Αποτελέσματα αναζήτησης   |
| Español   | Resultados de la búsqueda |
| Esperanto | Serĉrezultoj              |
| Euskara   | Bilaketaren emaitza       |
| فارسی     | نتایج جستجو               |
| Français  | Résultats de la recherche |
  
Scenario: Search for celebrities birthday and spouse details in wiki
Given I am on the wiki main page
Then I search for below celebrities to validate their dob and spouse details
| celebrities_name    | birthdate          | spouse_name      |
| Johnny Depp         | June 9, 1963       | Lori Allison     |
| Emma Watson         | 15 April 1990      |                  |
| Brad Pitt           | December 18, 1963  | Jennifer Aniston |
| Robert Downey Jr.   | April 4, 1965      | Deborah Falconer |
| Daniel Radcliffe    | 23 July 1989       |                  |
| Chris Evans (actor) | June 13, 1981      |                  |
| Leonardo DiCaprio   | November 11, 1974  |                  |
| Tom Cruise          | July 3, 1962       | Mimi Rogers      |
| Sylvester Stallone  | July 6, 1946       | Sasha Czack      |
| Will Smith          | September 25, 1968 | Sheree Zampino   |
| Dwayne Johnson      | May 2, 1972        | Dany Garcia      |  

```

**Test Results**
![Test Results](https://github.com/afsarali273/Equinix_Assignment/blob/master/Result.png)


