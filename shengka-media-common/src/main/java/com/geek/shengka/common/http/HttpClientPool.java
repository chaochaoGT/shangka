package com.geek.shengka.common.http;
//package com.geek.core.http;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.collections.MapUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpRequestBase;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import java.io.IOException;
//
//import org.apache.commons.lang3.ObjectUtils;
//import org.apache.http.Consts;
//import org.apache.http.ParseException;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//
//
//public class HttpClientPool {
//	
//	
//	private static final Logger logger = LoggerFactory.getLogger(HttpClientPool.class);
//
//    private static final int CONN_TIMEOUT = 10 * 1000;
//
//    private static final int SO_TIMEOUT = 10 * 1000;
//
//    private static final String CHAR_SET = "UTF-8";
//
//    private static org.apache.http.client.HttpClient httpClient;
//
//    public static synchronized org.apache.http.client.HttpClient getHttpClient() {
//        if (httpClient == null) {
//            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
//            // 将最大连接数增加
//            cm.setMaxTotal(200);
//            // 将每个路由基础的连接增加
//            cm.setDefaultMaxPerRoute(40);
//            httpClient = HttpClients.custom()
//                    .setConnectionManager(cm).build();
//        }
//        return httpClient;
//    }
//
//    private static void config(HttpRequestBase httpRequestBase) {
//        // 配置请求的超时设置
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectionRequestTimeout(CONN_TIMEOUT)
//                .setConnectTimeout(CONN_TIMEOUT).setSocketTimeout(SO_TIMEOUT).build();
//        httpRequestBase.setConfig(requestConfig);
//    }
//
//    public static void release() {
//        getHttpClient().getConnectionManager().shutdown();
//    }
//
//    /**
//     * Get method
//     *
//     * @param url
//     * @param data
//     * @return
//     */
//    public static String get(String url, String data) throws Exception {
//        if (StringUtils.isNotEmpty(data)) {
//            url = url + "?" + data;
//        }
//        HttpGet get = new HttpGet(url);
//        config(get);
//        try {
//            org.apache.http.client.HttpClient httpClient = getHttpClient();
//            
//            
//            logger.debug("start get!" + " Url:" + url);
//            HttpResponse response = httpClient.execute(get);
//            logger.debug("end get!");
//            if (null == response) {
//                logger.error(" ============> inputStream of the response is null ");
//                throw new RuntimeException("inputStream of the response is null");
//            }
//            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
//                get.abort();
//                logger.error("Get Method failed! Connection ErrCode=" + response.getStatusLine().getStatusCode()
//                        + " Url:" + url);
//                throw new RuntimeException("Get Method failed! Connection ErrCode=" + response.getStatusLine().getStatusCode()
//                        + " Url:" + url);
//            }
//            String result = EntityUtils.toString(response.getEntity(), CHAR_SET);
//            if (null == result || result.isEmpty()) {
//                logger.info(" ====>the response is null ");
//                throw new RuntimeException("the response is null");
//            }
//            return result;
//        } catch (Exception e) {
//            logger.error("Get Method failed! ErrMsg=" + e.getMessage() + " Url:" + url, e);
//            throw e;
//        } finally {
//            get.releaseConnection();
//        }
//    }
//
//    
//    
//    
//    
//    
//    
//    /**
//     * apache Httpclient post
//     *
//     * @param url
//     * @param list
//     * @return
//     * @throws Exception
//     */
//    public static String post(String url, List<NameValuePair> list) throws Exception {
//        HttpPost post = new HttpPost(url);
//        post.setEntity(new UrlEncodedFormEntity(list));
//        try {
//            org.apache.http.client.HttpClient httpClient = getHttpClient();
//            logger.debug("start post!" + " Url:" + url);
//            HttpResponse response = httpClient.execute(post);
//            logger.debug("end post!");
//            if (null == response) {
//                logger.error(" ============> inputStream of the response is null ");
//                throw new RuntimeException("inputStream of the response is null");
//            }
//            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
//                post.abort();
//                logger.error("Post Method failed! Connection ErrCode=" + response.getStatusLine().getStatusCode()
//                        + " Url:" + url);
//                throw new RuntimeException("Post Method failed! Connection ErrCode=" + response.getStatusLine().getStatusCode()
//                        + " Url:" + url);
//            }
//            String result = EntityUtils.toString(response.getEntity(), CHAR_SET);
//            if (null == result || result.isEmpty()) {
//                logger.info(" ====>the response is null ");
//                throw new RuntimeException("the response is null");
//            }
//            return result;
//        } catch (Exception e) {
//            logger.error("Post Method failed! ErrMsg=" + e.getMessage() + " Url:" + url, e);
//            throw e;
//        } finally {
//            post.releaseConnection();
//        }
//    }
//
//    /**
//     * 多态, post方式
//     *
//     * @param url
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    public static String post(String url, Map<String, Object> map) throws Exception {
//        List<NameValuePair> list = new ArrayList<>();
//        for (Map.Entry<String, Object> en : map.entrySet()) {
//            list.add(new BasicNameValuePair(en.getKey(), en.getValue().toString()));
//        }
//        return post(url, list);
//    }
//    
//    
//    /**
//     * 
//     * @param uri
//     *            the request address
//     * @param json
//     *            the request data that must be a JSON string
//     * @param connectTimeout
//     *            the timeout in milliseconds until a connection is established
//     * @param connectionRequestTimeout
//     *            the timeout in milliseconds used when requesting a connection
//     *            from the connection manager
//     * @param socketTimeout
//     *            the socket timeout in milliseconds, which is the timeout for
//     *            waiting for data or, put differently, a maximum period
//     *            inactivity between two consecutive data packets
//     * @return null when method parameter is null, "", " "
//     * @throws IOException
//     *             if HTTP connection can not opened or closed successfully
//     * @throws ParseException
//     *             if response data can not be parsed successfully
//     */
//    public static String postJson(String uri, String json, int connectTimeout, int connectionRequestTimeout, int socketTimeout)
//            throws IOException, ParseException {
//        if (StringUtils.isBlank(uri)) {
//            return null;
//        }
//
//        org.apache.http.client.HttpClient httpClient = getHttpClient();
//        HttpPost httpPost = new HttpPost(uri);
//        
//        
////        post.setHeader("ddd", "vvv");
////        post.setHeader("ddd", "");
//        
//        
//        // Create request data
//        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
//        // Set request body
//        httpPost.setEntity(entity);
//
//        RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeout)
//                .setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).build();
//        httpPost.setConfig(config);
//        // Response content
//        String responseContent = null;
//        HttpResponse response = null;
//        try {
//        	response = httpClient.execute(httpPost);
//            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                responseContent = EntityUtils.toString(response.getEntity(), Consts.UTF_8.name());
//            }
//        } finally {
//           
//        }
//        return responseContent;
//    }
//    
//    
//    
//    /**
//     * 
//     * @param uri
//     *            the request address
//     * @param json
//     *            the request data that must be a JSON string
//     * @param connectTimeout
//     *            the timeout in milliseconds until a connection is established
//     * @param connectionRequestTimeout
//     *            the timeout in milliseconds used when requesting a connection
//     *            from the connection manager
//     * @param socketTimeout
//     *            the socket timeout in milliseconds, which is the timeout for
//     *            waiting for data or, put differently, a maximum period
//     *            inactivity between two consecutive data packets
//     * @return null when method parameter is null, "", " "
//     * @throws IOException
//     *             if HTTP connection can not opened or closed successfully
//     * @throws ParseException
//     *             if response data can not be parsed successfully
//     */
//    public static String postJson(String uri, String json, int connectTimeout, int connectionRequestTimeout, int socketTimeout, Map<String, String> headers)
//            throws IOException, ParseException {
//        if (StringUtils.isBlank(uri)) {
//            return null;
//        }
//
//        org.apache.http.client.HttpClient httpClient = getHttpClient();
//        HttpPost httpPost = new HttpPost(uri);
//        
//        //设置header头属性
//        if(MapUtils.isNotEmpty(headers)) {
//        	for(Map.Entry<String, String> entry : headers.entrySet()) {
//        		httpPost.setHeader(entry.getKey(), entry.getValue());
//        	}
//        }
//        
////        post.setHeader("ddd", "vvv");
////        post.setHeader("ddd", "");
//        
//        
//        // Create request data
//        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
//        // Set request body
//        httpPost.setEntity(entity);
//
//        RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeout)
//                .setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).build();
//        httpPost.setConfig(config);
//        
//        // Response content
//        String responseContent = null;
//        HttpResponse response = null;
//        try {
//        	response = httpClient.execute(httpPost);
//            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                responseContent = EntityUtils.toString(response.getEntity(), Consts.UTF_8.name());
//            }
//        } finally {
//           
//        }
//        return responseContent;
//    }
//    
//    
//
//}
