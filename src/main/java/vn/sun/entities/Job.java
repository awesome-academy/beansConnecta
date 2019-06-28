package vn.sun.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "jobs")
public class Job {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "{validateJob.title.notNull}")
	private String title;
	@NotNull(message = "{validateJob.description.notNull}")
	private String description;
	@NotNull(message = "{validateJob.requirement.notNull}")
	private String requirement;

	public enum tag {
		FULLTIME, PARTIME, INTERN, FREELANCE
	}

	@Column(columnDefinition = "enum('FULLTIME', 'PARTIME', 'INTERN', 'FREELANCE')")
	@Enumerated(EnumType.STRING)
	private tag tag;

	@NotNull(message = "{validateJob.pay.notNull}")
	@Positive(message = "{validateJob.pay.positive}")
	private BigDecimal minPay;

	@NotNull(message = "{validateJob.pay.notNull}")
	@Positive(message = "{validateJob.pay.positive}")
	private BigDecimal maxPay;

	private boolean isActive;

	@Positive(message = "{validateJob.quantity.positive}")
	@Max(value = 999, message = "{validateJob.quantity.tooMany}")
	private Integer quantity;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime")
	private Date createTime;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateTime")
	private Date updateTime;

	@ManyToOne
	@JoinColumn(name = "companyId", nullable = false)
	private Company company;

	@ManyToOne
	@JoinColumn(name = "jobTypeId", nullable = false)
	private JobType jobType;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {
			CascadeType.MERGE,
			CascadeType.PERSIST,
	})
	@JoinTable(name = "applies",
		joinColumns = @JoinColumn(name = "jobId", nullable = false, updatable = false),
		inverseJoinColumns = @JoinColumn(name = "candidateId", nullable = false, updatable = false))
	private List<Candidate> candidates;

	@Override
	public String toString() {
		return "Job [id=" + id + ", title=" + title + ", tag=" + tag + ", quantity=" + quantity + ", company=" + company
				+ ", jobType=" + jobType + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public tag getTag() {
		return tag;
	}

	public void setTag(tag tag) {
		this.tag = tag;
	}

	public BigDecimal getMinPay() {
		return minPay;
	}

	public void setMinPay(BigDecimal minPay) {
		this.minPay = minPay;
	}

	public BigDecimal getMaxPay() {
		return maxPay;
	}

	public void setMaxPay(BigDecimal maxPay) {
		this.maxPay = maxPay;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

}
