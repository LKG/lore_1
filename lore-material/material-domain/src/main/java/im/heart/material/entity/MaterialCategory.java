package im.heart.material.entity;

import im.heart.core.entity.TreeEntity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import im.heart.core.enums.Status;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 
 * @author gg
 * 材料分类表
 */

@Entity()
@Table(name = "material_periodical_category")
@DynamicUpdate()
@DynamicInsert()
@Data
public class MaterialCategory implements TreeEntity<BigInteger>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1163397145222684789L;
	
	public MaterialCategory(){
		
	}
	public MaterialCategory(BigInteger categoryId,String categoryCode,String categoryName,BigInteger parentId,Integer level,Status status){
		this.categoryId=categoryId;
		this.categoryCode=categoryCode;
		this.categoryName=categoryName;
		this.parentId=parentId;
		this.level=level;
		this.status=status;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 32, name = "CATEGORY_ID", nullable = false, unique = true, updatable = false)
	private BigInteger categoryId;
	
	@NotNull
	@Column(name = "CATEGORY_NAME", nullable = false,length=128)
	private String categoryName;
	
	@Column(name = "CATEGORY_CODE", nullable = false,updatable = false, length=32)
	private String categoryCode;
	
	@NotNull
	@Column(length = 32, name = "PARENT_ID", nullable = false)
	private BigInteger parentId;
	@Column(name = "STATUS", nullable = false, length = 32)
	@Enumerated(EnumType.STRING)
	private Status status ;
	@JSONField (serialize=false)
	@Column(name = "LEVEL", nullable = false)
	private Integer level=1;
	@JSONField (format="yyyy-MM-dd HH:mm:ss" ,serialize=false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "CREATE_TIME" ,updatable = false)
	private Date createTime;
	
	@JSONField (format="yyyy-MM-dd HH:mm:ss" ,serialize=false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "MODI_TIME" )
	private Date modiTime;
	
	@Column(name = "REMARK", nullable = false, length=512)
	private String remark;
	@Formula(value = "(select model.category_name from material_periodical_category model where model.category_id = parent_id)")
	private String parentName="";
	/**
	 * //是否有子节点
	 */
	@Formula(value = "(select count(*) from material_periodical_category model where model.parent_id = category_id)")
	private boolean hasChildren;
	

	@PrePersist
	protected void onCreate() {
		createTime = new Date();
		modiTime = new Date();
    }
	@PreUpdate
	protected void onUpdate() {
		modiTime = new Date();
    }

	public String getSeparator() {
		// TODO Auto-generated method stub
		return null;
	}


	public String makeSelfParentIds() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getParentIds() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRoot() {
		if (this.getParentId() != null
				&& this.getParentId().toString().equals("0")) {
			return true;
		}
		return false;
	}


	@Override
	public boolean isLeaf() {
		if (isRoot()) {
			return false;
		}
		if (isHasChildren()) {
			return false;
		}
		return true;
	}

	
	
}
