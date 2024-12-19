Intrduction
===========================================
This a simple APIs that created for regiter books in a library system. This system has 5 APIs as post, update, delete and 2 get APIs. More details are available in API detail
section.

How to install
===========================================
1. Install Java (13 or higher version) to your machine.
2. From the jar derectry run below command.

java -jar demo-0.0.1-SNAPSHOT.jar

3. Wait for application to start.

Special notes: Do not close the terminal or command line which you have started the application when you test it. It has in memeory db. So data will be persist till you
stop the terminal or command line. If you stop the application previous persisted dat will be lost. So please make sure create your own data set before you start the test.



How to access
===========================================

Application will be started in below url.

http://localhost:7081

You can append requred api path to above url to test APIs.

There are 2 users in the system as "admin" and "user". Password for both users are "password". 

Admin user can access all 4 APIs and invoke functionality. User can access post, get APIs.

Special notes: There are hidden bugs in the system. Your task is to find those bugs using your test cases and mark those tests as Failed in your test case document. 




API details
===========================================

GET /api/books Get All Book list

GET /api/books/{id} get a book [Enter book id to the {id} to retrive book details]


POST /api/books Create a book

Request: 
{
    "id": integer, // optional parameter
    "title": "String", // mandatory parameter
    "author": "String" // mandatory parameter
}


PUT /api/books/{id} Update a book

Request: 
{
    "id": integer, // mandatory parameter
    "title": "String", // mandatory parameter
    "author": "String" // mandatory parameter
}


DELETE /api/books/{id} delete a book [Enter book id to the {id} to delete book]


Response codes

200 Successfully update/delete the book


201 Successfully created the book

400 Invalid | Empty Input Parameters in the Request

401 You are not authorized to create the book

403 Request api call is forbidden

404 Book is not found




Instruction for the Assignment

Member who is doing API testing should select APIs as below

2GET APIs should cover by 1 memeber and should demo both API

POST API by 1 member

UPDATE API by 1 memeber

DELETE API by 1 memeber


I will ask to run the test by changing values when demo time. The member should be able to show it without getting help from others.