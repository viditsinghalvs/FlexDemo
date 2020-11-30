/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package oracle.apps.xxon.om.gbw.invres.webui;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.nav.OAButtonBean;
import oracle.apps.xxon.om.gbw.util.XXONGbwUtil;


/**
 * Controller for ...
 */
public class XXONGbwIrSearchCO extends OAControllerImpl
{
  public static final String RCS_ID="$Header$";
  public static final boolean RCS_ID_RECORDED =
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");
    OAApplicationModule am =null;

  /**
   * Layout and page setup logic for a region.
   * @param pageContext the current OA page context
   * @param webBean the web bean corresponding to the region
   */
  public void processRequest(OAPageContext pageContext, OAWebBean webBean)
  {
    super.processRequest(pageContext, webBean);
      am = pageContext.getApplicationModule(webBean);
      am = pageContext.getApplicationModule(webBean);
      am.invokeMethod("recDisInit");
      am.invokeMethod("initSearchValuesVO");
      XXONGbwUtil utils= new XXONGbwUtil();
      utils.recordsTobeDisplayed(pageContext,webBean,"ResultAdvTabRN");
      clearValues(pageContext,webBean);
    
    
      
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
      am = pageContext.getApplicationModule(webBean);
      String actionEvents=pageContext.getParameter(EVENT_PARAM);
      String lovInputSourceId=pageContext.getLovInputSourceId();
      String rowReference =  pageContext.getParameter(OAWebBeanConstants.EVENT_SOURCE_ROW_REFERENCE);
      log("actionEvents::"+actionEvents);
      log("lovInputSourceId::"+lovInputSourceId); 
      log("rowReference-->"+rowReference);
      if(actionEvents.equalsIgnoreCase("Go"))
      {
        am.invokeMethod("exeResult");
      }else if(actionEvents.equalsIgnoreCase("rowsField"))
      {
          String rowCount = pageContext.getParameter("rowsField");
          if(rowCount!=null)
          {
            pageContext.putSessionValue("RecordsDisplayed",rowCount);
          }
          XXONGbwUtil utils= new XXONGbwUtil();
          utils.recordsTobeDisplayed(pageContext,webBean,"ResultAdvTabRN");
      }else if("export".equals(actionEvents))
      {   
      try{
          String columnName[]    = new String[11];
          String viewAttribute[] = new String[11];
              
              columnName[0] = "Source";
              columnName[1] = "Cust5 Code";
              columnName[2] = "OPN";
              columnName[3] = "PAL";
              columnName[4] = "Pkg Group Code";                        
              columnName[5] = "Corp Code";              
              columnName[6] = "RSD";
              columnName[7] = "SSD";
              columnName[8] = "Quantity";             
              columnName[9] = "Creation Date";
              columnName[10] = "Last Update Date";
              
              viewAttribute[0] = "SourceCode";
              viewAttribute[1] = "ReservtnCustCd";
              viewAttribute[2] = "Mpn";
              viewAttribute[3] = "Pti4";
              viewAttribute[4] = "PackageStdCd";                        
              viewAttribute[5] = "CorpCd";              
              viewAttribute[6] = "RequestDate";
              viewAttribute[7] = "ScheduleShipDate";
              viewAttribute[8] = "BalanceQuantity";              
              viewAttribute[9] = "CreationDate";
              viewAttribute[10] = "LastUpdateDate";
          
            /* Call the method to export data */
             XXONGbwUtil csvFile = new XXONGbwUtil();
             csvFile.writeXLSXFile(pageContext, 
                                    "Disaggregate_IR_View",      // file name as you wish but without extension
                                     "XXONGbwIrSearchVO1",      // view instance name
                                     columnName,               // all Column names array
                                     viewAttribute            // viewAttribute Name
                                     );
                                     
      }catch(OAException exception){
              throw exception;
          }catch(Exception exception){
              throw new OAException("Error while exporting the data to excel -->"+exception.getMessage(),OAException.ERROR);
          }
        } 
  }
    /**
     * Below Method is to print the log messages
     *@param Message for text 
    */
    private void log(String Message)
    {
        XXONGbwUtil utils= new XXONGbwUtil();
        utils.log(Message,am.getOADBTransaction());      
    }
    private void clearValues(OAPageContext pageContext,OAWebBean webBean)
        {
              StringBuffer jsString = new StringBuffer();
                jsString.append( "function clearFunction()");
                jsString.append("{");
                jsString.append("if(document.getElementById('OPNInput').value!==null)document.getElementById('OPNInput').value='';" );
                jsString.append("if(document.getElementById('PackageInput').value!==null)document.getElementById('PackageInput').value='';") ;
                jsString.append("if(document.getElementById('CustCodeInput').value!==null)document.getElementById('CustCodeInput').value='';");
                jsString.append("if(document.getElementById('PALInput').value!==null)document.getElementById('PALInput').value='';") ;
                jsString.append("if(document.getElementById('Code5Input').value!==null)document.getElementById('Code5Input').value='';") ;
                jsString.append("if(document.getElementById('souceInput').value!==null)document.getElementById('souceInput').value='';") ;
                jsString.append("}" );
               log("jsString-->"+jsString.toString());
                pageContext.putJavaScriptFunction("clearFunction",jsString.toString());
                OAButtonBean clearButton2 = (OAButtonBean) webBean.findChildRecursive("clearBtn") ;
                String javaSClear = "javascript:clearFunction();";
                clearButton2.setOnClick(javaSClear);
      }
      
     
}
