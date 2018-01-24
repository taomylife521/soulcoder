package com.soulcoder.partener.gt;

import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


/**
 * 娴ｈ法鏁ost閺傜懓绱￠敍宀冪箲閸ョ偤鐛欑拠浣虹波閺嬶拷, request鐞涖劌宕熸稉顓炵箑妞よ瀵橀崥鐜瀐allenge, validate, seccode
 */
public class VerifyLoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(), 
				GeetestConfig.isnewfailback());
		String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
		String validate = request.getParameter(GeetestLib.fn_geetest_validate);
		String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);
		
		//娴犲窏ession娑擃叀骞忛崣鏉噒-server閻樿埖锟斤拷
		int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
		
		//娴犲窏ession娑擃叀骞忛崣鏉erid
		String userid = (String)request.getSession().getAttribute("userid");
		
		//閼奉亜鐣炬稊澶婂棘閺侊拷,閸欘垶锟藉瀚ㄥǎ璇插
		HashMap<String, String> param = new HashMap<String, String>(); 
		param.put("user_id", userid); //缂冩垹鐝悽銊﹀煕id
		param.put("client_type", "web"); //web:閻絻鍓虫稉濠勬畱濞村繗顫嶉崳顭掔幢h5:閹靛婧�娑撳﹦娈戝ù蹇氼潔閸ｎ煉绱濋崠鍛缁夎濮╂惔鏃傛暏閸愬懎鐣崗銊ュ敶缂冾喚娈憌eb_view閿涙捕ative閿涙岸锟芥俺绻冮崢鐔烘晸SDK濡炲秴鍙咥PP鎼存梻鏁ら惃鍕煙瀵拷
		param.put("ip_address", "127.0.0.1"); //娴肩姾绶悽銊﹀煕鐠囬攱鐪版宀冪槈閺冭埖澧嶉幖鍝勭敨閻ㄥ嚘P
		
		int gtResult = 0;

		if (gt_server_status_code == 1) {
			//gt-server濮濓絽鐖堕敍灞芥倻gt-server鏉╂稖顢戞禍灞绢偧妤犲矁鐦�
				
			gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
			System.out.println(gtResult);
		} else {
			// gt-server闂堢偞顒滅敮鍛婂剰閸愬吀绗呴敍宀冪箻鐞涘畺ailback濡�崇础妤犲矁鐦�
				
			System.out.println("failback:use your own server captcha validate");
			gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
			System.out.println(gtResult);
		}


		if (gtResult==1) {
			// 妤犲矁鐦夐幋鎰
			
			request.getSession().setAttribute("isValidatePass", true);
			PrintWriter out = response.getWriter();
			JSONObject data = new JSONObject();
			try {
				data.put("status", "success");
				data.put("version", gtSdk.getVersionInfo());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.println(data.toString());
		}
		else {
			request.getSession().setAttribute("isValidatePass", false);
			// 妤犲矁鐦夋径杈Е
			JSONObject data = new JSONObject();
			try {
				data.put("status", "fail");
				data.put("version", gtSdk.getVersionInfo());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.println(data.toString());
		}

	}
}
