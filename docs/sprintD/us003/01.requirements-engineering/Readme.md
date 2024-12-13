# US 003 - Register new Employees 

## 1. Requirements Engineering

### 1.1. User Story Description

As a system administrator, I want to register a new employee.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	The company's systems administrator will be responsible for registering all employees (specifying
the name, the citizen's card number, the tax number, the address, the email address, the contact
telephone number and the agency to which it is assigned).

**From the client clarifications:**

> **Question:** Can an employee be registered to more than one agency?
>  
> **Answer:** No.


> **Question:** What would be the attributes of the System Administrator, Agency and the Roles?
>  
> **Answer:** The System Administrator is an employee. You can get the roles from the project description. Please check the project description.


> **Question:** The administrator when registering a new employee will also have to specify the category/office that he will perform (for example agent, store manager, store network manager)?
>
> **Answer:** The administrator has to specify the role of the employee.


> **Question:** However, it was replied to a question when a new Employee is created in the system, that an 8 digit Password should be automatically generated. How many digits should we go forward for password length validation in your software? And please confirm required special characters, etc.
> 
> **Answer:** Sorry, I completely forgot that all our authentication systems require passwords with seven alphanumeric characters in length , including three capital letters and two digits. The password should be generated automatically. The password is sent to the employee by e-mail.


> **Question:** The system administrator cannot add an agent that already exists, the agent has two unique numbers that identify him (Tax number and Citizen's card number) which one should be used to identify the agent?
>
> **Answer:** The tax number.


> **Question:** Does the system administrator select the agency to which the employee is assigned and his role from a list? Or does he just type that data?
>
> **Answer:** The System Administrator should select.


> **Question:**  I have a question related to the output data: when the system administrator is registering a new employee are we free to display what we feel is important or should a specific message be shown? I was thinking of displaying whether the operation was successful or not, is that fine or should something else be displayed as well?
>
> **Answer:** A good practice is to show the information and ask for confirmation.


> **Question:** You have stated before that name, cc number, tax number, email address, phone number and the assigned agency of the employee are the mandatory requirements to register a new one, leaving out the employee's address and role. This confused me, because it wasn't clear whether leaving out those two characteristics from the answer was intentional or not. Furthermore, the role of the employee seems like too much of an important piece of information to be left out. My request is, then, for you to state whether that was a conscious decision in your answer.
>
> **Answer:** The role is required.


> **Question:** When registering a new employee, all the required data (name, citizen's card number, etc...) have to be filled or exists not mandatory data?
>
> **Answer:** Required/Mandatory data that should be filled when registering an employee: name, the citizen's card number, the tax number, the email address, the contact telephone number and the agency to which it is assigned.


> **Question:** Lastly, can there be more than one admin?
>
> **Answer:** No.


> **Question:** The network manager is the system admin ?
>
> **Answer:** No.


> **Question:**  There is only one manager for each store and only ONE system administrator
>
> **Answer:** Yes.
 
> **Question:** When the System Administrator registers a new Employee, he should receive in his e-mail, the login ID and password. I wanted to know if we are supposed to actually send the credentials to the email, or if we have to approach this rhetorically and create for example a txt file with the information.
> 
> **Answer:** The credentials should be written to a local file named email.txt.


> **Question:** The same question applies to the role.
>
> **Answer:** The System Administrator should select the role of the employee.


> **Question:** Can a single employee have more than one role? This is, when a system administrator is registering an employee, can he/she select more than one role for that employee or is it limited to one role per employee?
>
> **Answer:** An employee can have more than one role.

### 1.3. Acceptance Criteria

* **AC1:** All fields must be filled in with the requested data(Employee's name, passport card and tax number, email address, telephone number, role and store to which it is assigned), apart from the employee address which is not mandatory.
* **AC2:** When creating an employee with an already existing reference (tax number), the system must reject such operation and the system administrator must have the chance to modify the typed information.
* **AC3:** After the creation of an employee, the password associated with the account will be generated automatically and has to respect the following criteria: seven alphanumeric characters in length , including three capital letters and two digits.

### 1.4. Found out Dependencies


* There is no dependency between "US003 : Register new Employees"  and any other US present until now.


### 1.5 Input and Output Data


**Input Data:** 

* Typed data:
	* Employee's name, 
	* Employee's passport card number,
	* Employee's tax number,
	* Employee's address,
	* Employee's email address,
	* Employee's contact telephone number,
	
* Selected data:
	* Employee's role
    * Employee's assigned store

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)


![System Sequence Diagram](svg/us003-system-sequence-diagram.svg)
### 1.7 Other Relevant Remarks
