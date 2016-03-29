package or.jp.kimiyo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sample_table database table.
 * 
 */
@Entity
@Table(name="sample_table")
@NamedQuery(name="SampleTable.findAll", query="SELECT s FROM SampleTable s")
public class SampleTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	public SampleTable() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}