package com.github.lucasgueiros.ifuwhist.testWeb;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LogarECriarMesaIT {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLogarECriarMesa() throws Exception {
    driver.get(baseUrl + "/ifuwhist/");
    driver.findElement(By.linkText("Logar")).click();
    driver.findElement(By.id("oform:oLoginField")).clear();
    driver.findElement(By.id("oform:oLoginField")).sendKeys("firefox");
    driver.findElement(By.id("oform:oSenhaField")).clear();
    driver.findElement(By.id("oform:oSenhaField")).sendKeys("123");
    driver.findElement(By.id("oform:oLoginButton")).click();
    driver.findElement(By.xpath("//div[@id='oform:j_idt9']/ul/li[4]/a/span")).click();
    driver.findElement(By.xpath("//tbody[@id='j_idt5:jogadores_data']/tr/td[2]")).click();
    driver.findElement(By.id("j_idt5:jogadores:j_idt33")).click();
    driver.findElement(By.id("j_idt5:jogadores:j_idt30")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
}
