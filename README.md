# BundleOffer

## Run app
1. Compile the code: *mvn clean install*
2. run in embedded tomcat: *mvn tomcat7:run*

## Endpoints
By default the app url:
http://localhost:8080/BundleProvider

One of options for executing the REST services available - Postman.
Add Headers:
* Content-Type      application/json
* Accept            application/json


### Provide applicable bundles depending on answers to the questions provided
POST http://localhost:8080/BundleProvider/bundle

#### Sample
Body:
{
			"age" : "19", 
			"student" : "true",
			"income" : "1001"
}

Expected result:
[
  {
    "displayValue": "Classic",
    "Products": [
      "currentAccountProduct",
      "debitCardProduct"
    ],
    "Rules": [
      "ageMoreThen17",
      "incomeMoreThen0"
    ]
  }
]


### Validate bundle if it is applicable based on the bundle data provided
POST http://localhost:8080/BundleProvider/validation

#### Sample
Body:
{
	"bundleName" : "juniorSaverCustom",
	"productNames" : ["juniorSaverAccountProduct"],
	"question" : {
			"age" : "19", 
			"student" : "false",
			"income" : 200
			}
}

Expected result:
{
  "ERR_BUNDLE_NOT_FOUND": "juniorSaverCustom"
}


Body:
{
	"bundleName" : "goldBundle",
	"productNames" : ["goldCreditCardProduct", "goldCreditCardProduct"],
	"question" : {
			"age" : "19", 
			"student" : "false",
			"income" : 200
			}
}

Expected result:
{
  "ERR_RULE_VIOLATED": "goldCreditCardProduct, goldCreditCardProduct"
}


Available bundles, products and rules are located in conf. file business-config.xml
