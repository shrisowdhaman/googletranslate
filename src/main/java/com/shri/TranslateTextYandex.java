package com.shri;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import com.google.gson.JsonObject;

/**
 * @author shrisowdhaman
 * Dec 1, 2017
 */
public class TranslateTextYandex {
	 
	public static void main(String[] args) {
		
		 
		 try {
			String myUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20171201T164638Z.c519d6eb482cde56.af1d2615ca1fe6e3c983ee4d72ca5200b9b70a66&text=how%20are%20you&lang=en-th&[format=plain]";
			
			System.out.println("Requeted URL:" + myUrl);
			StringBuilder sb = new StringBuilder();
			URLConnection urlConn = null;
			InputStreamReader in = null;
			try {
				URL url = new URL(myUrl);
				urlConn = url.openConnection();
				if (urlConn != null)
					urlConn.setReadTimeout(60 * 1000);
				if (urlConn != null && urlConn.getInputStream() != null) {
					in = new InputStreamReader(urlConn.getInputStream(),
							Charset.defaultCharset());
					BufferedReader bufferedReader = new BufferedReader(in);
					if (bufferedReader != null) {
						int cp;
						while ((cp = bufferedReader.read()) != -1) {
							sb.append((char) cp);
						}
						bufferedReader.close();
					}
				}
			in.close();
			} catch (Exception e) {
				throw new RuntimeException("Exception while calling URL:"+ myUrl, e);
			} 
	 
			System.out.println("Response : "+sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		   

 }

}
