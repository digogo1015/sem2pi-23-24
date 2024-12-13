# US 0012 - Import information common in others agencies

## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID | Question: Which class is responsible for...      | Answer                  | Justification (with patterns)                                                                                 |
|:---------------|:-------------------------------------------------|:------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?                 | ImportFilesUI           | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		        | 	... coordinating the US?                        | ImportFilesController   | Controller                                                                                                    |
| Step 2  		     | ... showing file's list?							                  | ImportFilesUI           | IE: is responsible for user interactions.                                                                     |
| Step 3  		     | 	...saving the inputted data?                    | Task                    | IE: object created in step 1 has its own data.                                                                |
| 			  		        | ... instantiating a new request? 	               | RequestRepository       | Creator                                                                                                       |
| 			  		        | ... saving request data read from file?	         | Request                 | IE: owns its data.                                                                                            |
| 			  		        | ... instantiating a new commission?	             | CommissionRepository    | Creator                                                                                                       |
| 			  		        | ... saving commission data read from file?	      | Commission              | IE: owns its data.                                                                                            |
| 			  		        | ... instantiating a new store?	                  | StoreRepository         | Creator                                                                                                       |
| 			  		        | ... saving store data read from file?	           | Store                   | IE: owns its data.                                                                                            |
| 			  		        | ... instantiating a new advertisement?	          | AdvertisementRepository | Creator                                                                                                       | 
| 			  		        | ... saving advertisement data read from file?	   | Advertisement           | IE: owns its data.                                                                                            |
| 			  		        | ... instantiating a new client?	                 | ClientRepository        | Creator                                                                                                       |
| 			  		        | ... instantiating a new purchase order?	         | AdvertisementRepository | Creator                                                                                                       | 
| 			  		        | ... saving purchase order data read from file?	  | PurchaseOrder           | IE: owns its data.                                                                                            |
| 			  		        | ... adding a purchase order to the system?	      | AdvertisementRepository | IE: owns all advertisement's purchase orders                                                                  | 
| 			  		        | ... adding a store to the system?	               | StoreRepository         | IE: owns all stores                                                                                           | 
| 			  		        | ... adding a advertisement order to the system?	 | AdvertisementRepository | IE: owns all advertisements                                                                                   | 
| Step 4  		     | 	... informing operation success?                | ImportFilesUI           | IE: is responsible for user interactions.                                                                     | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Advertisement
 * Request
 * Client
 * PurchaseOrder
 * Commission
 * Store

Other software classes (i.e. Pure Fabrication) identified: 

 * ImportFileUI  
 * ImportFileController
 * AdvertisementRepository
 * StoreRepository
 * RequestRepository
 * ClientRepository
 * CommissionRepository


## 3.2. Sequence Diagram (SD)

### Alternative 1 - Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us0012-sequence-diagram-full.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us0012-class-diagram.svg)