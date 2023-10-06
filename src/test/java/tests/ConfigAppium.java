package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.*;
import pageObjects.Android.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import tests.Android.*;

public class ConfigAppium {

    public AndroidDriver androidDriver;
    public AppiumDriverLocalService service;


    /** Page Objects **/
    public LoginPage loginPage;
    public HomePage homePage;
    public BenefitPage benefitPage;
    public ShoppingPage shoppingPage;
    public MyPage myPage;
    public SearchPage searchPage;
    public ProductPage productPage;
    public DetailPage detailPage;
    public SamplePage samplePage;




    @BeforeClass
    public void ConfigureAppium() throws MalformedURLException {
        service = new AppiumServiceBuilder().withAppiumJS(new File("//opt//homebrew//lib//node_modules//appium//build//lib//main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("R3CN907CG2L");
        options.setChromedriverExecutable("//Users//juyeon//Downloads//chromedriver");
        options.setApp("//Users//juyeon//Automation//hwahae-production-release.apk");

        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        loginPage = new LoginPage(androidDriver);
        homePage = new HomePage(androidDriver);
        benefitPage = new BenefitPage(androidDriver);
        shoppingPage = new ShoppingPage(androidDriver);
        myPage = new MyPage(androidDriver);
        searchPage = new SearchPage(androidDriver);
        productPage = new ProductPage(androidDriver);
        detailPage = new DetailPage(androidDriver);
        samplePage = new SamplePage(androidDriver);

        if (this.getClass() != Login.class && this.getClass() != Home.class) {
            loginPage.clickEmailSignIn();
            loginPage.setIdField("//아이디 입력");
            loginPage.setPasswordField("//비밀번호 입력");
            homePage.chkBanner();
        }
        else if(this.getClass() == Home.class){
            loginPage.clickEmailSignIn();
            loginPage.setIdField("//아이디 입력");
            loginPage.setPasswordField("//비밀번호 입력");
        }
    }




    @AfterClass
    public void tearDown(){
        androidDriver.quit();
        service.stop();

    }


}
