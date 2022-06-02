package org.selenide.examples.cucumber;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GoogleSearchStepDefinitions {
  static boolean cookieBannerAccepted = false;	
	
  @Given("an open browser with google.com")
  public void openGoogleSearch() {
    Configuration.reportsFolder = "target/surefire-reports";
    open("https://google.es/");

	if (!cookieBannerAccepted)
	{
System.out.println(1);
//		screenshot("init");
		try{Thread.sleep(3000);}catch(Exception e){}
System.out.println(2);
//		$(byText("Acepto")).should(appear);
		$(byText("Acepto")).click();
		try{Thread.sleep(100);}catch(Exception e){}
		$(byText("Acepto")).should(disappear);
		try{Thread.sleep(100);}catch(Exception e){}
		cookieBannerAccepted = true;
System.out.println(3);
	}
  }

  @When("a keyword {string} is entered in input field")
  public void enterKeyword(String keyword) {
    $(By.name("q")).val(keyword).pressEnter();
	screenshot(keyword);
  }

  @Then("at least top {int} matches should be shown")
  public void topTenMatchesShouldBeShown(int resultsCount) {
    $$("#res .g").shouldHave(sizeGreaterThanOrEqual(resultsCount));
  }

  @Then("the first one should contain {string}")
  public void theFirstOneShouldContainKeyword(String expectedText) {
    $("#res .g").shouldHave(text(expectedText));
  }
}
