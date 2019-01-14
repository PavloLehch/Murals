package pl.coderslab.mural.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "mural")
public class Mural {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String nameMural;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private List<Author> authorsMural = new ArrayList<Author>();

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private District districtMural;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Street streetMural;

	@Column(name = "house")
	private String numberHouseMural;

	@Column(name = "created", updatable = false)
	// @CreatedDate
	@CreationTimestamp
	private Date dateCreateMural;

	@Column(name = "updated")
	@LastModifiedDate
	// @UpdateTimestamp
	private Date dateUpdateMural;

	@Column(name = "picture")
	private String pictureMural;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNameMural() {
		return nameMural;
	}

	public void setNameMural(String nameMural) {
		this.nameMural = nameMural;
	}

	public List<Author> getAuthorsMural() {
		return authorsMural;
	}
	
//	public String getAuthorsMural() {
//		String clearAuthorsMural = authorsMural.toString();
//		return clearAuthorsMural.substring(1, clearAuthorsMural.length() - 1);
//	}

	public void setAuthorsMural(List<Author> authorsMural) {
		this.authorsMural = authorsMural;
	}

	public District getDistrictMural() {
		return districtMural;
	}

	public void setDistrictMural(District districtMural) {
		this.districtMural = districtMural;
	}

	public Street getStreetMural() {
		return streetMural;
	}

	public void setStreetMural(Street streetMural) {
		this.streetMural = streetMural;
	}

	public String getNumberHouseMural() {
		return numberHouseMural;
	}

	public void setNumberHouseMural(String numberHouseMural) {
		this.numberHouseMural = numberHouseMural;
	}

	public Date getDateCreateMural() {
		return dateCreateMural;
	}

	public void setDateCreateMural(Date dateCreateMural) {
		this.dateCreateMural = dateCreateMural;
	}

	public Date getDateUpdateMural() {
		return dateUpdateMural;
	}

	public void setDateUpdateMural(Date dateUpdateMural) {
		this.dateUpdateMural = dateUpdateMural;
	}

	public String getPictureMural() {
		return pictureMural;
	}

	public void setPictureMural(String pictureMural) {
		this.pictureMural = pictureMural;
	}

}
