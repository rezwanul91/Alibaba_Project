package step_definitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class verify_login_functionalities extends Base{
	String userName;
	String pass;
	int rowNum;
	String errMsg;
	
	@Given("I am in the Alibaba Home page")
	public void i_am_in_the_Alibaba_Home_page() {
	    navigateURL(host);
	}

	@When("I Mouse Hovar in signin menu cart")
	public void i_Mouse_Hovar_in_signin_menu_cart(){
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='sc-hd-right sc-hd-float-r']/div[1]/div[1]/div[1]/i[1]")));
	    click(By.xpath("//div[@class='sc-hd-right sc-hd-float-r']/div[1]/div[1]/div[1]/i[1]"));
	}

	@Then("I click in signin button")
	public void i_click_in_signin_button() {
	    click(By.xpath("//div[@class='sc-hd-right sc-hd-float-r']//div[@class='sc-hd-ms-btgroup']/a[1]"));
	}

	@Then("I enter the invalid email id from {int}")
	public void i_enter_the_invalid_email_id_from(Integer rowNum) {
		userName = testData.get(rowNum).get("userId");
	    sendkeys(By.id("fm-login-id"),userName);
	}

	@Then("I enter the invalid password")
	public void i_enter_the_invalid_password() {
		pass = testData.get(rowNum).get("password");
	    sendkeys(By.id("fm-login-password"),pass);
	
	}
	
	@Then("I click in submit button")
	public void i_click_in_submit_button() {
	    click(By.id("fm-login-submit"));
	}

	@Then("I verify the login error message")
	public void i_verify_the_login_error_message() {
		errMsg = testData.get(rowNum).get("errorMessage");
	    String actualMsg = getElementText(By.xpath("//span[@class='notice-descript']"));
	    Assert.assertEquals(errMsg, actualMsg);
	    System.out.println("Verification done");
	}




}
