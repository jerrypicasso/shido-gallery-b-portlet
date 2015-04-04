package com.njshido.component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

public class ShidoGalleryConfigurationAction extends DefaultConfigurationAction {
	
	public static final String SHIDO_GALLERY_COLUMNS = "SHIDO_GALLERY_COLUMNS";
	public static final String SHIDO_GALLERY_IMG_WIDTH = "SHIDO_GALLERY_IMG_WIDTH";
	public static final String SHIDO_GALLERY_IMG_HEIGHT = "SHIDO_GALLERY_IMG_HEIGHT";
	public static final String SHIDO_GALLERY_SPACING = "SHIDO_GALLERY_SPACING";
	
	public static final String SHIDO_GALLERY_CELL_IMG_URL = "SHIDO_GALLERY_CELL_IMG_URL";
	public static final String SHIDO_GALLERY_CELL_LINK = "SHIDO_GALLERY_CELL_LINK";
	public static final String SHIDO_GALLERY_CELL_TITLE = "SHIDO_GALLERY_CELL_TITLE";
	public static final String SHIDO_GALLERY_CELL_SUMMARY = "SHIDO_GALLERY_CELL_SUMMARY";
	//public static final String SHIDO_GALLERY_CELL_TAG = "SHIDO_GALLERY_CELL_TAG";
	
	public static final String SHIDO_GALLERY_SPACING_DEFAULT_VAL = "10";
	
	private static final String CONFIG_JSP = "/WEB-INF/config/config.jsp";
	private static final String SUCCESS = "success";
	
	public static final String SHIDO_GALLERY_COLUMNS_DEFAULT_VAL = "3";
	
	@Override
	public String render(PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		String portletId = renderRequest.getParameter("portletResource");
		PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(renderRequest, portletId);
		renderRequest.setAttribute(SHIDO_GALLERY_COLUMNS, preferences.getValue(SHIDO_GALLERY_COLUMNS, StringPool.BLANK));
		renderRequest.setAttribute(SHIDO_GALLERY_IMG_WIDTH, preferences.getValue(SHIDO_GALLERY_IMG_WIDTH, StringPool.BLANK));
		renderRequest.setAttribute(SHIDO_GALLERY_IMG_HEIGHT, preferences.getValue(SHIDO_GALLERY_IMG_HEIGHT, StringPool.BLANK));
		renderRequest.setAttribute(SHIDO_GALLERY_SPACING, preferences.getValue(SHIDO_GALLERY_SPACING, StringPool.BLANK));
		renderRequest.setAttribute(SHIDO_GALLERY_CELL_IMG_URL, preferences.getValues(SHIDO_GALLERY_CELL_IMG_URL, StringPool.EMPTY_ARRAY));
		renderRequest.setAttribute(SHIDO_GALLERY_CELL_LINK, preferences.getValues(SHIDO_GALLERY_CELL_LINK, StringPool.EMPTY_ARRAY));
		renderRequest.setAttribute(SHIDO_GALLERY_CELL_TITLE, preferences.getValues(SHIDO_GALLERY_CELL_TITLE, StringPool.EMPTY_ARRAY));
		renderRequest.setAttribute(SHIDO_GALLERY_CELL_SUMMARY, preferences.getValues(SHIDO_GALLERY_CELL_SUMMARY, StringPool.EMPTY_ARRAY));
		//renderRequest.setAttribute(SHIDO_GALLERY_CELL_TAG, preferences.getValues(SHIDO_GALLERY_CELL_TAG, StringPool.EMPTY_ARRAY));
		return CONFIG_JSP;
	}
	
	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		String portletResource = ParamUtil.getString(actionRequest, "portletResource");
		PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
		if (Validator.isNotNull(preferences)) {
			String columns = ParamUtil.getString(actionRequest, SHIDO_GALLERY_COLUMNS);
			String imgWidth = ParamUtil.getString(actionRequest, SHIDO_GALLERY_IMG_WIDTH);
			String imgHeight = ParamUtil.getString(actionRequest, SHIDO_GALLERY_IMG_HEIGHT);
			String spacing = ParamUtil.getString(actionRequest, SHIDO_GALLERY_SPACING);
			String[] imgUrls = ParamUtil.getParameterValues(actionRequest, SHIDO_GALLERY_CELL_IMG_URL);
			String[] links = ParamUtil.getParameterValues(actionRequest, SHIDO_GALLERY_CELL_LINK);
			String[] titles = ParamUtil.getParameterValues(actionRequest, SHIDO_GALLERY_CELL_TITLE);
			String[] summaries = ParamUtil.getParameterValues(actionRequest, SHIDO_GALLERY_CELL_SUMMARY);
			//String[] tags = ParamUtil.getParameterValues(actionRequest, SHIDO_GALLERY_CELL_TAG);
			
			preferences.setValue(SHIDO_GALLERY_COLUMNS, columns);
			preferences.setValue(SHIDO_GALLERY_IMG_WIDTH, imgWidth);
			preferences.setValue(SHIDO_GALLERY_IMG_HEIGHT, imgHeight);
			preferences.setValue(SHIDO_GALLERY_SPACING, spacing);
			preferences.setValues(SHIDO_GALLERY_CELL_IMG_URL, imgUrls);
			preferences.setValues(SHIDO_GALLERY_CELL_LINK, links);
			preferences.setValues(SHIDO_GALLERY_CELL_TITLE, titles);
			preferences.setValues(SHIDO_GALLERY_CELL_SUMMARY, summaries);
			//preferences.setValues(SHIDO_GALLERY_CELL_TAG, tags);
			preferences.store();
			
			SessionMessages.add(actionRequest, SUCCESS);
		}
		super.processAction(portletConfig, actionRequest, actionResponse);
	}
}
