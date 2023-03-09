@Smoke
Feature: Validate User Details

  Background: 
    * def javaUtilClass = Java.type('com.example.JavaUtilClass')
    * json Resp = new javaUtilClass().readExcelFileAsJsonObject_RowWise('src/test/java/testData/'+env+'.xls')
    * print Resp
    * def region = paramjson.Regions[0].region2
    * print region
    Given json result = karate.jsonPath(Resp, "$." + region + "")
    * json datasource = result.slice(paramjson.TestCase3[0].start , paramjson.TestCase3[0].end)
    * print datasource
    * def toNumber = function(x){ return parseInt(x) }

  Scenario Outline: <TestaseName>
    Given call read('classpath:SAFFWfeaturefiles/utility.feature@Validatedetails')
    Given call read('classpath:SAFFWfeaturefiles/test.feature@ValidateidandFname')
    Given call read('classpath:SAFFWfeaturefiles/test.feature@ValidateLNameandEmail')

    Examples: 
      | datasource |

  @ValidateidandFname @ignore
  Scenario: Get a user
    * print response
    * def RecordResp = response
    #Resp
    * print RecordResp.data.id.toString()
    #Actual
    * print Recordid
    And match toNumber(RecordResp.data.id) == toNumber(Recordid)
    And match RecordResp.data.first_name == FirstName

  @ValidateLNameandEmail @ignore
  Scenario: Get a user
    * print response
    * def RecordResp = response
    And match RecordResp.data.last_name == LastName
    And match RecordResp.data.email == Emailid
