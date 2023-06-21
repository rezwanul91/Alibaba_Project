package step_definitions;

import org.junit.Assert;
import org.openqa.selenium.By;

import base.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class verify_alibaba_logo extends Base{
	
	@Given("I am landing on alibaba home page")
	public void i_am_landing_on_alibaba_home_page() {
	    navigateURL(host);
	}

	@Then("I Verify alibaba logo in the home page")
	public void i_Verify_alibaba_logo_in_the_home_page() {
		String actualLogo = getElementText(By.xpath("//a[text()='Alibaba.com']"));
		System.out.println(actualLogo);
		Assert.assertEquals("Alibaba.com", actualLogo);
		System.out.println("Logo Verified");
	}

}
