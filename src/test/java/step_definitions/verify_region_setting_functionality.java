package step_definitions;

import org.openqa.selenium.By;

import base.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class verify_region_setting_functionality extends Base{
	
	@Given("I am in landing page")
	public void i_am_in_landing_page() {
		navigateURL(host);
	}
	@When("I input data in search box {int}")
	public void i_input_data_in_search_box(Integer row) {
		String product = testData.get(row).get("productName");
	   sendkeys(By.xpath("//input[@class='search-bar-input']"),product);
	}

	@And("I Click on search button")
	public void i_Click_on_search_button() {
	    click(By.xpath("//input[@class='search-bar-input']/following-sibling::button"));
	}

}
