package cn.shirdon.liao.action.user_profile_info;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.shirdon.liao.constant.ErrorMsg;
import cn.shirdon.liao.exception.QingException;
import cn.shirdon.liao.service.qiniu_info.QiniuInfoService;
import cn.shirdon.liao.service.template_info.TemplateInfoService;
import cn.shirdon.liao.service.user_info.UserInfoService;
import cn.shirdon.liao.service.user_profile_info.UserProfileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shirdon.liao.constant.ErrorMsg;
import cn.shirdon.liao.exception.QingException;
import cn.shirdon.liao.service.qiniu_info.QiniuInfoService;
import cn.shirdon.liao.service.template_info.TemplateInfoService;
import cn.shirdon.liao.service.user_info.UserInfoService;
import cn.shirdon.liao.service.user_profile_info.UserProfileInfoService;
import cn.shirdon.liao.view.QiniuInfo;
import cn.shirdon.liao.view.Result;
import cn.shirdon.liao.view.UserInfo;
import cn.shirdon.liao.view.UserProfileInfo;

@Controller
@RequestMapping("portal/user_profile_info")
public class UserProfileInfoAction {

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserProfileInfoService userProfileInfoService;
	@Autowired
	private QiniuInfoService qiniuInfoService;
	@Autowired
	private TemplateInfoService templateInfoService;
	
	/**
	 * 跳转修改头像页面
	 * @return
	 */
	@RequestMapping("change_avatar.action")
	public String changeAvatar() {
		// 获取所选模板
		String templatePC = templateInfoService.selectNameByType("pc");
				
		return "portal/pc/template/" + templatePC + "/user/personal/avatar";
	}
	
	/**
	 * 保存用户头像
	 * @param request
	 * @param avatar
	 * @param userToken
	 * @return
	 * @throws IOException
	 * @throws QingException
	 */
	@RequestMapping("save_avatar.json")
	@ResponseBody
	public Result saveAvatar(
			HttpServletRequest request,
			@RequestParam(value="avatar") String avatar,
			@RequestParam(value="userToken") String userToken) throws IOException, QingException {
		
		if (StringUtils.isEmpty(userToken)) {
			throw new QingException(ErrorMsg.ERROR_100012);
		}
		
		// 判断session
		HttpSession session  = request.getSession();
		// 从session中取出用户身份信息
		UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
		
		if (userInfo==null) {
			userInfo = userInfoService.getUserInfoByUserToken(userToken);
			// 将用户信息保存进session
			request.getSession().setAttribute("userInfo", userInfo);
		}
		
		// 将base64头像上传到七牛云
		QiniuInfo qiniuInfo = qiniuInfoService.selectByType("touxiang");
		avatar = qiniuInfoService.uploadAvatar(avatar, qiniuInfo);
		System.out.println(avatar);
		
		// 保存用户头像
		UserProfileInfo userProfileInfo = new UserProfileInfo();
		userProfileInfo.setAvatar(avatar);
		userProfileInfo.setUserId(userInfo.getId());
		
		userProfileInfoService.save(userProfileInfo);
		
		// 重新设置session
		userInfo = userInfoService.getUserInfoByUserToken(userToken);
		request.getSession().setAttribute("userInfo", userInfo);
		
		return Result.success();
	}
}
