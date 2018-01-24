package com.soulcoder.controller;

import com.soulcoder.pojo.SysUser;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The type Abstract controller.
 */
public class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());


	protected SysUser getUser() {
		return (SysUser) SecurityUtils.getSubject().getPrincipal();
	}

	protected Integer getUserId() {
		return getUser().getId();
	}



    /**
     * Gets request.
     *
     * @return the request
     */
    public HttpServletRequest getRequest() {
		return request;
	}

    /**
     * Sets request.
     *
     * @param request the request
     */
    public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

    /**
     * Gets response.
     *
     * @return the response
     */
    public HttpServletResponse getResponse() {
		return response;
	}

    /**
     * Sets response.
     *
     * @param response the response
     */
    public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;

    /**
     * Set req and res.
     *
     * @param request  the request
     * @param response the response
     */
//@ModelAttribute閺�鎯ф躬缁粯鏌熷▔鏇犳畱閸欏倹鏆熼崜宥夋桨鐞涖劎銇氬鏇犳暏Model娑擃厾娈戦弫鐗堝祦,閺�鎯ф躬缁粯鏌熷▔鏇氱瑐闂堛垹鍨悰銊с仛鐠囶檱ction缁鑵戦惃鍕槨娑擃亣顕Ч鍌濈殶閻€劋绠ｉ崜宥夊厴娴兼碍澧界悰宀冾嚉閺傝纭堕敍灞芥礈濮濄倕婀銈嗘煙濞夋洟鍣烽棃銏犲讲娴犮儱浠涙稉锟芥禍娑滎嚞濮瑰倿顣╂径鍕倞閿涘苯鍩勯悽銊︻劃閻楄锟窖冩皑閸欘垯浜掗幎鍊熻杽閸戣桨绔存稉顏勭唨閺堢徆ction缁紮绱�
	//閻掕泛鎮楅幍锟介張澶屾畱Action缁鍏樼紒褎澹欓懛顏囩箹娑擃亜鐔�閺堢徆citon缁紮绱濋崷銊ョ唨閺堢徆ction缁鍣烽棃銏ｅ箯閸欐牜娴夋惔鏃傛畱request閵嗕购esponse閵嗕够ession鐎电钖勯敍锟�
	   @ModelAttribute
	   public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
	       this.request = request;
	       this.response = response;
	       this.session = request.getSession();
	   }
}
