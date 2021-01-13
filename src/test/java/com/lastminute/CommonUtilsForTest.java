/**
 * @author
 * @date
 * @version
 */

package com.lastminute;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.lastminute.model.ShoppingBasket;
import com.lastminute.util.Mapper;

/**
 *
 */
public class CommonUtilsForTest {

    private static final String REQUEST_EXAMPLE = "json/RequestExample1.json";
    private static final String REQUEST_EXAMPLE2 = "json/RequestExample2.json";
    private static final String REQUEST_EXAMPLE3 = "json/RequestExample3.json";
    private static final String REQUEST_KO_VALIDATION = "json/RequestKoValidation1.json";

    public static Path getPathFromClasspath(String classpath) throws URISyntaxException {
        return Paths.get(ClassLoader.getSystemResource(classpath).toURI());
    }
    
    public static ShoppingBasket getRequestKoValidation1() throws Exception
    {
		return getJson(REQUEST_KO_VALIDATION);
    }
    
    public static ShoppingBasket getRequestExample1() throws Exception
    {
		return getJson(REQUEST_EXAMPLE);
    }
    
    public static ShoppingBasket getRequestExample2() throws Exception
    {
		return getJson(REQUEST_EXAMPLE2);
    }
    
    public static ShoppingBasket getRequestExample3() throws Exception
    {
		return getJson(REQUEST_EXAMPLE3);
    }


    private static ShoppingBasket getJson(String path) throws Exception {

        InputStream is = CommonUtilsForTest.class.getClassLoader().getResourceAsStream(path);
        BufferedReader streamReader = null;
        try {
            streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw e;
        }
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        String request = responseStrBuilder.toString();
        return Mapper.getObjectMapper().readValue(request, ShoppingBasket.class);
    }
    
	public static String asJsonString(final Object obj) {
		try {
			Mapper.getObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			return Mapper.getObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
