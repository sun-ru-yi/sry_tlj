package com.srytzj.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sry
 * @description
 * @date 2022/2/18 3:06 下午
 * @Version 1.0
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public static final int OK_CODE = 200;
	public static final int ERROR_CODE = 1;

	public R() {
		put("code", OK_CODE);
		put("msg", "操作成功");
	}

	public static R error() {
		return error(ERROR_CODE, "操作失败");
	}

	public static R error(String msg) {
		return error(ERROR_CODE, msg);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R error(int code, String msg, Map<String, Object> map) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		r.putAll(map);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public int getCode() {
		return Integer.parseInt(get("code").toString());
	}

	public String getMsg() {
		return get("msg").toString();
	}

	public String getData() {
		return get("data").toString();
	}

    public R putData(Object data) {
		this.put("data", data);
		return this;
    }

    public static boolean okFlag(R r) {
	    return r != null && r.getCode() == OK_CODE;
    }

	public static boolean failFlag(R r) {
		return !okFlag(r);
	}

}
