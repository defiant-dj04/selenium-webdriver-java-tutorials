package dev.alpha.dashboard.naberly;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * Test class to change the ownership of a contact in 'https://alpha.dashboard.naberly.dev/'
 * 
 * <pre>
 * Detailed Scenario:
 *  - Open up 'https://alpha.dashboard.naberly.dev/login' in browser
 *  - Enter valid email address and password and click on 'Log in' button
 *  - Click on 'Testing Teams' tab
 *  - Click on 'Contacts' icon on top left corner of the navigation panel
 *  - Click on 'Jai Kumar's' contact in the table
 *  - Click on the edit (pencil) icon for 'Contact Owner' field and select 'Michael' from the dynamic listbox
 *  - Click on the green tick button to save the selected 'Contact Owner'
 * </pre>
 * 
 * @author Deepjyoti Barman
 * @since March 31, 2020
 */
public class ChangeOwnershipOfAContact
{
    static final String BASE_URL = "https://alpha.dashboard.naberly.dev/login";
    
    @Test
    public void tc001_changeOwnershipOfAContact()
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
        
        // Click on the edit (pencil) icon for 'Contact Owner' field
        driver.findElement(By.xpath("//div[contains(@class, 'd-md-flex')]/descendant::div[text()='Contact Owner:']/parent::li/descendant::button")).click();
        
        // Click on the dropdown list for 'Contact Owner' field
        driver.findElement(By.xpath("//div[contains(@class, 'd-md-flex')]/descendant::div[text()='Contact Owner:']/parent::li/descendant::span[@class='w-100']")).click();
        
        // Select a specific 'Contact Owner' from the dynamic listbox by id/position
        // id='react-select-2-option-0'   ->  First option of the dynamic listbox
        // id='react-select-2-option-4'   ->  Fifth option of the dynamic listbox
//        driver.findElement(By.xpath("//div[contains(@class, 'd-md-flex')]/descendant::div[text()='Contact Owner:']/parent::li/descendant::div[contains(@class, 'css-3nalw5-menu')]/descendant::div[@id='react-select-2-option-4']")).click();
        
        // Select a specific 'Contact Owner' from the dynamic listbox by visible text
        driver.findElement(By.xpath("//div[contains(@class, 'd-md-flex')]/descendant::div[text()='Contact Owner:']/parent::li/descendant::div[contains(@class, 'css-3nalw5-menu')]/descendant::div[contains(text(), 'Michael')]")).click();
        
        // Click on the green tick button to save the selected 'Contact Owner'
        driver.findElement(By.xpath("//*[name()='svg' and @data-icon='check']")).click();
    }
}
