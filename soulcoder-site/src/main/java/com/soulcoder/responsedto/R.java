package com.soulcoder.responsedto;

import com.soulcoder.enums.ResponseStatus;


/**
 * The type R.
 *
 * @param  the type parameter
 */
public  class R  {




	/**
     * Ok r.
     *
     * @param data the data
     * @return the r
     */

    public   static R ok(ResponseBase datas)
	{
		R r=new R();
		r.status=ResponseStatus.Success.ordinal();
		r.errormsg="";
		r.data=datas;

		return r;
	}
private  R(){}
    /**
     * Ok r.
     *
     * @return the r
     */
    public  static R ok()
	{
		return ok(null);
	}

	/**
	 * Failed r.
	 *
	 * @param errMsg the err msg
	 * @param data   the data
	 * @return the r
	 */
	public static R failed(String errMsg)
	{
		R r=new R();
		r.status=ResponseStatus.Failed.getIndex();
		r.errormsg=errMsg;
		r.data=null;
		return r;
	}

    /**
     * Failed r.
     *
     * @param errMsg the err msg
     * @param data   the data
     * @return the r
     */
    public static R failed(String errMsg,ResponseBase datax)
	{
		R r=new R();
		r.status=ResponseStatus.Failed.getIndex();
		r.errormsg=errMsg;
		r.data=datax;
		return r;

	}



    /**
     * Exception r.
     *
     * @param errMsg the err msg
     * @param ex     the ex
     * @param data   the data
     * @return the r
     */
    public static R exception(String errMsg,Exception exs,ResponseBase datas)
	{
		R r=new R();
		r.status=ResponseStatus.Failed.getIndex();
		r.errormsg=errMsg;
		r.data=datas;
		r.ex=exs;
		return r;

	}

    /**
     * Exception r.
     *
     * @param errMsg the err msg
     * @param ex     the ex
     * @return the r
     */
    public static  R exception(String errMsg,Exception ex)
	{
		 return exception(errMsg, ex,null);
	}

    /**
     * Exception r.
     *
     * @param ex the ex
     * @return the r
     */
    public static R exception(Exception ex)
	{
		 return exception(ex.getMessage(), ex,null);
	}
private  transient final long serialVersionUID = 1L;
	
	private  int status=ResponseStatus.Success.getIndex();
	private  String errormsg="";
	private  ResponseBase data;

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public ResponseBase getData() {
		return data;
	}

	public void setData(ResponseBase data) {
		this.data = data;
	}

	public Exception getEx() {
		return ex;
	}

	public void setEx(Exception ex) {
		this.ex = ex;
	}

	private  transient Exception ex=null;
}


