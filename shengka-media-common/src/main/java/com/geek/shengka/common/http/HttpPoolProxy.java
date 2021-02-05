package com.geek.shengka.common.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author jiangxinqiang
 * @date 2019-07-08
 */
public class HttpPoolProxy extends HttpClientPoolUtil {
	
	private static final int CONNECT_TIMEOUT_MILLIIS = 2000; 
	
	private static final int CONNECTION_REQUEST_TIMEOUT_MILLIIS = 1000; 
	
	private static final int SOCKET_TIMEOUT_MILLIIS = 3000; 
	
	
	
	
	public static String postWithParamsForString(String url, Map<String, String> inputParam) {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if(! MapUtils.isEmpty(inputParam)) {
			for(Map.Entry<String, String> entry : inputParam.entrySet()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		
		
		HttpPost httpPost = new HttpPost(url);

		// http 超时时间配置
		setRequestConfig(httpPost);
		CloseableHttpResponse response = null;

		String responseContent = null;
		try {
			CloseableHttpClient httpClient = HttpClientPoolUtil.getHttpClient(url);

			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				responseContent = EntityUtils.toString(response.getEntity(), Consts.UTF_8.name());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseContent;
	}

	  /**
     * 
     * @param uri
     *            the request address
     * @param json
     *            the request data that must be a JSON string
     * @param connectTimeout
     *            the timeout in milliseconds until a connection is established
     * @param connectionRequestTimeout
     *            the timeout in milliseconds used when requesting a connection
     *            from the connection manager
     * @param socketTimeout
     *            the socket timeout in milliseconds, which is the timeout for
     *            waiting for data or, put differently, a maximum period
     *            inactivity between two consecutive data packets
     * @return null when method parameter is null, "", " "
     * @throws IOException
     *             if HTTP connection can not opened or closed successfully
     * @throws ParseException
     *             if response data can not be parsed successfully
     */
    public static String postJson(String uri, String json, int connectTimeout, int connectionRequestTimeout, int socketTimeout)
            throws IOException, ParseException {
        if (StringUtils.isBlank(uri)) {
            return null;
        }
        
        String responseContent = null;
        
        HttpPost httpPost = new HttpPost(uri);
        
        // Create request data
        httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        
        //http 超时时间配置
        setRequestConfig(httpPost);
        CloseableHttpResponse response = null;
       
        
        try {
        	CloseableHttpClient httpClient = HttpClientPoolUtil.getHttpClient(uri);
        	response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                responseContent = EntityUtils.toString(response.getEntity(), Consts.UTF_8.name());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);;
        } finally {
           if(response != null) {
        	   //释放连接
        	   response.close();
           }
        }
        
       
        return responseContent;
    }
    
    /**
     * 
     * @param uri
     *            the request address
     * @return null when method parameter is null, "", " "
     * @throws IOException
     *             if HTTP connection can not opened or closed successfully
     * @throws ParseException
     *             if response data can not be parsed successfully
     */
    public static String get(String uri )
            throws IOException, ParseException {
    	return getJson(uri, CONNECT_TIMEOUT_MILLIIS, CONNECTION_REQUEST_TIMEOUT_MILLIIS , SOCKET_TIMEOUT_MILLIIS );
    }
    
    /**
     * 
     * @param uri
     *            the request address
     * @param json
     *            the request data that must be a JSON string
     * @param connectTimeout
     *            the timeout in milliseconds until a connection is established
     * @param connectionRequestTimeout
     *            the timeout in milliseconds used when requesting a connection
     *            from the connection manager
     * @param socketTimeout
     *            the socket timeout in milliseconds, which is the timeout for
     *            waiting for data or, put differently, a maximum period
     *            inactivity between two consecutive data packets
     * @return null when method parameter is null, "", " "
     * @throws IOException
     *             if HTTP connection can not opened or closed successfully
     * @throws ParseException
     *             if response data can not be parsed successfully
     */
    public static String getJson(String uri , int connectTimeout, int connectionRequestTimeout, int socketTimeout)
            throws IOException, ParseException {
        if (StringUtils.isBlank(uri)) {
            return null;
        }
        
        String responseContent = null;
        
        HttpGet httpGet = new HttpGet(uri);
        
        // Create request data
        //httpGet.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        
        //http 超时时间配置
        setRequestConfig(httpGet);
        CloseableHttpResponse response = null;
       
        
        try {
        	CloseableHttpClient httpClient = HttpClientPoolUtil.getHttpClient(uri);
        	response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                responseContent = EntityUtils.toString(response.getEntity(), Consts.UTF_8.name());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);;
        } finally {
           if(response != null) {
        	   //释放连接
        	   response.close();
           }
        }
        
       
        return responseContent;
    }
    
    
    /**
     * 
     * @param uri
     *            the request address
     * @param json
     *            the request data that must be a JSON string
     * @param connectTimeout
     *            the timeout in milliseconds until a connection is established
     * @param connectionRequestTimeout
     *            the timeout in milliseconds used when requesting a connection
     *            from the connection manager
     * @param socketTimeout
     *            the socket timeout in milliseconds, which is the timeout for
     *            waiting for data or, put differently, a maximum period
     *            inactivity between two consecutive data packets
     * @return null when method parameter is null, "", " "
     * @throws IOException
     *             if HTTP connection can not opened or closed successfully
     * @throws ParseException
     *             if response data can not be parsed successfully
     */
    public static String getJson(String uri , int connectTimeout, int connectionRequestTimeout, int socketTimeout, Map<String, String> headers)
            throws IOException, ParseException {
        if (StringUtils.isBlank(uri)) {
            return null;
        }
        
        String responseContent = null;
        
        HttpGet httpGet = new HttpGet(uri);
        
        //设置header头属性
        if(MapUtils.isNotEmpty(headers)) {
        	for(Map.Entry<String, String> entry : headers.entrySet()) {
        		httpGet.setHeader(entry.getKey(), entry.getValue());
        	}
        }
        
        // Create request data
        //httpGet.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        
        //http 超时时间配置
        setRequestConfig(httpGet);
        CloseableHttpResponse response = null;
       
        
        try {
        	CloseableHttpClient httpClient = HttpClientPoolUtil.getHttpClient(uri);
        	response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                responseContent = EntityUtils.toString(response.getEntity(), Consts.UTF_8.name());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);;
        } finally {
           if(response != null) {
        	   //释放连接
        	   response.close();
           }
        }
        
       
        return responseContent;
    }
    
    
    /**
     * 
     * @param uri
     *            the request address
     * @param json
     *            the request data that must be a JSON string
     * @param connectTimeout
     *            the timeout in milliseconds until a connection is established
     * @param connectionRequestTimeout
     *            the timeout in milliseconds used when requesting a connection
     *            from the connection manager
     * @param socketTimeout
     *            the socket timeout in milliseconds, which is the timeout for
     *            waiting for data or, put differently, a maximum period
     *            inactivity between two consecutive data packets
     * @return null when method parameter is null, "", " "
     * @throws IOException
     *             if HTTP connection can not opened or closed successfully
     * @throws ParseException
     *             if response data can not be parsed successfully
     */
    public static String postJson(String uri, String json, int connectTimeout, int connectionRequestTimeout, int socketTimeout, Map<String, String> headers)
            throws IOException, ParseException {
        if (StringUtils.isBlank(uri)) {
            return null;
        }
        String responseContent = null;
        HttpPost httpPost = new HttpPost(uri);
        
        // Create request data
        httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        
        //设置header头属性
        if(MapUtils.isNotEmpty(headers)) {
        	for(Map.Entry<String, String> entry : headers.entrySet()) {
        		httpPost.setHeader(entry.getKey(), entry.getValue());
        	}
        }
        
        //http 超时时间配置
        setRequestConfig(httpPost);
        CloseableHttpResponse response = null;
        
        
        try {
        	CloseableHttpClient httpClient = HttpClientPoolUtil.getHttpClient(uri);
        	response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                responseContent = EntityUtils.toString(response.getEntity(), Consts.UTF_8.name());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);;
        } finally {
           if(response != null) {
        	   //释放连接
        	   response.close();
           }
        }
        
       
        return responseContent;
    }
    
    

//	public static void main(String []args) throws Exception{
//		String url = "http://user.ywan3.com/lwCategory/list";
//		
//		String json = getJson(url, 1000, 1000 , 1000);
//		System.out.println(json);
//		
// 		url = "http://172.16.11.247:9092//api/v2/test/debug";
// 		String json2 = postJson(url, "{}", 1000, 1000 , 1000);
// 		System.out.println(json2);
//		
//	}
	
}
