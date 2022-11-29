package com.hsf.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {

    By id = By.id("username_box_id");

    @FindBy(name = "passw")
    WebElement otherId;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigatePage(String url) {
        waitElement(otherId);
        click(otherId);

        super.navigate("https://accounts.saucelabs.com/am/XUI/?region=eu-central-1&next=%2Farchives%2Fvdc%3Fq%3Ddate%253A%2520%255B2022-10-29T00%253A00%253A00%2520TO%25202022-11-29T23%253A59%253A59%255D%26start%3D1669737599#login/");
    }

}
