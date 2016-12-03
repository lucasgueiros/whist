package com.github.lucasgueiros.ifuwhist.testWeb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CadastrarUsuarioIT {
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
	public void testELogin() throws Exception {
		driver.get(baseUrl + "/ifuwhist/");
		driver.findElement(By.linkText("Logar")).click();
		driver.findElement(By.id("oform:oLoginField")).clear();
		driver.findElement(By.id("oform:oLoginField")).sendKeys("novousuario");
		driver.findElement(By.id("oform:oSenhaField")).clear();
		driver.findElement(By.id("oform:oSenhaField")).sendKeys("novasenha");
		driver.findElement(By.id("oform:oCadastrarButton")).click();
		driver.findElement(By.id("oform:oNomeField")).clear();
		driver.findElement(By.id("oform:oNomeField")).sendKeys("Nome Novo Usuario");
		
		assertEquals(driver.findElement(By.id("oform:oMensagemBoasVindas")).getText(), "Bem-vindo, lucas");
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
