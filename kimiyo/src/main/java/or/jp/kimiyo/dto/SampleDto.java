package or.jp.kimiyo.dto;

import java.io.Serializable;

/**
 * sample_table用DTOクラス
 *
 * @author olee
 *
 */
public class SampleDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	private String id;
	/** Name */
	private String name;

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id セットする id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
