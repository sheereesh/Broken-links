package brokenLinks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinks {

	public static void main(String[] args) throws MalformedURLException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://www.deadlinkcity.com/");
		int brokenlinks=0;
		List<WebElement> links=driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		for( WebElement link :links) {
			String url=link.getAttribute("href");
			if(url==null||url.isEmpty()) {
				System.out.println("url is empty");
				continue;
			}
			URL linkUrl=new URL(url);
			try {
				HttpURLConnection httpconnect=(HttpURLConnection)linkUrl.openConnection();
				httpconnect.connect();
				if(httpconnect.getResponseCode()>=400) {
					System.out.println(httpconnect.getResponseCode()+url+"  is" +"broken links");
					brokenlinks++;
				}
				else
				{
					System.out.println(httpconnect.getResponseCode()+url+"  is"+ "valid link");

				}

			} catch (Exception e) {
//System.out.println(e);
			}




		}


		System.out.println("no of broken links:" +brokenlinks);

	}

}
