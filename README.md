# Test project for payback_android

## Type: QA

### Stack: Java, Appium, Gradle, Cucumber.

Test project for payback interview. I implemented one test case for coupon functionality.
To run the test you have to clone the project from the master branch.
Also, please change the credentials in the `coupons.feature` file.

Please change some props in android.properties file. For example:

```
platform.version=11.0
device.name=Pixel
adb.path=/Users/user/Library/android-sdk/platform-tools/adb
app.path=/Users/user/Documents/payback_app/payback.apk
app.path.previousVersion=/Users/user/Documents/payback_app/payback_old.apk
```


Next framework updates:
1) Add allure reporting/report-portal;
2) Implement multiple times login possibility (DB);
3) Implement the ability to choose a partner by name/image;
4) Implement coupon rollback.


### For any questions and concerns contact me via mail: [mykhailo.filipenko@gmail.com](mailto:mykhailo.filipenko@gmail.com).