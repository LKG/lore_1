package im.heart.usercore.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import im.heart.core.entity.AbstractEntity;

/**
 * 
 * @author gg
 * 机构关联表，设置机构非直属关联关系
 */

@Entity()
@Table(name = "dic_frame_org_relate")
@DynamicUpdate()
@DynamicInsert()
@Data
public class FrameOrgRelate implements AbstractEntity<BigInteger> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4462546725686348247L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 32, name = "RELATE_ID", nullable = false, unique = true, updatable = false)
	private BigInteger relateId;


	@NotNull
	@Column(length = 32, name = "ORG_ID", nullable = false, updatable = false)
	private BigInteger orgId;
	
	@Column(length = 32, name = "RELATE_TYPE", nullable = false, updatable = false)
	private String relateType;
	
	@ManyToOne(cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name="RELATE_ORG_ID")
    @JSONField(serialzeFeatures={SerializerFeature.DisableCircularReferenceDetect})
	private FrameOrg relateOrg;
	
	@JSONField(serialize = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name = "IS_DEFAULT", nullable = false,length=2)
	private Boolean isDefault=Boolean.FALSE;
	@PrePersist
	protected void onCreate() {
		createTime = new Date();
	}
	
}
