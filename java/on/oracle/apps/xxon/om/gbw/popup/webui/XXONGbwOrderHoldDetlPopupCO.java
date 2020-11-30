/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package oracle.apps.xxon.om.gbw.popup.webui;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.xxon.om.gbw.util.XXONGbwUtil;

/**
 * Controller for ...
 */
public class XXONGbwOrderHoldDetlPopupCO extends OAControllerImpl
{
  public static final String RCS_ID="$Header$";
  public static final boolean RCS_ID_RECORDED =
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

  /**
   * Layout and page setup logic for a region.
   * @param pageContext the current OA page context
   * @param webBean the web bean corresponding to the region
   */
  public void processRequest(OAPageContext pageContext, OAWebBean webBean)
  {
    super.processRequest(pageContext, webBean);
      OAApplicationModule am = pageContext.getApplicationModule(webBean);
      String HeaderId=pageContext.getParameter("pHeaderId");
      String LineId=pageContext.getParameter("pLineId");
      log("HeaderId-->"+HeaderId,pageContext);
      log("LineId-->"+LineId,pageContext);
      Serializable parms[]={HeaderId,LineId};
      am.invokeMethod("initHoldVO",parms);
  }

  /**
   * Procedure to handle form submissions for form elements in
   * a region.
   * @param pageContext the current OA page context
   * @param webBean the web bean corresponding to the region
   */
  public void processFormRequest(OAPageContext pageContext, OAWebBean webBean)
  {
    super.processFormRequest(pageContext, webBean);
  }
    /**
     * Below Method is to print the log messages
     *@param Message for text 
    */
    private void log(String Message,OAPageContext pageContext)
    {
        XXONGbwUtil utils= new XXONGbwUtil();
        utils.Log(Message,pageContext);      
    }
}
