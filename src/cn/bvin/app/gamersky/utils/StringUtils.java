package cn.bvin.app.gamersky.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;




import cn.bvin.app.gamersky.config.Config;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;

import android.text.TextUtils;
import android.util.Log;

public class StringUtils {

  public static String getStringFromArray(int[] array) {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for (int i = 0, len = array.length; i < len; i++) {
      if (i == len - 1) {
        sb.append(array[i] + "}");
      } else
        sb.append(array[i] + ",");
    }
    return sb.toString();
  }

  public static String getStringFromArray(float[] array) {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for (int i = 0, len = array.length; i < len; i++) {
      if (i == len - 1) {
        sb.append(array[i] + "}");
      } else
        sb.append(array[i] + ",");
    }
    return sb.toString();
  }

  public static String getStringFromArray(double[] array) {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for (int i = 0, len = array.length; i < len; i++) {
      if (i == len - 1) {
        sb.append(array[i] + "}");
      } else
        sb.append(array[i] + ",");
    }
    return sb.toString();
  }

  public static String getStringFromArray(Array array) {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for (int i = 0, len = Array.getLength(array); i < len; i++) {
      if (i == len - 1) {
        sb.append(Array.get(array, i) + "}");
      } else
        sb.append(Array.get(array, i) + ",");
    }
    return sb.toString();
  }

  public static String getStringFromList(List array) {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for (int i = 0, len = array.size(); i < len; i++) {
      if (i == len - 1) {
        sb.append(array.get(i) + "}");
      } else
        sb.append(array.get(i) + ",");
    }
    return sb.toString();
  }

  /**
   * 
   * @Title: getStringFromMap
   * @Description: 从map返回一个符合URL规范的字符串
   * @return: String
   */
  public static String getStringFromMap(Map<String, String> map) {
    Joiner joiner = Joiner.on("&");
    String paramString = joiner.withKeyValueSeparator("=").join(map);
    return paramString;
  }
  
  /**
   * 
   * @Title: formatJson2Map 
   * @Description: 把json字符串格式化成符合URL规范的字符串
   * @param jsonString
   * @return: String
   */
  public static String formatJson2Map(String jsonString) {
		if (hasAvailableContent(jsonString)) {
			StringBuilder sb = new StringBuilder();
			String[] paramArray = jsonString.substring(1, jsonString.length()-2).replace("\"", "").split(",");
			for (String string : paramArray) {
				String[] paramMap = string.split(":");
				sb.append("&").append(paramMap[0]).append("=").append(paramMap[1]);
			}
			return sb.toString().substring(1);
		}else {
			return jsonString;
		}
	}
  
  /**
   * 
   * @Title: getURLEncodedString 
   * @Description: 返回URL编码过的字符串
   * @param desireString：目标字符串
   * @param charsetName：编码格式
   * @return: Optional<String>
   */
  public static Optional<String> getURLEncodedString(String desireString,String charsetName) {
    Optional<String> encodedString = Optional.absent();
    try {
      encodedString = Optional.of(URLEncoder.encode(desireString, charsetName));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return encodedString;
  }

  /**
   * 
   * @Title: getUTF8URLString 
   * @Description: 获取utf8编码过的URL字符串
   * @param desireString：要编码的字符串
   * @return: Optional<String>
   */
  public static Optional<String> getUTF8URLString(String desireString){
	  
	  return getURLEncodedString(desireString, Config.DEFAULT_CHATSET);
  }
  
  /**
   * 
   * @Title: hazAvailableContent 
   * @Description: 判断该字符串是否有实际内容，不等于null不为空字符串
   * @param content
   * @return: boolean
   */
  public static boolean hazAvailableContent(String content){
    if (!TextUtils.isEmpty(content)) {
        String trimText = content.trim();
        if (!TextUtils.isEmpty(trimText)) {
            return true;
        }
    }
    return false;
}
  
  public static boolean hasAvailableContent(String content) {
    if (!TextUtils.isEmpty(content)) {
      String trimText = content.trim();
      if (!TextUtils.isEmpty(trimText)) {
        return true;
      }
    }
    return false;
  }


}
