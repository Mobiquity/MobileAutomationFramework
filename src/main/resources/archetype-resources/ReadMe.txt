#Mobile Automation Framework.
Mobile applications have tapped the pulse of the modern-day consumers who are on a lookout for extensive features at the reach of a click that is available to them no matter wherever they go.
This makes it essential to have a testing process that is not only quick but also has more comprehensive coverage to meet the requirements successfully. Naturally, Automation testing comes as the best choice to meet these requirements.

To keep up with the growing expectations of customers, We need mobile applications that are high performing and compatible with a fleet of devices that are available in the market.
Hence we came up with the Mobile Automation Framework which serves the purpose.

This framework is designed to automate mobile apps to ensure cross-platform automation testing.

It automates the testing for:
* Native Mobile Applications that are written using iOS, Android or Windows SDKs

* Mobile Web Applications that can be accessed using mobile phone browsers such as Safari, Chrome or the in-built native browser applications for android devices

* Hybrid Mobile Applications that have a native wrapper around the web view

Following are the components used in framework:

Appium - Appium an open source tool, similar to that of Selenium, offers you to write the test script in different programming languages which include Java, JavaScript, PHP, Ruby, Python, and C#. Best for Mobile Automation and also widely used and integrated with 3rd party tools. Also it is the most stable platform for mobile automation.

TestNG - TestNG is a testing framework for the Java programming language created by Cédric Beust and inspired by JUnit and NUnit.

POM(PageObjectModel) - Page Object Model is a Design Pattern which has become popular in Appium Test Automation. It is widely used design pattern in Appium for enhancing test maintenance and reducing code duplication. 
1. Write testcase only once and use it in both platforms
2. Locators are maintained in separate files rather than test files, hence easier maintainable and can be used across multiple tests.

ExtentReport - With Extent Framework, you can create beautiful, interactive and detailed reports for your tests. Add events, screenshots, tags, devices, authors or any other relevant information you decide is important to create an informative and a stunning report

Maven - Maven is a build automation tool used primarily for Java projects.

#Pre-requisites.
1. Install Appium Desktop to identify locators
2. Install npm
3. Install appium via npm "npm install -g appium"

#How to run.
1. To run the test in real android device execute below command
    "mvn test -Dplatform=android -DtestDataType=real/mock -Dgroups=<groupName> -Denv=<envName> -Dtarget=androidDevice"

2. To run the test in real iOS device execute below command
    "mvn test -Dplatform=iOS -DtestDataType=real/mock -Dgroups=<groupName> -Denv=<envName> -DdeviceName=<deviceName> -Dtarget=iPhone"

3. To run the test in android emulator execute below command
    "mvn test -Dplatform=android -DtestDataType=real/mock -Dgroups=<groupName> -Denv=<envName> -DavdName=<avdName> -Dtarget=avd"

4. To run the test in iOS simulator execute below command
    "mvn test -Dplatform=iOS -DtestDataType=real/mock -Dgroups=<groupName> -Denv=<envName> -Dtarget=simulator"