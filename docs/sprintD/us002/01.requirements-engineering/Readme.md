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

>   An SMS notification should be sent to the owner when the listing of the
property becomes available. The notification must include the property identification and the date it
became available. The Notification must include the name and phone number of the
responsible Agent.



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

> **Question:** Another doubt that has emerged is related to the identification of the owner of the property, isn't it required that the owner identifies themselves in the announcement ?  
>
> **Answer:** Info about the owner is not published.  

> **Question:** Are there only 2 types of commission or can the Administrator define more types of commission?
>
> **Answer:** For now we only have two types of commissions.

> **Question:** When the request arrives at the agent, are all the essential characteristics of the property in question already present?
>
> **Answer:** Yes.

> **Question:** In US002, does the agent only register the request in the system that he receives from the owner in a phone call, or can he (the agent) also accept/reject requests that the owner registers in the system (without a phone call)?
>
> **Answer:** The agent only publishes the sale announcement that he receives from the owner in a phone call.
In a previous post I also said that the agent that receives the phone call is the property responsible agent.

> **Question:** Regarding US002, the client previously stated that if the announcement is made through a phone call between the Owner and the agent, the Owner´s information wasn't needed, and therefore it wasn't recorded in the system. My question is whether this decision still applies even with the introduction of US007 (client/user registration)?
>
> **Answer:** The agent should identify, in the system, the owner that wants to sell a property. The agent should use the owner e-mail address to identify the owner. The owner who is contacting the agent (for example, through a phone call) must be registered in the system. The agent that receives the phone call is the property responsible agent.

> **Question:** Which number should be used to send the SMS, the responsible agent's number or the agency where the agent works?
>
> **Answer:** The responsible agent phone number.

> **Question:** In the Acceptance Criteria number 2 of the new refinement to the US002, it is stated that "The notification must include the property identification...". Is this property identification the address of said property or something else?
>
> **Answer:** Yes, the identification is the property address.

> **Question:** Will we need to implement Java FX for this US, despite it already being built for console?
>
> **Answer:** US2 is a US from Sprint A. Therefore you do not need to implement a Graphical User Interface (GUI) for this US. The GUI requirement is only for USs introduced in Sprint D.

> **Question:** Is the phone call the only way the agent can receive the sale announcement? Or he can receive them via e-mail/letter/etc
>
> **Answer:** For now this is the only way.

> **Question:** Is the owner able to edit an already active listing of a property? If so, do the edits need to be accepted by the agent before being published?
>
> **Answer:** No.

> **Question:** When the agent receives the phone call (as mentioned in US002) is it the agent who registers the order in the system or has the order already been entered into the system by the owner? Or is the phone call just for the owner to tell the agent that he registered a request in the system?
>
> **Answer:** The agent registers the order in the system

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

>**Question:** Does a rent request includes a contract duration (minimum or defined)?
>
>**Answer:** The caracteristics for a rental are the same as the ones for the sale of a property. The rent value is per month. Additionally, we have to define the contract duration. There is no minimum.

>**Question:** Are the SMSs created the same way as the emails? The emails are made through a text file, are the SMS notifications the same way?
>
>**Answer:** A file named SMS.txt should be used.

>**Question:**  In the Acceptance Criteria number 2 of the new refinement to the US002, it is stated that "The notification must include the property identification...". Is this property identification the address of said property or something else?
>
>**Answer:** Yes, the identification is the property address.

### 1.3. Acceptance Criteria

* **AC1:** An SMS notification should be sent to the owner when the listing of the property becomes available.
* **AC2:** The notification must include the property identification and the date it became available.
* **AC3:** The Notification must include the name and phone number of the responsible Agent.
* **AC4:** All fields must be filled in with the requested data, apart from the number of bathrooms, available equipment and sun exposure which are not mandatory.
* **AC5:** When publishing a sale, the system must verify if an identical sale has already been published and if so inform the use of this event and allow the user to either retype or cancel the operation.
* **AC6:** When publishing a sale, the system must verify that the owner email exists and has already been registered into the system.

### 1.4. Found out Dependencies

* There is a dependency to "US003 : Register new Employees" since to publish any sale on the system, the system administrator must first insert the employee into the system.

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