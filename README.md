# Fetch-rewards-coding

This project uses java springboot framework. It implements the function of adding user's points, spending points according to known rules, and viewing user's balance.

## How to run the program

### Prerequisites
* Install appropriate Java JDK (like JDK 8)
* Install IntelliJ or any appropriate IDE.
* Clone the repository `git clone https://github.com/stefan0711/fetch-rewards-coding.git`
* This project uses the maven build tool, and all the dependencies used are in pom.xml
* Use the Postman test Api

### Start the project in the IDE
* Run the `/Users/yinzhipeng/work/project/fetch-rewards-coding/src/main/java/com/zhip/fetchrewardscoding/FetchRewardsCodingApplication.java`

### Start the project in the terminal
*  to the project folder.
* Run using command : `mvn spring-boot:run`

## Test Api
### Adding a transaction 
`Post http://localhost:8880/fetch/api/addTransaction`
* ![avatar](/markdownPic/add.png) 

* Request body: 
```
{
    "payer": "MILLER COORS",
    "points": 10000,
    "timestamp": "2022-11-01T14:00:00Z"
}
```

* ![avatar](/markdownPic/addreq.png)
* Here is the format of the Response body is standardized, code is the status number, request success is 200, message is the returned information provided (optional), data is the code required to return information.
* Response body:
```
{
    "code": 200,
    "message": "Add successful",
    "data": ""
}
```
### Spend points  
`Post  http://localhost:8880/fetch/api/spendPoints`
* Request body:
```
{
    "points": 5000
}
```
* Response body
```
{
    "code": 200,
    "message": "",
    "data": [
        {
            "UNILEVER": -200
        },
        {
            "MILLER COORS": -4700
        },
        {
            "DANNON": -100
        }
    ]
}
```
* ![avatar](/markdownPic/spend.png)

### Get balance
`Get http://localhost:8880/fetch/api/getBalance`

* Response body
```
{
    "code": 200,
    "message": "",
    "data": {
        "UNILEVER": 0,
        "MILLER COORS": 5300,
        "DANNON": 1000
    }
}
```
* ![avatar](/markdownPic/balance.png)

## Note
* This project does not have a persistent database, the data is temporarily stored in memory



