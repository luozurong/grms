package com.hori.adms.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.text.ParseException;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 获取mac地址
 * @author admin
 *
 */
public class MacAddrUtil {
	
	public static final Log log = LogFactory.getLog(MacAddrUtil.class);
	
	/**
	 * 根据属性名获取其在propeties文件中的值
	 * 
	 * @param propertyname
	 * @return
	 */
	public static String getGlobalPropertiesValue(String propertyname) {

		String propertyValue = "";
		String GLOBAL_PROPERTIES = "/com/jlit/chims/resources/global.properties";
		try {
			Properties properties = new Properties();
			properties.load(MacAddrUtil.class.getClassLoader().getResourceAsStream(GLOBAL_PROPERTIES));
			propertyValue = (String) properties.get(propertyname);
		} catch (IOException e) {
			e.printStackTrace();
			log.info("load properties文件出错！文件名称：" + GLOBAL_PROPERTIES);
		}
		return propertyValue;
	}
	
	public final static String getMacAddress() throws IOException {
		  String os = System.getProperty("os.name");
		  try {
		   if (os.startsWith("Windows")) {
		    return windowsParseMacAddress(windowsRunIpConfigCommand());
		   } else if (os.startsWith("Linux")) {
		    return linuxParseMacAddress(linuxRunIfConfigCommand());
		   } else {
		    throw new IOException("unknown operating system: " + os);
		   }
		  } catch (ParseException ex) {
		   ex.printStackTrace();
		   throw new IOException(ex.getMessage());
		  }
    }
	
	 /*
	  * Linux stuff
	  */
	 private final static String linuxParseMacAddress(String ipConfigResponse) throws ParseException {
	  String localHost = null;
	  try {
	      localHost = InetAddress.getLocalHost().getHostAddress();
	  } catch (java.net.UnknownHostException ex) {
	      ex.printStackTrace();
	      throw new ParseException(ex.getMessage(), 0);
	  }
	  StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
	  String lastMacAddress = null;
	  
	  while (tokenizer.hasMoreTokens()) {
		   String line = tokenizer.nextToken().trim();
		   boolean containsLocalHost = line.indexOf(localHost) >= 0;
		   // see if line contains IP address
		   if (containsLocalHost && lastMacAddress != null) {
		    return lastMacAddress;
		   }
		   // see if line contains MAC address
		   int macAddressPosition = line.indexOf("HWaddr");
		   if (macAddressPosition <= 0)
		    continue;
		   String macAddressCandidate = line.substring(macAddressPosition + 6)
		     .trim();
		   if (linuxIsMacAddress(macAddressCandidate)) {
		    lastMacAddress = macAddressCandidate;
		    continue;
		   }
	  }
	  
		  ParseException ex = new ParseException("cannot read MAC address for "
		    + localHost + " from [" + ipConfigResponse + "]", 0);
		  ex.printStackTrace();
		  throw ex;
	 }
	 
	 private final static boolean linuxIsMacAddress(String macAddressCandidate) {
		    // TODO: use a smart regular expression
		  if (macAddressCandidate.length() != 17)
		       return false;
		  return true;
	 }
	 
	 private final static String linuxRunIfConfigCommand() throws IOException {
		  Process p = Runtime.getRuntime().exec("ifconfig");
		  InputStream stdoutStream = new BufferedInputStream(p.getInputStream());
		  StringBuffer buffer = new StringBuffer();
		  for (;;) {
			   int c = stdoutStream.read();
			   if (c == -1)
			    break;
			   buffer.append((char) c);
		  }
		  String outputText = buffer.toString();
		  stdoutStream.close();
		  return outputText;
	 }
	 /*
	  * Windows stuff
	  */
	 private final static String windowsParseMacAddress(String ipConfigResponse){
	  String localHost = null;
	  try {
		  localHost = InetAddress.getLocalHost().getHostAddress();
	  } catch (java.net.UnknownHostException ex) {
		   ex.printStackTrace();
		   //throw new ParseException(ex.getMessage(), 0);
	  }
	  StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
	  String lastMacAddress = null;
	  while (tokenizer.hasMoreTokens()) {
		   String line = tokenizer.nextToken().trim();
		   // see if line contains IP address
		   if (line.endsWith(localHost) && lastMacAddress != null) {
		    return lastMacAddress;
		   }
		   // see if line contains MAC address
		   int macAddressPosition = line.indexOf(":");
		   if (macAddressPosition <= 0)
		    continue;
		   String macAddressCandidate = line.substring(macAddressPosition + 1)
		     .trim();
		   if (windowsIsMacAddress(macAddressCandidate)) {
		    lastMacAddress = macAddressCandidate;
		    continue;
		   }
	  }
	  
	  return lastMacAddress;
		  //ParseException ex = new ParseException("cannot read MAC address from ["+ ipConfigResponse + "]", 0);
		  //ex.printStackTrace();
		  //throw ex;
	 }
	 
	 private final static boolean windowsIsMacAddress(String macAddressCandidate) {
		  // TODO: use a smart regular expression
		  if (macAddressCandidate.length() != 17)
			   return false;
	   return true;
	 }
	 
	 private final static String windowsRunIpConfigCommand() throws IOException {
		  Process p = Runtime.getRuntime().exec("ipconfig /all");
		  InputStream stdoutStream = new BufferedInputStream(p.getInputStream());
		  StringBuffer buffer = new StringBuffer();
		  for (;;) {
			   int c = stdoutStream.read();
			   if (c == -1)
			    break;
			   buffer.append((char) c);
		  }
		  String outputText = buffer.toString();
		  stdoutStream.close();
		  return outputText;
	 }
}
