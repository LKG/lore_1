package im.heart.core.plugins.ip;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 
 * @author gg
 * @desc IP model 类
 */
@Data
public class IpInfo {
	private String country;
	@JSONField(name="country_id")
	private String countryId;
	private String area;
	@JSONField(name="area_id")
	private String areaId;
	private String region;
	@JSONField(name="region_id")
	private String regionId;
	private String county;
	@JSONField(name="county_id")
	private String countyId;
	private String isp;
	@JSONField(name="isp_id")
	private String ispId;
	private String ip;
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}