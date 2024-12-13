# Supplementary Specification (FURPS+)
## Functionality


* **Authentication**
  * All those who wish to use the
  application must be authenticated with a password of seven alphanumeric characters, including
  three capital letters and two digits.

* **Communication**
  * Client contacts an agent to visit, buy or rent a property.
  * Owner contacts an agent to sell a property.
  
* **Security**
  * Authentication method(user/password) required to access the application.
  * Permissions depend on user login (Client, owner, employee,...).


## Usability 

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

* The code will also use Javadoc to generate useful documentation for the code.
* The unit tests will be implemented using JUnit 5.
* The JaCoCo plugin will also be used to generate the coverage report.
* All the images produced will be recorded in SVG format.

## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

* The system should be up and running 24 hours a day and 7 days a week, the results must be precise, and it must have a fast recoverability in case of system failure.


## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

* The application should quickly start after the authentication, have a quick response and recovery time. The application must also shut down after the log-out.

## Supportability
_The supportability requirements gathers several characteristics, such as:testability, adaptability, maintainability, compatibility, configurability, instability, scalability and more._ 

* The application must support the English language.
* The application should be adapted to the type of user that logs on, for example, an unregistered user he must list properties.
Maintainability

* The app should be maintained running all the time possible.
Configurability

* The app should also be very configurable to be able to adapt to the different types of users.

* **Testability**

  * The JaCoCo plugin should be used to generate the coverage report.
  * The development team must implement unit tests for all methods, except for methods that implement Input/Output operations.
  * The unit tests should be implemented using the JUnit 5 framework.
## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._
missing things  

* The application must be developed in Java.
* The development team must implement unit tests for all methods, except for methods that implement Input/Output operations.


### Implementation Constraints
Specifies or constraints the code or construction of a system such as: mandatory standards/patterns, implementation languages, database integrity, resource limits, operating system.
* **Standards-compliance**
  * Adopt best practices for identifying requirements, and for OO software analysis and design

* **Implementation languages**
  * The application must be developed in Java Language using Intellij or NetBeans.

* Implementation language: java.
* 
* Operating systems: Windows or Mac.

* There must be a set of modules for type of user.

* The application needs classes of objects to work efficiently.


### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the system being developed with other external systems._

* **Interface formats**
  * The application graphical interface is to be developed in JavaFX 11.
  * The application will use Java language with graphical interface, JavaFX 11. For Unit tests JUnit 5 and JaCoCo for the coverage report. Every image must be shown in SVG format.


### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

* There is no physical constraints.