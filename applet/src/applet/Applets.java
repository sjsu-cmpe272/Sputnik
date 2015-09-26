package applet;

import java.applet.*;
import java.awt.*;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.StringTokenizer;

@SuppressWarnings("deprecation")
public class Applets extends Applet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String consumer_Key = "zVtLcFdnOa9m3ZQx4YOwbLzJn";
	static String consumer_Secret = "TPeppb9NYZNpx48Fn4k74KGpzBIMoz5b1n29X6ETfTh3eCR8AU";
	static String access_Token = "42872740-2kvnm97y4w4hgmcCPdDBexDBZKhWAzx8frKBaYn0R";
	static String access_Token_Secret = "wUFfJJ3aicFuwAntSv22sHEUA8vAiUyGWg7eirc1wmmmV";
	private static HttpClient httpClient;
	
	public void paint(Graphics g){	   
	   
		String body="";
		//int statusCode=0;
		HttpResponse httpResponse=null;
		OAuthConsumer oAuth = new CommonsHttpOAuthConsumer(consumer_Key,consumer_Secret);
		oAuth.setTokenWithSecret(access_Token, access_Token_Secret);
		
		/********************code from bharath****************************/

		HttpGet httpGet2 = new HttpGet("https://api.twitter.com/1.1/account/verify_credentials.json");

		try {
			
			oAuth.sign(httpGet2);
			
		} catch (OAuthMessageSignerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (OAuthExpectationFailedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (OAuthCommunicationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		httpClient = new DefaultHttpClient();
		
		try {
			ResponseHandler<String> handler = new BasicResponseHandler();
			httpResponse = httpClient.execute(httpGet2);			
			//statusCode = httpResponse.getStatusLine().getStatusCode();
			body = handler.handleResponse(httpResponse);
			
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
		/******** /account/verify_credentials Parsing **********/
		
		String res="";
		String screen_name="";
		String name="";
		StringTokenizer s=new StringTokenizer(body,":");
		while (s.hasMoreTokens()) {
			 res=s.nextToken();
			 if(res.contains("screen_name"))
	         {
	        	 StringTokenizer s1=new StringTokenizer(res,"\"");
	        	 name=java.net.URLEncoder.encode(s1.nextToken().toString());
	         }
	         if(res.contains("location"))
	        	break;
	         }
		
		s=new StringTokenizer(res,"\"");
		screen_name=s.nextToken().toString();
	        
		System.out.println("My Name : "+name+" My Screen Name : "+screen_name);
		
		g.drawString("My Name : "+name,40,20);
		
		/********* /account/verify_credentials Parsing Ends**********/
		
		HttpGet httpGet = new HttpGet("https://api.twitter.com/1.1/friends/ids.json?screen_name="+name+"&user_id="+screen_name);
		
		try {
			
			oAuth.sign(httpGet);
			
		} catch (OAuthMessageSignerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (OAuthExpectationFailedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (OAuthCommunicationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		httpClient = new DefaultHttpClient();
		
		try {
			ResponseHandler<String> handler = new BasicResponseHandler();
			httpResponse = httpClient.execute(httpGet);			
			//statusCode = httpResponse.getStatusLine().getStatusCode();
			body = handler.handleResponse(httpResponse);
			
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		/******** /friends/ids.json?screen_name Parsing **********/
	
		int follows;
		s=new StringTokenizer(body,"[");

		res=s.nextToken();
		res=s.nextToken();
			 s=new StringTokenizer(res,"]");
			 res=s.nextToken();
			 	s=new StringTokenizer(res,",");
			 	follows=s.countTokens();
		
		System.out.println("I am Following : "+follows);
		
		g.drawString("I am Following : "+follows,40,40); 
		
		/***************************Code from Kavin *********************************/
		
		
		String resString="";
		body="";
		//statusCode=0;
		String seachstring="kavin";	
		
		httpResponse=null;
		oAuth = new CommonsHttpOAuthConsumer(consumer_Key,consumer_Secret);
		oAuth.setTokenWithSecret(access_Token, access_Token_Secret);

		httpGet2 = new HttpGet("https://api.twitter.com/1.1/search/tweets.json?q="+seachstring);
		
		//HttpGet httpGet = new HttpGet("https://api.twitter.com/1.1/application/rate_limit_status.json");
		//HttpGet httpGet1 = new HttpGet("https://api.twitter.com/1.1/users/lookup.json?user_id=3608955732");
		
		try {
			
			oAuth.sign(httpGet2);
			
		} catch (OAuthMessageSignerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (OAuthExpectationFailedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (OAuthCommunicationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		httpClient = new DefaultHttpClient();
		
		try {
			ResponseHandler<String> handler = new BasicResponseHandler();
			httpResponse = httpClient.execute(httpGet2);			
			//statusCode = httpResponse.getStatusLine().getStatusCode();
			body = handler.handleResponse(httpResponse);
			
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
		/******** /search/tweets.json Parsing **********/
		
		res="";
		int i=0,j=0,k=0;
		String[] tweets=new String[100];
		String [] local = new String[20];
		int[] lno=new int[20];
		s=new StringTokenizer(body,":");

		while (s.hasMoreTokens()) {
			 res=s.nextToken();
			 if(res.contains("\"source\""))
	         {
				 res=res.substring(0, res.length()-10);
	        	 tweets[i]=res;
	        	 i++;
	         }
			 
			 if(res.contains("\"geo_enabled\""))
	         {
				 res=res.substring(0, res.length()-14);
	        	 //System.out.println("Location "+(i+1)+": "+res);
	        	 resString=resString+"Location "+(i+1)+": "+res;
	        	 if(res!=null)
	        	 {
	        	 for(j=0;j<k;j++)
	        	 {
	        		 if(res.contains(local[j]))
	        			 break;
	        	 }
	        	 local[j]=res;
	        	 lno[j]=lno[j]+1;
	        	 if(j==k)
	        		 k++;
	        	 }
	         }

	         
	         }
		resString="";
		for(j=0;j<k;j++)
		{
			 System.out.println(j+" Local : "+local[j]+" Count "+lno[j]);
			 resString=resString+j+" Local : "+local[j]+" Count "+lno[j]+"   ";
   	 	}
		
		g.drawString("We found that "+seachstring+" is being searched from the following timeliness ",40,60);
		g.drawString(resString,40,80);
		
		
		/********* /search/tweets.json Parsing Ends**********/
		 	
			httpGet = new HttpGet("https://api.twitter.com/1.1/statuses/retweets_of_me.json");
		
			try {
				
				oAuth.sign(httpGet);
				
			} catch (OAuthMessageSignerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (OAuthExpectationFailedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (OAuthCommunicationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			httpClient = new DefaultHttpClient();
			
			try {
				ResponseHandler<String> handler = new BasicResponseHandler();
				httpResponse = httpClient.execute(httpGet);			
				//statusCode = httpResponse.getStatusLine().getStatusCode();
				body = handler.handleResponse(httpResponse);
				
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			res="";
			/******** /statuses/retweets_of_me.json Parsing **********/
			
			s=new StringTokenizer(body,":");
			i=0;
			int f=0;
			while (s.hasMoreTokens()) {
				 res=s.nextToken();
				 if(res.contains("\"source\""))
		         {
					 res=res.substring(0, res.length()-10);
		        	 System.out.println("Retweets of My Tweets : "+(i+1)+": "+res);
		        	 resString=resString+" Retweets of My Tweets : "+(i+1)+": "+res+" ";
		        	 tweets[i]=res;
		        	 i++;
		         }
				 else
				 {
					 f=1;
					 
				 }
				 
				 if(f==1)
				 {
					 g.drawString("None retweeted your tweet yet",40,100);
				 }
				 else
					 g.drawString("Retweets of your tweets"+resString,40,100);
				 
			}
			
				/******** /statuses/retweets_of_me.json Parsing **********/
		
		/*******************Code Ends from Kavin******************************/
			
			
			/*******************Code from Neha******************************/
			
			
			body="";
			//statusCode=0;
			httpResponse=null;
			oAuth = new CommonsHttpOAuthConsumer(consumer_Key,consumer_Secret);
			oAuth.setTokenWithSecret(access_Token, access_Token_Secret);

			httpGet2 = new HttpGet("https://api.twitter.com/1.1/trends/place.json?id=1");
			
			try {
				
				oAuth.sign(httpGet2);
				
			} catch (OAuthMessageSignerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (OAuthExpectationFailedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (OAuthCommunicationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			httpClient = new DefaultHttpClient();
			
			try {
				ResponseHandler<String> handler = new BasicResponseHandler();
				httpResponse = httpClient.execute(httpGet2);			
				//statusCode = httpResponse.getStatusLine().getStatusCode();
				body = handler.handleResponse(httpResponse);
				
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			/******** /trends/place.json?id=1 Parsing **********/
			
			res="";
			screen_name="";
			name="";
			i=0;
			String now="";
			String[] trends=new String[10];
			s=new StringTokenizer(body,":");
			while (s.hasMoreTokens()) {
				 res=s.nextToken();
				 if(res.contains("query"))
		         {
		        	 StringTokenizer s1=new StringTokenizer(res,"\"");
		        	 name=s1.nextToken().toString();
		        	 System.out.println("Trend "+(i+1)+": "+name);
		        	 now=now+(i+1)+": "+name;
		        	 trends[i]=name;
		        	 i++;
		         }
		         
		         }
			
			g.drawString("Trends : "+now+" ",40,120);
			
			
			/********* /trends/place.json?id=1 Parsing Ends**********/
			now="";
		
			 httpGet = new HttpGet("https://api.twitter.com/1.1/followers/list.json");
			
			try {
				
				oAuth.sign(httpGet);
				
			} catch (OAuthMessageSignerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (OAuthExpectationFailedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (OAuthCommunicationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			httpClient = new DefaultHttpClient();
			
			try {
				ResponseHandler<String> handler = new BasicResponseHandler();
				httpResponse = httpClient.execute(httpGet);			
				//statusCode = httpResponse.getStatusLine().getStatusCode();
				body = handler.handleResponse(httpResponse);
				
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			/******** /followers/list.json Parsing **********/
			
			s=new StringTokenizer(body,":");
			i=0;
			
			while (s.hasMoreTokens()) {
				 res=s.nextToken();
				// System.out.println("Body : "+res);
				 if(res.contains("location"))
		         {
		        	 StringTokenizer s1=new StringTokenizer(res,"\"");
		        	 name=s1.nextToken().toString();
		        	 System.out.println("Names "+(i+1)+": "+name);
		        	 //responcetext="Names "+(i+1)+": "+name;
		        	 //names[i]=name;
		        	 i++;
		         }
		         
		         }
			
			int no_of_followers=i;
			
			System.out.println("No of Followers : "+no_of_followers);
			
			g.drawString("No of Followers : "+no_of_followers,40,140);
			
			
			
			/****************************End************/
			
			
			/*********************Code from Sonal******************/
			
			httpGet2 = new HttpGet("https://api.twitter.com/1.1/statuses/user_timeline.json");
			body="";
			try {
				
				oAuth.sign(httpGet2);
				
			} catch (OAuthMessageSignerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (OAuthExpectationFailedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (OAuthCommunicationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			httpClient = new DefaultHttpClient();
			
			try {
				ResponseHandler<String> handler = new BasicResponseHandler();
				httpResponse = httpClient.execute(httpGet2);			
				//statusCode = httpResponse.getStatusLine().getStatusCode();
				body = handler.handleResponse(httpResponse);
				
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			
			/******** /statuses/user_timeline.json Parsing **********/
			
			res="";
			now="";
			i=0;
			
			s=new StringTokenizer(body,":");
			while (s.hasMoreTokens()) {
				 res=s.nextToken();
				 if(res.contains("\"source\""))
		         {
					 res=res.substring(0, res.length()-10);
		        	 System.out.println("Tweets "+(i+1)+" : "+res);
		        	 now=" "+now+" "+(i+1)+" : "+res;
		        	 tweets[i]=res;
		        	 i++;
		         }
		         
		         }
			
			g.drawString("Tweets from Timeline : "+now+" ",40,160);
			
			/********* /statuses/user_timeline.json Parsing Ends**********/
			
			httpGet = new HttpGet("https://api.twitter.com/1.1/account/settings.json");
			body="";
		
			try {
				
				oAuth.sign(httpGet);
				
			} catch (OAuthMessageSignerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (OAuthExpectationFailedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (OAuthCommunicationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			httpClient = new DefaultHttpClient();
			
			try {
				ResponseHandler<String> handler = new BasicResponseHandler();
				httpResponse = httpClient.execute(httpGet);			
				//statusCode = httpResponse.getStatusLine().getStatusCode();
				body = handler.handleResponse(httpResponse);
				
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			/******** /account/settings.json Parsing **********/
			
			
			s=new StringTokenizer(body,":");
			i=0;
			String zone="",sname="",lname="";
			
			while (s.hasMoreTokens()) {
				 res=s.nextToken();
				// System.out.println("Body : "+res);
				 if(res.contains("utc_offset"))
		         {
		        	 StringTokenizer s1=new StringTokenizer(res,"\"");
		        	 zone=s1.nextToken().toString();
		        	 System.out.println("My Timezone : "+zone);
		         }
				 if(res.contains("always_use_https"))
		         {
		        	 StringTokenizer s1=new StringTokenizer(res,"\"");
		        	 sname=s1.nextToken().toString();
		        	 System.out.println("My Screen Name : "+sname);
		         }
				 if(res.contains("countryCode"))
		         {
		        	 StringTokenizer s1=new StringTokenizer(res,"\"");
		        	 lname=s1.nextToken().toString();
		        	 System.out.println("My Set Trend Location : "+lname);
		         }
		         
		         }
			
			g.drawString("My Timezone : "+zone,40,180);
			g.drawString("My Screen Name : "+sname,40,200);
			g.drawString("My Set Trend Location : "+lname,40,220);
			
			/******** /account/settings.json Parsing **********/

			/***************Ends****************/

   }
}