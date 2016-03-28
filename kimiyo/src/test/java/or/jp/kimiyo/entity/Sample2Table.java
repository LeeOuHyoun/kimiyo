package or.jp.kimiyo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sample2_table database table.
 * 
 */
@Entity
@Table(name="sample2_table")
@NamedQuery(name="Sample2Table.findAll", query="SELECT s FROM Sample2Table s")
public class Sample2Table implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	public Sample2Table() {
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