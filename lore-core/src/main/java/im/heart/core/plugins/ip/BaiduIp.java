package im.heart.core.plugins.ip;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import im.heart.core.utils.OkHttpClientUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author gg
 * @desc : iP 解析接口 实现类，通过调用淘宝iP 解析服务实现
 */
@Component(BaiduIp.BEAN_NAME)
public class BaiduIp implements IpParse {
	public static final String BEAN_NAME = "baiduIpParser";
	protected static final Logger logger = LoggerFactory.getLogger(BaiduIp.class);
	final static String API_URL = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php";
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
		return null;
	}

	public static void main(String[] args) throws Exception{

	}
}