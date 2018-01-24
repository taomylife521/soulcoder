package com.soulcoder.partener.gt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


/**
 * 娴ｈ法鏁et閻ㄥ嫭鏌熷蹇氱箲閸ョ�媓allenge閸滃畱apthca_id,濮濄倖鏌熷蹇庝簰鐎圭偟骞囬崜宥呮倵缁旑垰鐣崗銊ュ瀻缁傝崵娈戝锟介崣鎴災佸锟�
 */
public class StartCaptchaServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(), 
				GeetestConfig.isnewfailback());

		String resStr = "{}";
		
		//閼奉亜鐣炬稊濉絪erid
		String userid = "test";
		
		//閼奉亜鐣炬稊澶婂棘閺侊拷,閸欘垶锟藉瀚ㄥǎ璇插
		HashMap<String, String> param = new HashMap<String, String>(); 
		param.put("user_id", userid); //缂冩垹鐝悽銊﹀煕id
		param.put("client_type", "web"); //web:閻絻鍓虫稉濠勬畱濞村繗顫嶉崳顭掔幢h5:閹靛婧�娑撳﹦娈戝ù蹇氼潔閸ｎ煉绱濋崠鍛缁夎濮╂惔鏃傛暏閸愬懎鐣崗銊ュ敶缂冾喚娈憌eb_view閿涙捕ative閿涙岸锟芥俺绻冮崢鐔烘晸SDK濡炲秴鍙咥PP鎼存梻鏁ら惃鍕煙瀵拷
		param.put("ip_address", "127.0.0.1"); //娴肩姾绶悽銊﹀煕鐠囬攱鐪版宀冪槈閺冭埖澧嶉幖鍝勭敨閻ㄥ嚘P

		//鏉╂稖顢戞宀冪槈妫板嫬顦╅悶锟�
		int gtServerStatus = gtSdk.preProcess(param);
		
		//鐏忓棙婀囬崝鈥虫珤閻樿埖锟戒浇顔曠純顔煎煂session娑擄拷
		request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
		//鐏忓敇serid鐠佸墽鐤嗛崚鐨奺ssion娑擄拷
		request.getSession().setAttribute("userid", userid);
		
		resStr = gtSdk.getResponseStr();

		PrintWriter out = response.getWriter();
		out.println(resStr);

	}
}
