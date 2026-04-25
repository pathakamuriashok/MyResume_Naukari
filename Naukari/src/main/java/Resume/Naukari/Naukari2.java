package Resume.Naukari;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;



public class Naukari2 {

	@Test
	public void update_Resume_QA() throws IOException {

		String path = System.getProperty("user.dir")+"\\src\\main\\java\\Resume\\Naukari\\P ASHOK KUMAR_LTM.pdf";
		System.out.println(path);
	
		ChromeOptions options = new ChromeOptions();
		
	//	options.addArguments("--incognito"); // to pen in incognito mode

		options.addArguments("--headless=new");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-blink-features=AutomationControlled");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/120 Safari/537.36");
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver(options);
       
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		try {
			driver.manage().window().maximize();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));

			// Step 1: Go to Naukri login page
			driver.get("https://www.naukri.com/nlogin/login");

			// Step 2: Enter credentials
			

			WebElement emailField=wait.until(ExpectedConditions.presenceOfElementLocated(By.id("usernameField")));
			
			//WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameField")));
			emailField.sendKeys("ashok.selenium15@gmail.com"); // 🔹 Replace with your email

			WebElement passwordField = driver.findElement(By.id("passwordField"));
			passwordField.sendKeys("Searching@123LTM"); // 🔹 Replace with your password
			FileUtils.copyFile(src, new File("D:\\Git\\Naukari Resume Upload\\Naukari\\screenshot\\Enter UserName and password.png"));
			// Step 3: Click login
			WebElement loginBtn = driver.findElement(By.xpath("//button[text()='Login']"));
			loginBtn.click();

			// Wait until profile/homepage loads
			wait.until(ExpectedConditions.urlContains("naukri.com/mnjuser/homepage"));
			System.out.println("✅ Logged in successfully!");
			FileUtils.copyFile(src, new File("D:\\Git\\Naukari Resume Upload\\Naukari\\screenshot\\Login.png"));
			
			// Step 4: Go to profile page
			driver.get("https://www.naukri.com/mnjuser/profile");

			// Wait for upload section
			WebElement uploadButton = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file' and @id='attachCV']")));

			// Step 5: Upload new resume (path to your updated resume)
			File resume = new File(path); // 🔹 Replace with your actual path
			if (!resume.exists()) {
				System.out.println("❌ Resume file not found: " + resume.getAbsolutePath());
				FileUtils.copyFile(src, new File("D:\\Git\\Naukari Resume Upload\\Naukari\\screenshot\\Resume file not found.png"));
				return;
			}

			uploadButton.sendKeys(resume.getAbsolutePath());
			System.out.println("📄 Uploading resume...");

			// Wait for upload confirmation (profile update success)
			wait.until(ExpectedConditions.textToBePresentInElementLocated(
					By.xpath("//*[contains(text(),'Resume has been successfully uploaded.')]"),
					"Resume has been successfully uploaded." + ""));

			System.out.println("✅ Resume uploaded successfully!");
			FileUtils.copyFile(src, new File("D:\\Git\\Naukari Resume Upload\\Naukari\\screenshot\\Resume uploaded successfully.png"));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("❌ Something went wrong!");
			
			FileUtils.copyFile(src, new File("D:\\Git\\Naukari Resume Upload\\Naukari\\screenshot\\headless.png"));
		} finally {
			// Wait a few seconds to see the result
			try {
				Thread.sleep(5000);
			} catch (InterruptedException ignored) {
			}
			driver.quit();
		}
	}
}