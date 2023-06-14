package org.selenide.examples.cucumber;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.screenshot;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GoogleImageSearchStepDefinitions {
    private String keyword;

    @When("click \"Images\" link")
    public void chooseImagesAsSearchTarget() {
        $(byText("Accept All")).click();
        $(byText("Accept All")).should(disappear);

        $(byText("Images")).shouldBe(visible);
        $(byText("Images")).click();
    }

    @When("enter a keyword {string} in input field")
    public void enterKeyword(String keyword) {
        this.keyword = keyword;
        $(byText("Accept All")).click();
        $(byText("Accept All")).should(disappear);
        $(by.name("q")).shouldBe(visible);
        $(by.name("q")).val(keyword).pressEnter();
        screenshot("Images");
    }

    @Then("at least top {int} matching images should be shown")
    public void topTenMatchedImagesShouldBeShown(int resultsCount) {
        $$(".rg_i").shouldHave(sizeGreaterThanOrEqual(resultsCount));
    }
}