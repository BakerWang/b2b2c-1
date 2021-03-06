package com.enation.app.b2b2c.front.tag.store.activity;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.enation.app.b2b2c.core.member.model.StoreMember;
import com.enation.app.b2b2c.core.member.service.IStoreMemberManager;
import com.enation.app.b2b2c.core.store.service.activity.IStoreActivityGiftManager;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.database.Page;
import com.enation.framework.taglib.BaseFreeMarkerTag;

import freemarker.template.TemplateModelException;

/**
 * 获取店铺促销活动赠品分页列表Tag
 * @author DMRain
 * @date 2016年1月14日
 * @version v1.0
 * @since v1.0
 */
@Component
public class ActivityGiftListTag extends BaseFreeMarkerTag{
	@Autowired
	private IStoreActivityGiftManager storeActivityGiftManager;
	@Autowired
	private IStoreMemberManager storeMemberManager;
	
	@Override
	protected Object exec(Map params) throws TemplateModelException {
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		
		int pageSize = 10;
		Integer pageNo = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page").toString());
		String keyword = request.getParameter("keyword");
		StoreMember storeMember = this.storeMemberManager.getStoreMember();
		
		Map result = new HashMap();
		Page page = this.storeActivityGiftManager.list(keyword, storeMember.getStore_id(), pageNo, pageSize);
		
		result.put("giftList",page);
		result.put("totalCount", page.getTotalCount());
		result.put("page", pageNo);
		result.put("pageSize", pageSize);
		result.put("keyword", keyword);
		return result;
	}

}
