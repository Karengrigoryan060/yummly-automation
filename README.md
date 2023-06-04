# Required tools
1. Xcode
2. Android tools (ADB included)
3. java
4. Appium
5. allure

# You can run tests by running 
``mvn failsafe:integration-test -Dandroid.app.path=${androidAppPath} -Dandroid.device.udid=${androidDeviceUdid} -Dios.app.path=${iosAppPath} -Dios.device.udid=${iosDeviceUdid} -Delement.wait.timeout=${elementWaitTimeout} -Dretry.count=${retryCount}``
#### all params are optional, and default values are in config.properties file

# After execution, you can run the command below to generate allure report
``allure serve /allure-results``

## the Test can be executed bor android and ios. From the testng.xml file you need to pass param (android or ios)

## There is included jenkins file, and you can import it into jenkins, and run tests from there

## note
### Add APK file in resources before test execution, or provide valid path in config.properties
### Add facebook integration app in resources folder before test run. As I haven't chance to have debug signed build, I have used integrationApp to start the driver, and switch to the yummly app.

