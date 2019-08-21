package com.example.demo.controller;

import com.example.demo.dao.DBManger;
import com.example.demo.service.AssosiationCRUD;
import com.example.demo.service.MemberCRUD;
import com.example.demo.util.EAuthorizer;
import com.example.demo.util.UnifyCRUDUtil;
import com.example.demo.util.UnifyCRUDUtilV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class Home {

	private final String _PARAMETER_CODE_ = "code";
	private UnifyCRUDUtil unifyCRUDUtil;
	private UnifyCRUDUtilV2 unifyCRUDUtilV2;

	@Autowired
	public Home(UnifyCRUDUtil unifyCRUDUtil,UnifyCRUDUtilV2 unifyCRUDUtilV2) {
		this.unifyCRUDUtil = unifyCRUDUtil;
		this.unifyCRUDUtilV2 = unifyCRUDUtilV2;
	}

	@GetMapping("/hi")
	public String hi() {
		return "hi,dude.";
	}


	/**
	 * @Description 判断是易班回调还是登陆的授权认证，做对应返回
	 * @Author jun
	 * @Param request, response
	 */
	@GetMapping("/yiban")
	public String yiban(HttpServletRequest request, HttpServletResponse response,HttpServletResponse resp) {
		//写个.bat占用8888端口

		//判断是否有code参数
		String code = request.getParameter(_PARAMETER_CODE_);
		if (code == null || code.isEmpty()) {
			//若初次授权
			EAuthorizer.parseCallback(response);
			return "Redirect!See it means Error!";
		} else {
			//若回调
			String callback = EAuthorizer.parseCallback(code,resp);
			return callback;
			//将回调参数保存准备交付前端
			//即重定向到主页并带上参数
		}

	}

	/**
	 * @Description: 获取社团信息
	 * 请求格式：{"operation":"0123(对应crud)"，"operation_table":"member","id(易班id/系统id)":"1","ts":"currentTimeMillies()","sign":"加密串","data":"json"}
	 * 注意回复格式：{"status":"1","data":[{一条}]}   前端同学 遍历一下json把，为方便data永远是数组,数组中装着一个jsonObject
	 * @Param: [req]
	 * @Return: java.lang.String
	 * @Date: 2019/4/20 5:40
	 */
	@GetMapping("/assosiation")
	public String Assosiation(HttpServletRequest req) {
		long bef = System.currentTimeMillis();
		//目前先不加密测试
		try {
			String res = unifyCRUDUtil.unify(req.getParameter("operation_table"),req.getParameter("operation"), req.getParameter("data"));
			System.out.println("cost:"+(System.currentTimeMillis()-bef));
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}

	/**
	 * @Description: 因为用的replace语句update之后id会变，前端注意更新数据
	 * @Param: [req]
	 * @Return: java.lang.String
	 * @Date: 2019/4/21 21:27
	 */
	@GetMapping("/member")
	public String Member(HttpServletRequest req) {
		//目前不加密测试
		try {
			return unifyCRUDUtil.unify(req.getParameter("operation_table"), req.getParameter("operation"), req.getParameter("data"));
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}


	@GetMapping("/v2")
	public String V2(HttpServletRequest req){
		try {
			return unifyCRUDUtilV2.unify(req.getParameter("operation_table"), req.getParameter("operation"), req.getParameter("data"));
		} catch (Exception e) {
			e.printStackTrace();
			return "Illegal operation!";
		}
	}

	@GetMapping("/v3")
	public String V3(HttpServletRequest req){
		try {
			String tmp = req.getParameter("data");
			tmp = tmp.replace("@","{");
			tmp = tmp.replace("*","}");
			System.out.println("tmpSTR======"+tmp);
			return unifyCRUDUtilV2.unify(req.getParameter("operation_table"), req.getParameter("operation"), tmp);
		} catch (Exception e) {
			e.printStackTrace();
			return "[{\"status\":400,\"data\":\"Illegal operation!\"}]";
		}
	}
}