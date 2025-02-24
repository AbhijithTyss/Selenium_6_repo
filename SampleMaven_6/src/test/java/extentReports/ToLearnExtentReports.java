package extentReports;

import java.time.Duration;
import java.time.LocalDateTime;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ToLearnExtentReports {
	@Test
	public void createReport() {
		String time = LocalDateTime.now().toString().replace(":", "-");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get("https://www.flipkart.com/");
		TakesScreenshot ts=(TakesScreenshot) driver;
		String screenshot = ts.getScreenshotAs(OutputType.BASE64);
		
		// s1: create ExtentSparkReporter object
		ExtentSparkReporter spark=new ExtentSparkReporter("./HTML_reports/extent_"+time+".html");
		
		// s2: create object for ExtentReports
		ExtentReports extReport=new ExtentReports();
		
		//s3: attach ESR to ER
		extReport.attachReporter(spark);
		
		//s4: create ExtentTest object
		ExtentTest test = extReport.createTest("createReport");
		
		// s5: call log()
		test.log(Status.PASS, "Message added into report");
		test.addScreenCaptureFromBase64String(screenshot);
		
		// s6: call flush()
		extReport.flush();
	}
}
