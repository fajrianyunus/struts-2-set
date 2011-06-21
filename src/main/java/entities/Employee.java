package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import lib.RandomAlphaNumericStringGenerator;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Employee {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(updatable=false)	
	private Long id;
	
	@Column(columnDefinition="TEXT", updatable=false, nullable=false)
	private String staticKey;
	
	@Column(columnDefinition="TIMESTAMP", updatable=false, nullable=false)
	private Date createdAt;
	
	@Column(columnDefinition="TIMESTAMP", insertable=false)
	private Date updatedAt;
	
	@Version
	@Column(nullable=false)	
	private Long updateCounter;
	
	@Column(columnDefinition="TEXT", nullable=false)
	private String fullName;
	
	@Column(columnDefinition="TEXT", nullable=false)
	private String nric;
	
	@Column(columnDefinition="TEXT", nullable=false)
	private String remarks;	
	
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setStaticKey(String staticKey) {
		this.staticKey = staticKey;
	}
	public String getStaticKey() {
		return staticKey;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdateCounter(Long updateCounter) {
		this.updateCounter = updateCounter;
	}
	public Long getUpdateCounter() {
		return updateCounter;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setNric(String nric) {
		this.nric = nric;
	}
	public String getNric() {
		return nric;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarks() {
		return remarks;
	}	
	
	@PrePersist
	public void beforeInsert() {
		staticKey = RandomAlphaNumericStringGenerator.generate(RandomAlphaNumericStringGenerator.DEFAULT_LENGTH);
		createdAt = new Date();
		updatedAt = null;
		updateCounter = 0L;
	}
	
	@PreUpdate
	public void beforeUpdate() {
		updatedAt = new Date();
	}
}
