# BundleOffer

## Run app
1. Compile the code: *mvn clean install*
2. run in embedded tomcat: *mvn tomcat7:run*

## Endpoints
By default the app local url:
http://localhost:8080/BundleProvider

One of options for executing the REST services available - Postman.
Add Headers:
* Content-Type      application/json
* Accept            application/json


### Provide best match applicable bundles depending on customer answers to the questions provided
POST http://localhost:8080/BundleProvider/bundle

#### Sample
Body:

``` 
{
	"age" : "18", 
	"student" : "true",
	"income" : "12001"
}
``` 

Expected result:
``` 
[
  {
    "containingAccountProduct": true,
    "Priority": 2,
    "Display value": "Classic Plus",
    "Products": [
      "currentAccountProduct",
      "debitCardProduct",
      "creditCardProduct"
    ],
    "Rules": [
      "ageMoreThen17",
      "incomeMoreThen12000"
    ]
  }
]
``` 

### Provide best match applicable bundles depending on customer answers to the questions provided
POST http://localhost:8080/BundleProvider/bundles

#### Sample
Body:

``` 
{
	"age" : "18", 
	"student" : "true",
	"income" : "12001"
}
``` 

Expected result:

``` 
[
  {
    "containingAccountProduct": true,
    "Priority": 2,
    "Display value": "Classic Plus",
    "Products": [
      "currentAccountProduct",
      "debitCardProduct",
      "creditCardProduct"
    ],
    "Rules": [
      "ageMoreThen17",
      "incomeMoreThen12000"
    ]
  },
  {
    "containingAccountProduct": true,
    "Priority": 1,
    "Display value": "Classic",
    "Products": [
      "currentAccountProduct",
      "debitCardProduct"
    ],
    "Rules": [
      "ageMoreThen17",
      "incomeMoreThen0"
    ]
  },
  {
    "containingAccountProduct": true,
    "Priority": 0,
    "Display value": "Student",
    "Products": [
      "studentAccountProduct",
      "debitCardProduct",
      "creditCardProduct"
    ],
    "Rules": [
      "ageMoreThen17",
      "isStudent"
    ]
  }
]
``` 

### Validate bundle if it is applicable based on the bundle data provided
POST http://localhost:8080/BundleProvider/validation

#### Sample
Body:

``` 
{
	"bundleUid" : "studentBundle",
	"productUids" : ["studentAccountProduct"],
	"question" : {
			"age" : "18", 
			"student" : "false",
			"income" : 12001
			}
}
``` 

Expected result:
``` 
[
  {
    "validationType": "ProductRule",
    "validationMsg": "Rule id = isStudent has failed for product "
  },
  {
    "validationType": "BundleRule",
    "validationMsg": "Bundle rule IsStudent validation failed with question age=18 stdent=false income=12001.0 for bundle Student"
  }
]
``` 


Body:

``` 
{
	"bundleUid" : "goldBundleNonExistant",
	"productUids" : ["goldCreditCardProduct", "goldCreditCardProduct"],
	"question" : {
			"age" : "19", 
			"student" : "false",
			"income" : 41000
			}
}
``` 

Expected result:

``` 
[
  {
    "validationType": "ERR_BUNDLE_NOT_FOUND",
    "validationMsg": "Bundle with uid = goldBundleNonExistant does not exist in repository."
  },
  {
    "validationType": "ERR_PRODUCT_NOT_SUPPORTED_BY_BUNDLE",
    "validationMsg": "Bundle with uid = goldBundleNonExistant does not exist and can not include product goldCreditCardProduct"
  },
  {
    "validationType": "ERR_PRODUCT_NOT_SUPPORTED_BY_BUNDLE",
    "validationMsg": "Bundle with uid = goldBundleNonExistant does not exist and can not include product goldCreditCardProduct"
  },
  {
    "validationType": "ERR_BUNDLE_NOT_FOUND",
    "validationMsg": "Could not locate bundle in the repository. Can not process with bundle rules evaluation."
  }
]
``` 
