package com.soulcoder.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * The type Index interceptor.
 */
public class IndexInterceptor extends HandlerInterceptorAdapter {
	 /**  
     * 閸︺劋绗熼崝鈥愁槱閻炲棗娅掓径鍕倞鐠囬攱鐪版稊瀣鐞氼偉鐨熼悽锟�  
     * 婵″倹鐏夋潻鏂挎礀false  
     *     娴犲骸缍嬮崜宥囨畱閹凤附鍩呴崳銊ョ窔閸ョ偞澧界悰灞惧閺堝瀚ら幋顏勬珤閻ㄥ垷fterCompletion(),閸愬秹锟斤拷閸戠儤瀚ら幋顏勬珤闁撅拷 
     * 婵″倹鐏夋潻鏂挎礀true  
     *    閹笛嗩攽娑撳绔存稉顏呭閹搭亜娅�,閻╂潙鍩岄幍锟介張澶屾畱閹凤附鍩呴崳銊╁厴閹笛嗩攽鐎瑰本鐦�  
     *    閸愬秵澧界悰宀冾潶閹凤附鍩呴惃鍑渙ntroller  
     *    閻掕泛鎮楁潻娑樺弳閹凤附鍩呴崳銊╂懠,  
     *    娴犲孩娓堕崥搴濈娑擃亝瀚ら幋顏勬珤瀵帮拷閸ョ偞澧界悰灞惧閺堝娈憄ostHandle()  
     *    閹恒儳娼冮崘宥勭矤閺堬拷閸氬簼绔存稉顏呭閹搭亜娅掑锟介崶鐐村⒔鐞涘本澧嶉張澶屾畱afterCompletion()  
     */   
    public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {
         
        System.out.println("preHandle(), 閸︺劏顔栭梻鐡筼ntroller娑斿澧犵悮顐ョ殶閻拷");  
        return true;
         
    }  
 
    /** 
     * 閸︺劋绗熼崝鈥愁槱閻炲棗娅掓径鍕倞鐠囬攱鐪伴幍褑顢戠�瑰本鍨氶崥锟�,閻㈢喐鍨氱憴鍡楁禈娑斿澧犻幍褑顢戦惃鍕З娴ｏ拷    
     * 閸欘垰婀猰odelAndView娑擃厼濮為崗銉︽殶閹诡噯绱濆В鏂款洤瑜版挸澧犻弮鍫曟？ 
     */ 
     
    public void postHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler,    
            ModelAndView modelAndView) throws Exception {  
        System.out.println("postHandle(), 閸︺劏顔栭梻鐡筼ntroller娑斿鎮楅敍宀冾問闂傤喛顫嬮崶鍙ョ閸撳秷顫︾拫鍐暏,鏉╂瑩鍣烽崣顖欎簰濞夈劌鍙嗘稉锟芥稉顏呮闂傛潙鍩宮odelAndView娑擃叏绱濋悽銊ょ艾閸氬海鐢荤憴鍡楁禈閺勫墽銇�");
        modelAndView.addObject("date","閻㈣鲸瀚ら幋顏勬珤閻㈢喐鍨氶惃鍕闂傦拷:" + new Date());
    }  
 
    /**  
     * 閸︹�昳spatcherServlet鐎瑰苯鍙忔径鍕倞鐎瑰矁顕Ч鍌氭倵鐞氼偉鐨熼悽锟�,閸欘垳鏁ゆ禍搴㈢閻炲棜绁┃鎰搼   
     *   
     * 瑜版挻婀侀幏锔藉焻閸ｃ劍濮忛崙鍝勭磽鐢憡妞�,娴兼矮绮犺ぐ鎾冲閹凤附鍩呴崳銊ョ窔閸ョ偞澧界悰灞惧閺堝娈戦幏锔藉焻閸ｃ劎娈慳fterCompletion()  
     */
     
    public void afterCompletion(HttpServletRequest request,    
            HttpServletResponse response, Object handler, Exception ex)  
    throws Exception {  
           
        System.out.println("afterCompletion(), 閸︺劏顔栭梻顔款潒閸ュ彞绠ｉ崥搴ゎ潶鐠嬪啰鏁�");  
    }  
}
