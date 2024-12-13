# US 004 - Request Advertisement

## 1. Requirements Engineering


### 1.1. User Story Description

As an owner, I intend to submit a request for listing a property sale or rent, choosing the responsible agent.


### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>Owners go to one of the company's branches and meet with a real estate agent to sell or
rent one or more properties, or they can use the company's application for the same purposes. The
owner provides property characteristics and the requested price and sends the request to an agent.


>Upon receiving the order, the agent sets the commission and publishes the offer in the system. The
commission can be a fixed amount or a percentage. In the case of a request for the sale of a
property, the owner must provide information on: the type of property (apartment, house or land),
the area in m2, the location, the distance from the city centre, the requested price and one or more
photographs. If the property is an apartment or a house, the owner also provides: the number of
bedrooms, the number of bathrooms, the number of parking spaces and the available equipment,
such as central heating and/or air conditioning. In case the property is a house, the existence of a
basement, an inhabitable loft, and sun exposure must be registered as well.	



**From the client clarifications:**

> **Question:** In the Project description, there are only specifications for a Sale. What are the required characteristics for a rental?
>  
> **Answer:** The characteristics for a rental are the same as the ones for the sale of a property. The rent value is per month. Additionally, we have to define the contract duration.

> **Question:** Are there any restrictions on the choice of an Agent?
>
> **Answer:** No.

> **Question:** In case the submission of the listing is online may the owner choose any agent?
>
> **Answer:** Yes.

> **Question:** In case it is on an agency, must the agent be assigned automatically by the system?
>
> **Answer:** The agent that registers the information in the system can choose to assign any agent.

> **Question:** Can a client also be an agent or owner ? Can the store manager , be also an agent?
> 
> **Answer:** A person can have multiple roles.

> **Question:** Can the Client see the list of available houses in all the branches?
>
> **Answer:** Yes.

> **Question:** Does the agent check with the owner when accepting the prospective tenant's or buyer's offer, does it offer ?
>
> **Answer:** The client makes an offer. How the agents checks with the owner is not a feature that will be included in the system.

> **Question:** How does the agent determine whether it is a fixed commission or a percentage commission?
>
> **Answer:** The agent should choose the type of commission and enter the value.

> **Question:** Is it possible to submit multiple listing for the same property and type of listing?
>
> **Answer:** No.

> **Question:** Is there a designated currency for this business, or should we use USD?
>
> **Answer:** Please use USD.

> **Question:** Should we consider that, until the request is reviewed and posted, the request stays in a "not published" state?
>
> **Answer:** This is an implementation detail. For me, as a client, I want the feature implemented as I already described in the project description.

> **Question:** Does an owner need to be registered in the system to submit a request for a property listing?
>
> **Answer:** No. When making the request to list a property, the owner should introduce his own data. The Owner attributes are: the name, the citizen's card number, the tax number, the address, the email address and the telephone number.

> **Question:** When a user wants to make a buy / rental request, does the property change to a "Reserved" state, simply disappears from the list, or continues there until a request is accepted?
>
> **Answer:** From the project description we get: "After being appreciated by the agent, he accepts or rejects the order. If the
request is accepted, the offer will not be shown again to clients using the application.". The property does not change o "Reserved" state.

> **Question:** When assigning an agent to a property listing, are the available agents shown by the system for the owner to pick? Or does the owner need to provide the agent's information (name, agency,etc)?
>
> **Answer:** The owner should select one agent from a list of agents that work in the selected agency. The owner should select the agency before selecting the agent.

> **Question:** According to the Project Description, the agent when selling a property can charge a flat price commission or a percentage of the sale value, my question here is either there is a minimum and/or a maximum to each of these types of commissions?
>
> **Answer:** There is no maximum and the minimum is 0.

> **Question:** In the case of listing a land property, shall the owner refer if there is a building permit already approved?
>
> **Answer:** No.

> **Question:** If the owner doesn't select an agent will the platform randomly assign one or the request cannot move to revision? If not, must we assume that all information slots must be filled?
>
> **Answer:** Thank you for your suggestion. When filling the property data, the owner should select one agent from the list of agents working in the selected agency. Moreover, the application should include a feature to randomly assign one agent. The address of the owner is not mandatory.

> **Question:** Do requests have any reference/code identifying them with any specific format? What about descriptions (any restrictions, like character limit)? Does that reference carry out with the advertisement?
>
> **Answer:** Please choose appropriate data formats for the request. You are a team of experts, and you should choose appropriate formats. In the next sprints I will specify some data formats.

> **Question:** When renting, does the owner have any space to clarify any prohibitions or obligations with the property?
>
> **Answer:** No.

> **Question:** Does the owner have a limit of requests they can do?
>
> **Answer:** No.

> **Question:** Regarding the property’s photographs, is that considered selected data?
>
> **Answer:** The owner should input the URI of each file/photograph.

> **Question:** Is there a maximum number of photos that can be submitted when publishing an announcement? If so, how many?
> 
>  **Answer:** The maximum number of photos is 30.

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be valid and all filled in (1.5 Input Data).
* **AC2:** When listing a property with an already existing reference, the system must reject such operation and the user must have the change to modify the typed reference.
* **AC2:** The Owner should be registered in the system to perform a request.

### 1.4. Found out Dependencies

* Needs to be logged as an Owner

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * area,
    * price,
    * address,
    * distance from the city centre,
    * photographs, one or more (maximum 30),
    * number of Bedrooms,
    * number of bathrooms,
    * number of parking spaces,
    * available equipment,
    * sun exposure,
    * existence of loft,
    * existence of basement,
    * rent period,
    
* Selected data:
	* type of property ,
    * type of business ,
    * agent.


**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram US 004](svg/us004-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks
