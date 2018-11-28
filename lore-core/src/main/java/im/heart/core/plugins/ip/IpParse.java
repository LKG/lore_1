package im.heart.core.plugins.ip;
/**
 * 
 * @author gg
 * @desc iP 解析接口
 */
public interface IpParse {
	/**
	 * 
	 * @Desc： 根据Ip 获取IpInfo 对象
	 * @param ip
	 * @return
	 * @throws IpParseException
	 */
	public  IpInfo getIp(String ip) throws IpParseException;

	/**
	 * @Desc： 根据Ip 获取IpInfo json 格式
	 * @param ip
	 * @return
	 * @throws IpParseException
	 */
	public  String getIpInfo(String ip) throws IpParseException;
}
