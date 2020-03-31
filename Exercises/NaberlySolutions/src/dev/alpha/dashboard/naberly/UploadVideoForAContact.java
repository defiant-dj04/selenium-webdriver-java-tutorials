package dev.alpha.dashboard.naberly;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * Test class to upload a video for a specific contact in 'https://alpha.dashboard.naberly.dev/'
 * 
 * <pre>
 * Detailed Scenario:
 *  - Open up 'https://alpha.dashboard.naberly.dev/login' in browser
 *  - Enter valid email address and password and click on 'Log in' button
 *  - Click on 'Testing Teams' tab
 *  - Click on 'Contacts' icon on top left corner of the navigation panel
 *  - Click on 'Jai Kumar's' contact in the table
 *  - Click on 'Send a Video (coming soon)' button, then 'Browse files' button
 *  - Select the .mp4 file
 *  - Provide a subject for the video
 *  - Click on 'Submit' button after the processing of the video is completed
 * </pre>
 * 
 * @author Deepjyoti Barman
 * @since March 31, 2020
 */
public class UploadVideoForAContact
{
    static final String BASE_URL      = "https://alpha.dashboard.naberly.dev/login";
    static final String MP4_FILE_PATH = "videos/3_secs_countdown.mp4";
    
    @Test
    public void tc001_uploadVideoForAContact() throws IOException, InterruptedException
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        
        // Enter "debasmitanaberly123@outlook.com" in the 'Email Address' text-field
        driver.findElement(By.name("email")).sendKeys("debasmitanaberly123@outlook.com");
        
        // Enter "1" in the 'Password' text-field
        driver.findElement(By.name("password")).sendKeys("1");
        
        // Click on the 'Log in' button
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        
        // Click on 'Testing Teams' tab
        driver.findElement(By.xpath("//h3[text()='Testing Teams']")).click();
        
        // Click on 'Contacts' icon
        driver.findElement(By.xpath("//a[@href='/contacts']")).click();
        
        // Click on 'Jai Kumar's' contact in the table
        driver.findElement(By.xpath("//td[text()='Jai  kumar']")).click();
        
        // Click on 'Send a Video (coming soon)' button
        driver.findElement(By.xpath("//div[contains(@class, 'd-md-flex')]//button[contains(@data-tip, 'Send a Video')]")).click();
        Thread.sleep(1000);
        
        // Click on 'Browse files' button
        driver.findElement(By.xpath("//button[text()='Browse files']")).click();
        
        // Upload the .mp4 file running the executable generated via compiling the AutoIT script
        String fileAbsoluteURI = Paths.get(MP4_FILE_PATH).toUri().toString();
        Runtime.getRuntime().exec("autoit-scripts/file-upload.exe" + " " + fileAbsoluteURI);
        
        // Enter "3 Secs Demo Startup Video" in 'Subject' text-field
        driver.findElement(By.xpath("//input[@placeholder='Subject']")).sendKeys("3 Secs Demo Startup Video");
        
        // Wait until processing of the video is not completed
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()='Processing...']")));
        
        // Click on the 'Submit' button
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }
}
