package step_definitions;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class verify_language_and_currency extends Base{
	
	@Given("I am in alibaba home page")
	public void i_am_in_alibaba_home_page() {
	   navigateURL(host);
	}

	@When("I Mouse Hover on language settings")
	public void i_Mouse_Hover_on_language_settings() {
		mouseHover(By.xpath("//div[@class='sc-hd-right sc-hd-float-r']/div[5]/div/div/div/label"));
	}

	@Then("I click in language dropdown menu")
	public void i_click_in_language_dropdown_menu() {
		click(By.xpath("//div[@id='J_SC_header']//div[@class='sc-hd-row sc-hd-link']//div[@class='input-container'][1]//div[@class='ellipsis']"));
	    //click(By.xpath("//div[@class='sc-hd-row sc-hd-link']/div/div[3]/div[5]/div/div/div[2]/div[2]/div/div/div/div"));
	}

	@Then("I select in hindi language")
	public void i_select_in_hindi_language() {
	    click(By.xpath("//div[@id='J_SC_header']//div[@class='sc-hd-row sc-hd-link']//div[@class='input-container'][1]//div/div[@class='droplist ']/ul/li/ul/li[1]"));
	  
	}

	@Then("I also click on currency dropdown menu")
	public void i_also_click_on_currency_dropdown_menu() throws InterruptedException{
		Thread.sleep(50000);
		//mouseHover(By.xpath("//div[@class='sc-hd-right sc-hd-float-r']/div[5]/div/div/div/label"));
		click(By.xpath("//div[@class='sc-hd-right sc-hd-float-r']//div[@class='sc-hd-lan']//div[@class='header-control-panel']//div[@class='input-container'][2]//div[@class='droplist has-filter']/div/input"));
		List<WebElement> c = driver.findElements(By.xpath("//div[@class='sc-hd-right sc-hd-float-r']//div[@class='sc-hd-lan']//div[@class='header-language-currency ready']//div[@class='header-control-panel']//div[3]//div[2]/ul/li[2]/ul/li"));
		
		for(WebElement a : c) {
			String i = a.getAttribute("data-value");
			
			if (i.contains("INR")) {
				a.click();
				break;
		}
			}

	}

	@Then("I click in save button")
	public void i_click_in_save_button() {
		click(By.xpath("//div[@id='J_SC_header']/header/div/div/div[3]/div[5]/div/div/div[2]/div[5]/button"));
	}

	@Then("I verify the new language")
	public void i_verify_the_new_language() {
	    String actualLan = getElementText(By.xpath("//div[@id='root']/div[1]/div[2]/div[2]/a/div/div/div[1]"));
	    Assert.assertEquals("Ready to Ship", actualLan);
	    System.out.println("Verification done");
	}


}
