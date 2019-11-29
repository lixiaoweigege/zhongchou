package com.dushan.zhongchou.util;

/**
 * 众筹整个项目都使用这个类型给Ajax请求返回响应，让Ajax请求接收到的响应体格式保持一�?
 * @author 吴彦�?
 *
 * @param <T>
 */
public class ResultEntity<T> {
	
	private String result;
	private T data;
	private String message;
	
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";
	public static final String NO_DATA = "NO_DATA";
	public static final String NO_MESSAGE = "NO_MESSAGE";
	
	/**
	 * 给Ajax返回响应数据时成功且没有要查询的数据
	 * @return ResultEntity对象作为Ajax请求的响应体
	 */
	public static ResultEntity<String> successWithoutData() {
		return new ResultEntity<String>(SUCCESS, NO_DATA, NO_MESSAGE);
	}
	
	/**
	 * 给Ajax返回响应数据时成功且有要查询的数�?
	 * @param data 当前请求要查询的数据
	 * @return ResultEntity对象作为Ajax请求的响应体
	 */
	public static <Data> ResultEntity<Data> successWithData(Data data) {
		return new ResultEntity<Data>(SUCCESS, data, NO_MESSAGE);
	}
	
	/**
	 * 给Ajax返回响应数据时失�?
	 * @param message 当前失败相关错误消息
	 * @return ResultEntity对象作为Ajax请求的响应体
	 */
	public static <Data> ResultEntity<Data> failed(String message) {
		return new ResultEntity<Data>(FAILED, null, message);
	}
	
	public ResultEntity() {
		
	}

	public ResultEntity(String result, T data, String message) {
		super();
		this.result = result;
		this.data = data;
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResultEntity [result=" + result + ", data=" + data + ", message=" + message + "]";
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
