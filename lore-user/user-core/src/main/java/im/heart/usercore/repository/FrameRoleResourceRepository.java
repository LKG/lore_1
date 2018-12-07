package im.heart.usercore.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import im.heart.usercore.entity.FrameRoleResource;

/**
 * 
 * @author gg
 * @Desc :用户角色关联 Repository
 */
public interface FrameRoleResourceRepository extends JpaRepository<FrameRoleResource, BigInteger>,JpaSpecificationExecutor<FrameRoleResource>  {
	
	public List<FrameRoleResource> findByRoleCode(String roleCode);

	public List<FrameRoleResource> findByRoleIdIn(BigInteger... roleIds);
	public List<FrameRoleResource> findByRoleIdIn(Iterable<BigInteger> roleIds);
	public List<FrameRoleResource> findByRoleCodeIn(Iterable<String> roleCodes);

	public List<FrameRoleResource> findByRoleCodeIn(String... roleCodes);
	@Modifying
	@Transactional
	@Query("DELETE FROM FrameRoleResource model WHERE model.roleId = :roleId")
	public void deleteByRoleId(@Param("roleId") BigInteger roleId);
}
