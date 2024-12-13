# Supplementary Specification (FURPS+)
## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._

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


(fill in here )

## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._


(fill in here )

## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._


(fill in here )

## Supportability
_The supportability requirements gathers several characteristics, such as:testability, adaptability, maintainability, compatibility, configurability, instability, scalability and more._ 

* The application must support the English language.


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


### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the system being developed with other external systems._

* **Interface formats**
  * The application graphical interface is to be developed in JavaFX 11.


### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

(fill in here )