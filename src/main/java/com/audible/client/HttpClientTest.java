package com.audible.client;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {

  static String URL = "https://carnaval.amazon.com/v1/unifiedSearch/v2018/simpleSearch.do?searchFormType=&searchString=AudibleMembershipManagementService";

  public static void main(String[] args) throws Throwable {
    BasicCookieStore cookieStore = new BasicCookieStore();
    addCookie(cookieStore, "JSESSIONID","81A40BF428FCE7DFFED36F03E7853C22");
    addCookie(cookieStore,"JSESSIONID","C7BE7842305380C3FFEB1A9D7D52B71B");
    addCookie(cookieStore,"amzn_sso_token","eyJ0eXAiOiJKV1MiLCJhbGciOiJSUzI1NiIsImtpZCI6IjM2O.eyJpc3MiOiJodHRwczovL21pZHdheS1hdXRoLmFtYXpvbi5jb20iLCJzdWIiOiJsdWtzYW1hZCIsImF1ZCI6Imh0dHBzOi8vY2FybmF2YWwuYW1hem9uLmNvbTo0NDMiLCJleHAiOjE1MzkxOTUyODMsImlhdCI6MTUzOTE5NDM4MywiYXV0aF90aW1lIjoxNTM5MTc0MTI0LCJub25jZSI6ImQwYjg5MzQ4MzAzZDA4MjliZjg1NzcwNjA3M2FjMmQ5NGJjOTFjNmRiYWVmNWIzYWMxMzlhZmNmZmE1MTcyZWIiLCJhbXIiOiJbXCJwd2RcIiwgXCJ1MmZcIiwgXCJwZXNcIl0iLCJ0cnVzdF9zY29yZSI6MSwicmVxX2lkIjoiMTRiNzYyYzEtZTM3MC00ZDQzLWFjNGMtOGE2YmM5OTQzMWJjIiwibWlkd2F5X3RydXN0X3Njb3JlIjpudWxsLCJhcGVzX3Jlc3VsdHMiOnsiVFpfQ09SUCI6eyJzdGF0dXMiOiJTVUNDRVNTIiwiY29uZmlkZW5jZSI6bnVsbCwic3RlcHVwQ2xhaW1zIjpbXSwiZmFpbHVyZUJsb2Nrc0FjY2VzcyI6dHJ1ZSwicGFzcyI6dHJ1ZSwiY2xhaW1zIjpbXX19fQ.IQuskPU7aDvcCR4kTE2-aAMmqzts91qO-p9wTi14O3tAT79tD0CVvw6Txj1sdYF8jZu6lcXb5KR-sbHQYSmiKpbsEfBTEcCrqoc2aPBITSgdtHwvXK-4Q4-Yhsy6hocrb6LCP6O2Z9u8qmnFGfwMhWhgBGrBC70WJEX1P1_UAHlm9FqBLjVsHl0hOJSG2AMP7sW8e-_DP94Rs5mUzBGDClZsFw7W_d4yEG6pOxvMOouULtinI1pb1DSPeDKMdn8HYNAOV2HOtjf2flusQlIF9FXQOeYesqf0Xavsicp5Hd50sACIct9-nZxG5FUhrAy3v02LUhj5tibeMq1qzYtTXw");

    CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
    HttpGet httpGet = new HttpGet(URL);


    CloseableHttpResponse response1 = httpClient.execute(httpGet);
    try {
      System.out.println(response1.getStatusLine());
      HttpEntity entity1 = response1.getEntity();
      EntityUtils.consume(entity1);
    } finally {
      response1.close();
    }

    /*
    HttpPost httpPost = new HttpPost("http://targethost/login");
    List<NameValuePair> nvps = new ArrayList<NameValuePair>();
    nvps.add(new BasicNameValuePair("username", "vip"));
    nvps.add(new BasicNameValuePair("password", "secret"));
    httpPost.setEntity(new UrlEncodedFormEntity(nvps));
    CloseableHttpResponse response2 = httpclient.execute(httpPost);

    try {
      System.out.println(response2.getStatusLine());
      HttpEntity entity2 = response2.getEntity();
      EntityUtils.consume(entity2);
    } finally {
      response2.close();
    }*/

  }

  public static void addCookie(BasicCookieStore cookieStore, String name, String value){
    BasicClientCookie cookie = new BasicClientCookie(name, value);
    cookie.setDomain("");
    cookie.setPath("");
    cookieStore.addCookie(cookie);
  }



}
