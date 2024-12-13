# US 002 - Publish an Advertisement


## 1. Requirements Engineering


### 1.1. User Story Description


As a Real Estate Agent, I can publish any sale announcement on the system, for example received  through a phone call.



### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>   The real estate agent reviews advertisement requests, registers the information in the system and
publishes the offer so that it is visible to all clients who visit the agency and use the application. All
registered information, except the agency commission, can be accessed by the client who intends to
buy or rent the property.


>	Upon receiving the order, the agent sets the commission and publishes the offer in the system. The
commission can be a fixed amount or a percentage.



**From the client clarifications:**

> **Question:**  Is it mandatory for the agent to input the commission value before publishing an announcement?
>
> **Answer:**  Yes.

> **Question:** What would be the attributes of the Owner and Agent?
>
> **Answer:** The Owner attributes are: the name, the citizen's card number, the tax number, the address, the email address and the contact
telephone number. The Agent is an employee of the company.

> **Question:** When a sale order arrives at the agent, are all the essential characteristics of the property in question already present?
>
> **Answer:** Yes.

> **Question:** How to define Sun Exposure?
>
> **Answer:** Sun exposure will take the following values: North, South, East, or West.

> **Question:** Is it possible to submit multiple listing for the same property and type of listing?
>
> **Answer:** No.

> **Question:** Can the properties be on sale and lease at the same time?
>
> **Answer:** No.

> **Question:**  Is there a designated currency for this business, or should we use USD?
>
> **Answer:** Please use USD.

> **Question:** Are all the criteria for publishing the sale of a property in the system mandatory, or is there any data that the owner can choose not to give? such as not saying the direction of sun exposure in the case of a house.
> 
> **Answer:**  The number of bathrooms, the available equipment and the sun exposure are not mandatory.


> **Question:** Is there a maximum number of photos that can be submitted when publishing an announcement? If so, how many?
>
> **Answer:** The maximum number of photos is 30.

>**Question:** Does a rent request includes a contract duration (minimum or defined)
>
>**Answer:** The caracteristics for a rental are the same as the ones for the sale of a property. The rent value is per month. Additionally, we have to define the contract duration. There is no minimum.

### 1.3. Acceptance Criteria


* **AC1:** All fields must be filled in with the requested data, apart from the number of bathrooms, available equipment and sun exposure which are not mandatory.
* **AC2:** When publishing a sale, the system must verify if an identical sale has already been published and if so inform the use of this event and allow the user to either retype or cancel the operation.
* **AC3:** When publishing a sale, the system must verify that the owner email exists and has already been registered into the system.

### 1.4. Found out Dependencies

* There is a dependency to "US003 : Register new Employees" since to publish any sale on the system the user must define the employee responsible for the sale which must have been, beforehand inserted into the system.

### 1.5 Input and Output Data


**Input Data:**

* Typed data:
    * Client name,
    * Client telephone number,
    * Client email,
    * Client passport card number,
    * Client tax number,
    * Client address,
    * area,
    * price,
    * address,
    * distance from the city centre,
    * photographs, one or more (maximum 30),
    * date of advertisement,
    * number of bedrooms,
    * number of bathrooms,
    * number of parking spaces
    * equipments, one or more (maximum 30),
    * sun exposure
    * existence of loft
    * existence of basement
	* commission value
    * rent period(if applicable),to be registered as an integer
	
* Selected data:
    * Type of business
    * Type of property
    * Type of commission
    * registered client email address


**Output Data:**

* List of existing task categories
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![](svg/us002-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks