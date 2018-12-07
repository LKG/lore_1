package im.heart.usercore.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

import im.heart.core.entity.AbstractEntity;

/**
 * 
 * @author gg
 * @Desc : 用户关系表
 */
@Entity()
@Table(name = "dic_frame_user_relate")
@DynamicUpdate()
@DynamicInsert()
@Data
public class FrameUserRelate implements AbstractEntity<BigInteger> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9013579269873331336L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 32, name = "RELATE_ID", nullable = false, unique = true, updatable = false)
	private BigInteger relateId;

	@NotNull
	@Column(length = 32, name = "USER_ID", nullable = false, updatable = false)
	private BigInteger userId;
	
	@Column(length = 32, name = "RELATE_TYPE", nullable = false, updatable = false)
	private String relateType;
	
	@NotNull
	@Column(length = 32, name = "RELATE_USER_ID", nullable = false, updatable = false)
	private BigInteger relateUserId;
	
	@JSONField(serialize = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@PrePersist
	protected void onCreate() {
		createTime = new Date();
	}

}
