package im.heart.core.plugins.ip;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import im.heart.core.utils.OkHttpClientUtils;

/**
 * 
 * @author gg
 * @desc : iP 解析接口 实现类，通过调用淘宝iP 解析服务实现
 */
@Component(TaoBaoIp.BEAN_NAME)
public class TaoBaoIp implements IpParse {
	public static final String BEAN_NAME = "taoBaoIpParser";
	protected static final Logger logger = LoggerFactory.getLogger(TaoBaoIp.class);
	final static String API_URL = "http://ip.taobao.com/service/getIpInfo.php";
	final static String CODE = "code";
	final static String DATA = "data";
	final static String TAOBAO_IP_SUCCESS = "0";
	public static IpInfo getTaoBaoIp(String ip) throws Exception {
		if (StringUtils.isNotBlank(ip)) {
			logger.debug(ip);
			String result = OkHttpClientUtils.fetchEntityString(API_URL+"?ip="+ip);
			JSONObject json = JSON.parseObject(result);
			if (TAOBAO_IP_SUCCESS.equals(json.getString(CODE))) {
				return JSON.parseObject(json.getString(DATA),IpInfo.class);
			}
			logger.debug(result);
		}
		return null;
	}
	@Override
	public String getIpInfo(String ip) throws IpParseException {
		try {
			return JSON.toJSONString(getIp(ip));
		} catch (Exception e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		}
		return null;
	}
	@Override
	public IpInfo getIp(String ip) throws IpParseException {
		try {
			return getTaoBaoIp(ip);
		} catch (Exception e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		}
		return null;
	}
}