package com.brokenLinkAndUniqueLink;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class brokenLinkAndUniqueLink {

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Viral Korat\\Videos\\QA_Testing\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.facebook.com/");
		List<WebElement> link = driver.findElements(By.tagName("a"));

		System.out.println("Total number of links : " + link.size());
		System.out.println("*************************************************************" + "\n");

		Set<String> uniqueLinks = new HashSet<String>();

		int count = 0;
		for (WebElement links : link) {
			uniqueLinks.add(links.getAttribute("href"));
			System.out.println(count + " : " + links.getText());
			count++;
		}
		
		System.out.println();
		System.out.println("Unique links : " + uniqueLinks.size());
		System.out.println("*************************************************************" + "\n");

		int countX = 0; // For-Each Loop
		for (String url : uniqueLinks) {
			System.out.println(countX + " : " + url);
			countX++;
		}

		/*
		 * //Using Interator method int countY = 0; Iterator<String> itr =
		 * uniqueLinks.iterator(); while (itr.hasNext()) { System.out.println(countY +
		 * " : " + itr.next()); countY++; }
		 */
		System.out.println("*************************************************************" + "\n");
		System.out.println("Valid and Broken link checking...........");
		System.out.println();
		
		// Converting Set to ArrayList
		ArrayList<String> uniqueLinkList = new ArrayList<String>();
		for (String uniqueList : uniqueLinks) {
			uniqueLinkList.add(uniqueList);
		}
		
		int countBroken = 0;
		int countValid = 0;
		for (int i = 0; i < uniqueLinkList.size(); i++) {

			URL linkUrl = new URL(uniqueLinkList.get(i));
			HttpURLConnection httpConn = (HttpURLConnection) linkUrl.openConnection();
			Thread.sleep(2000);

			int respCode = httpConn.getResponseCode();

			if (respCode >= 400) {
				System.out.println(countBroken + " : " + linkUrl + " - is broken link");
				countBroken++;
			} else {
				System.out.println(countValid + " : " + linkUrl + " - is valid link");
				countValid++;
			}
		}
			System.out.println("Total valid link : " + countValid);
			System.out.println("Total broken link : " + countBroken);
	}
}
