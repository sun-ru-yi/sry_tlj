package com.srytzj.util;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author sry
 * @description 分页查询类
 * @date 2022/2/21 5:53 下午
 * @Version 1.0
 */
public class PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer total;
	private List<?> rows;

	public PageUtils() {
	}

	public PageUtils(List<?> list, Integer total) {
		this.rows = list;
		this.total = total;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
