package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try {
            HashMap<String, String> backgroundColors = new HashMap<String, String>();
            backgroundColors.put("#e0e0e0", "rgba(224, 224, 224, 1)");
            backgroundColors.put("#6fcf97", "rgba(111, 207, 151, 1)");
            backgroundColors.put("#56ccf2", "rgba(86, 204, 242, 1)");
            backgroundColors.put("#bb6bd9", "rgba(187, 107, 217, 1)");

            WebDriverManager.chromedriver().setup();

            WebDriver driver = new ChromeDriver();
            driver.get("https://qacolorpicker.ccbp.tech/");

            WebElement headingEl = driver.findElement(By.className("color-picker-heading"));
            String expectedHeading = "Color Picker";
            String actualHeading = headingEl.getText();

            // check the heading text
            if (expectedHeading.equals(actualHeading)) System.out.println("Heading Checked");
            else System.out.println("Heading Mismatched");

            String expectedHeadingFontSize = "50px";
            String actualHeadingFontSize = headingEl.getCssValue("font-size");

            // check the heading font size
            if (expectedHeadingFontSize.equals(actualHeadingFontSize)) System.out.println("Heading Font Size Checked");
            else System.out.println("Heading Font Size Mismatched");

            List<WebElement> colorbuttons = driver.findElements(By.className("button"));

            WebElement colorPickerContainerEl =  driver.findElement(By.id("colorPickerContainer"));
            WebElement selectedColorEl = driver.findElement(By.id("selectedColorHexCode"));

            // check the color buttons
            for (WebElement colorbutton : colorbuttons) {
                String expectedBackgroundColor = colorbutton.getText();
                String actualBackgroundColor = colorbutton.getCssValue("background-color");

                if (actualBackgroundColor.equals(backgroundColors.get(expectedBackgroundColor))) System.out.println("Button Color " + expectedBackgroundColor + " Matched");
                else System.out.println("Button Color " + expectedBackgroundColor + " Mismatched");
            }

            // check the page color and selected color text
            for (WebElement colorbutton : colorbuttons) {
                colorbutton.click();

                String expectedBackgroundColor = colorbutton.getText();
                String actualColorPickerContainerColor = colorPickerContainerEl.getCssValue("background-color");
                String actualSelectedColorText = selectedColorEl.getText();

                if (actualColorPickerContainerColor.equals(backgroundColors.get(expectedBackgroundColor))) System.out.println("Page Color " + expectedBackgroundColor + " Matched");
                else System.out.println("Page Color " + expectedBackgroundColor + " Mismatched");

                if (expectedBackgroundColor.equals(actualSelectedColorText)) System.out.println("Selected Color Text " + expectedBackgroundColor + " Matched");
                else System.out.println("Selected Color Text " + expectedBackgroundColor + " Mismatched");
            }

            driver.quit();

        }catch (Exception e){
            System.out.println("Error Message throw in "+ e);
        }
    }
}