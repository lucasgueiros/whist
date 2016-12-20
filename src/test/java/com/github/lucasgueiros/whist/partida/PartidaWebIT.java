/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.partida;

import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author lucas
 */
public class PartidaWebIT{ 

    private static WebDriver driver;
    private static String baseUrl;
    private boolean acceptNextAlert = true;
    private static StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass // (alwaysRun = true)
    public static void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testJogoContraMaquinas(){
        driver.get(baseUrl + "/ifuwhist/");
        driver.findElement(By.linkText("Entrar")).click();
        driver.findElement(By.id("oform:oLoginField")).clear();
	driver.findElement(By.id("oform:oLoginField")).sendKeys("usuario1");
	driver.findElement(By.id("oform:oSenhaField")).clear();
	driver.findElement(By.id("oform:oSenhaField")).sendKeys("123");
        driver.findElement(By.partialLinkText("Criar mesa")).click();
        driver.findElement(By.id("formulario:campoNome")).clear();
        driver.findElement(By.id("formulario:campoNome")).sendKeys("sala");
        driver.findElement(By.partialLinkText("Um contra maquinas")).click();
        driver.findElement(By.partialLinkText("Entrar em uma sala")).click();
        driver.findElement(By.partialLinkText("Entrar")).click();
        driver.findElement(By.partialLinkText("Eu quero jogar")).click();
        driver.findElement(By.partialLinkText("Comecar a jogar")).click();
    }
    
    @AfterClass // (alwaysRun = true)
    public static void tearDown() throws Exception {
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

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

}
