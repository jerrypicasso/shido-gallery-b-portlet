<%@ page pageEncoding="utf-8"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@page import="com.njshido.component.ShidoGalleryConfigurationAction" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<portlet:defineObjects />

<%
	PortletPreferences preferences = renderRequest.getPreferences();
	int columns = Integer.parseInt(preferences.getValue(ShidoGalleryConfigurationAction.SHIDO_GALLERY_COLUMNS, 
			ShidoGalleryConfigurationAction.SHIDO_GALLERY_COLUMNS_DEFAULT_VAL));
	String imgWidth = preferences.getValue(ShidoGalleryConfigurationAction.SHIDO_GALLERY_IMG_WIDTH, StringPool.BLANK);
	String imgHeight = preferences.getValue(ShidoGalleryConfigurationAction.SHIDO_GALLERY_IMG_HEIGHT, StringPool.BLANK);
	float spacing = Float.parseFloat(preferences.getValue(ShidoGalleryConfigurationAction.SHIDO_GALLERY_SPACING, 
			ShidoGalleryConfigurationAction.SHIDO_GALLERY_SPACING_DEFAULT_VAL));
	
	String[] imgUrls = preferences.getValues(ShidoGalleryConfigurationAction.SHIDO_GALLERY_CELL_IMG_URL, StringPool.EMPTY_ARRAY);
	String[] links = preferences.getValues(ShidoGalleryConfigurationAction.SHIDO_GALLERY_CELL_LINK, StringPool.EMPTY_ARRAY);
	String[] titles = preferences.getValues(ShidoGalleryConfigurationAction.SHIDO_GALLERY_CELL_TITLE, StringPool.EMPTY_ARRAY);
	String[] summaries = preferences.getValues(ShidoGalleryConfigurationAction.SHIDO_GALLERY_CELL_SUMMARY, StringPool.EMPTY_ARRAY);
%>

<div id="<portlet:namespace/>shido-gallery-b" class="shido-gallery-b">
<%
	int rows = imgUrls.length/columns + (imgUrls.length%columns == 0 ? 0 : 1);
	for(int i = 0; i < rows; i++) {
%>
		<div class="row">
<%
		float width = 100f/columns;
		for(int j = columns*i; j < columns*i + columns; j++) {
%>
			<div class="column" style="width:<%= width%>%;">
				<div style="border-width:<%= spacing%>px;position:relative;">
					<a target="_blank" target="_blank"
<%
			if(j < links.length && links[j] != null && links[j].length() > 0) {
%>
				href="<%= links[j]%>"
<%
			}
%>
					>
						<br/>
<%
			if(j < titles.length && titles[j] != null && titles[j].length() > 0) {
%>
						<span class="img-title"><%= titles[j].replaceAll("\\n", "<br/>")%></span>
<%
			}
			if(j < summaries.length && summaries[j] != null && summaries[j].length() > 0) {
%>
						<span class="img-summary"><%= summaries[j]%></span>
<%
			}
			if(j < imgUrls.length && imgUrls[j] != null && imgUrls[j].length() > 0) {
%>
						<span style="height: <%=imgHeight%>px;
									background-image: url(<%= imgUrls[j]%>);
									background-position: center center;
									background-size: <%=imgWidth%>px <%=imgHeight%>px;
									background-repeat: no-repeat;
									display: block;">
						</span>
						<span class="mask"></span>
<%
			}
%>

					</a>
				</div>
			</div>
<%
 		}
%>
		</div>
<%		
	}
%>
</div>
