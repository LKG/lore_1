package im.heart.usercore.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import im.heart.core.service.CommonService;
import im.heart.usercore.entity.FramePermission;

/**
 * 
 * @author gg
 * @Desc : FramePermission接口
 */
public interface   FramePermissionService extends CommonService<FramePermission, BigInteger>{
	
	public static final String BEAN_NAME = "framePermissionService";
	public List<FramePermission> findAllById(Iterable<BigInteger> ids);
	public List<FramePermission> findAll();

	public Map<BigInteger,String> findPermissionMap();
}
