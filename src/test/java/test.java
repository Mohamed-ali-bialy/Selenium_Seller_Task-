
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.lang.Thread;
import java.util.Random;
import java.time.Duration;
import org.junit.*;
import org.openqa.selenium.By;

public class test {
    static boolean activated=false;
    static boolean found_search=false;
@BeforeClass public static void Intialization() {

    Random random = new Random();
    int randomNumber = random.nextInt(90000) + 10000; // Generates a random 5-digit number between 10000 and 99999
    String randomstr=String.valueOf(randomNumber);//random string
    TimeFormatter t=new TimeFormatter();
    String temp=t.getCurrentTime();//time string

    //input data
    String name="TEST_123_"+randomstr+"_"+temp;//name
    String phone="011"+randomstr+"006";//phone number
    String email="selled"+temp+"@gmail.com";//mail
    //input data

    //initialization
    WebDriverManager.edgedriver().setup();
    WebDriver driver = new EdgeDriver();
    driver.get("https://cp-stg.isupply.tech/buyers");
    driver.manage().window().maximize();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    //initialization

    //login
    (driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[2]/input"))).sendKeys("siteadmin@isupply.tech");
    (driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[3]/input"))).sendKeys("Isupply@1234");
    (driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[5]/button"))).click();//login
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    (wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div[6]/button[1]")))).click();//ok
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    //login

    //create page
    (driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div/div/div[4]/span/span[2]"))).click();//seller
    (driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div/div/div[4]/div/div[1]/a/span[2]"))).click();//seller list
    (driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div/div[2]/div/a"))).click();//create
    //create page

    //sending data
    try {Thread.sleep(Duration.ofSeconds(3));} catch (InterruptedException e) {}//time thread
    (driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div[1]/div[1]/input"))).sendKeys(name);
    (driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div[1]/div[2]/input"))).sendKeys(name+"Arabic");
    (driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div[2]/div[1]/input"))).sendKeys(name+"Displayname");
    (driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div[2]/div[3]/input"))).sendKeys(email);//send email
    (driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div[2]/div[4]/input"))).sendKeys(phone);//send phone
    (driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div[2]/div[5]/input"))).sendKeys("250");//limit
    (driver.findElement(By.cssSelector("#password"))).sendKeys("123@Ab123");
    (driver.findElement(By.cssSelector("#confirm_password"))).sendKeys("123@Ab123");
    (driver.findElement(By.cssSelector("#category_id > option:nth-child(2)"))).click();//option3
    (driver.findElement(By.cssSelector("#map"))).click();
    try {Thread.sleep(Duration.ofSeconds(5));} catch (InterruptedException e) {}
    //sending data

    (driver.findElement(By.cssSelector("#submit"))).click();//Submit key

    //search
    try {Thread.sleep(Duration.ofSeconds(5));} catch (InterruptedException e) {}
    (driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/form/div[1]/div[1]/div[1]/div/div/div/span/span[1]/span"))).click();//search click
    (driver.findElement(By.cssSelector("#kt_body > span > span > span.select2-search.select2-search--dropdown > input"))).sendKeys(name);
    try {Thread.sleep(Duration.ofSeconds(2));} catch (InterruptedException e) {}
    (driver.findElement(By.cssSelector("#kt_body > span > span > span.select2-search.select2-search--dropdown > input"))).sendKeys(Keys.ENTER);
    (driver.findElement(By.cssSelector("#kt_docs_card_collapsible > div > form > div.card-footer > button.btn.btn-active.btn-success.search_datatable"))).click();//apply
    String name_displayed=(driver.findElement(By.cssSelector("#seller-table > tbody > tr > td.dtr-control > div > span.text-gray-800.fw-bold.fs-6 > span"))).getText();
    if(name_displayed.equals(name)) {found_search=true;}//found in search
    //////search

    ///activation
    (driver.findElement(By.cssSelector("#seller-table > tbody > tr:nth-child(1) > td.text-center > a.btn.btn-sm.btn-icon.btn-success.row-action"))).click();//active
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    (wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#kt_body > div.swal2-container.swal2-center.swal2-backdrop-show > div > div.swal2-actions.my-actions > button.swal2-confirm.order-2.swal2-styled")))).click();//yes
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    try {Thread.sleep(Duration.ofSeconds(2));} catch (InterruptedException e) {}
    String status=(driver.findElement(By.cssSelector("#seller-table > tbody > tr > td:nth-child(3) > span"))).getText();
    if(status.equals("Active")){activated=true;}///activated
    //activation
    }

    @Test public void testfuncA()
    {
        Assert.assertEquals(true, found_search);//created correctly and found in search
    }
    @Test public void testfuncB()
    {
        Assert.assertEquals(true, activated);//account is activatd
    }
}
//