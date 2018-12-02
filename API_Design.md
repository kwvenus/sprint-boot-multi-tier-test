Story 1

AC1 Design

Given employeeID,

```JSON
{
	"employeeID" : String
}
```

When POST /parkingboys,


Then return HTTP status code 201
###
Unit case:

1. should_able_to_create_parkingboy_if_employeeId_does_not_exist_in_database

	Given employeeId that does not exist in database,

	```JSON
	{
		"employeeId" : String
	}
	```

	When POST /parkingboys,


	Then should return HTTP status code 201
	
	and able to find parkingboy with employeeID in database

	2. should_not_able_to_create_parkingboy_if_employeeId_exists_in_database

	Given employeeID that already exist in database,

	```JSON
	{
		"employeeID" : String
	}
	```

	When POST /parkingboys,


	Then should return HTTP status code 409
