/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package oracle.apps.xxon.om.gbw.audit.webui;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.nav.OAButtonBean;
import oracle.apps.xxon.om.gbw.audit.server.XXONGbwAuditHeaderVORowImpl;
import oracle.apps.xxon.om.gbw.util.XXONGbwUtil;

import oracle.jbo.domain.Number;


/**
 * Controller for ...
 */
public class XXONGbwOmAuditCO extends OAControllerImpl
{
  public static final String RCS_ID="$Header$";
  public static final boolean RCS_ID_RECORDED =
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");
    OAApplicationModule am=null;
  /**
   * Layout and page setup logic for a region.
   * @param pageContext the current OA page context
   * @param webBean the web bean corresponding to the region
   */
  public void processRequest(OAPageContext pageContext, OAWebBean webBean)
  {
    super.processRequest(pageContext, webBean);
      am = pageContext.getApplicationModule(webBean);
      am.invokeMethod("initSearchValVO");
      XXONGbwUtil utils= new XXONGbwUtil();
      utils.recordsTobeDisplayed(pageContext,webBean,"auditResultAdvRN");
      clearOrderAuditValues(pageContext,webBean);
      clearSchCtrlAuditValues(pageContext,webBean);
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
      String actionEvent=pageContext.getParameter(EVENT_PARAM); 
      String lovInputSourceId=pageContext.getLovInputSourceId();
      String rowReference =  pageContext.getParameter(OAWebBeanConstants.EVENT_SOURCE_ROW_REFERENCE);
      log("lovInputSourceId::"+lovInputSourceId); 
      log("rowReference-->"+rowReference);
      log("actionEvent-->"+actionEvent);
     // XXONGbwUtil utils= new XXONGbwUtil();
   if (actionEvent.equalsIgnoreCase("searchSo")) 
    {
        Serializable params[]={"auditSo"};
        am.invokeMethod("executeAuditHeader",params);
    }else if (actionEvent.equalsIgnoreCase("searchSc")) 
    {
        Serializable params[]={"auditSc"};
        am.invokeMethod("executeAuditHeader",params);
    }else if(actionEvent.equalsIgnoreCase("cancel")) 
      {
          am.clearVOCaches(null, true);        
          pageContext.forwardImmediately(
                  "OA.jsp?OAFunc=OAHOMEPAGE",
                  null,
                  OAWebBeanConstants.KEEP_MENU_CONTEXT,
                  null,
                  null,
                  false, // Retain AM 
                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO);                     
      }else if(actionEvent.equalsIgnoreCase("lineNumFilter"))
      {
          String lineNum = pageContext.getParameter("lineNumber");
          if(lineNum!=null)
          {
            Serializable params[]={lineNum};
            am.invokeMethod("exeAuditDetlByLineNum",params);
          }          
      }else if("showAuditDetails".equals(actionEvent))
      { 
          String orderNumber=null;
          String headerId=null;
          String auditLevel=null;
          XXONGbwAuditHeaderVORowImpl   row=(XXONGbwAuditHeaderVORowImpl)am.findRowByRef(rowReference);                 
          auditLevel=row.getAuditLevel();
          if(auditLevel!=null&&(auditLevel.equalsIgnoreCase("Staged Header")||auditLevel.equalsIgnoreCase("Scheduling Control")))
          {
              Number headerID=row.getHeaderId();
              headerId=String.valueOf(headerID);
          }else{
              orderNumber=row.getOrderNumber();  
              Number headerID=row.getHeaderId();
              headerId=String.valueOf(headerID);
          }
          log("orderNumber-->"+orderNumber);
          log("headerId-->"+headerId);
          log("auditLevel-->"+auditLevel);
          Serializable params[]={orderNumber,headerId,null,auditLevel};
          am.invokeMethod("executeAuditDetails",params);
      }else if("exportHeader".equals(actionEvent))
      {   
          try{
              String columnName[]    = new String[7];
              String viewAttribute[] = new String[7];
                 
                  columnName[0] = "Order Number";       
                  columnName[1] = "Line Number";
                  columnName[2] = "Corp Code";
                  columnName[3] = "Cust5 Code";
                  columnName[4] = "Level";
                  columnName[5] = "PAL";
                  columnName[6] = "Pkg Group Code";
                  
                  viewAttribute[0] = "OrderNumber";
                  viewAttribute[1] = "OrdLineNumber";
                  viewAttribute[2] = "CorpCode";
                  viewAttribute[3] = "Cust5Code";
                  viewAttribute[4] = "AuditLevel";
                  viewAttribute[5] = "Pal";
                  viewAttribute[6] = "PackageCd";
                 
                /* Call the method to export data */
                 XXONGbwUtil csvFile = new XXONGbwUtil();
                 csvFile.writeXLSXFile(pageContext, 
                                        "Audit_Trail_Header_Data",      // file name as you wish but without extension
                                         "XXONGbwAuditHeaderVO1",      // view instance name
                                         columnName,               // all Column names array
                                         viewAttribute            // viewAttribute Name
                                         );
                                         
          }catch(OAException exception){
                  throw exception;
              }catch(Exception exception){
                  throw new OAException("Error while exporting the data to excel -->"+exception.getMessage(),OAException.ERROR);
              }       
        } else if("export".equals(actionEvent))
      {   
          try{
              String columnName[]    = new String[10];
              String viewAttribute[] = new String[10];
                 
                  columnName[0] = "User Name";
                  columnName[1] = "History Date";
                  columnName[2] = "Level";
                  columnName[3] = "Order Number";                        
                  columnName[4] = "Line Number";
                  columnName[5] = "OPN";
                  columnName[6] = "Attribute Name";
                  columnName[7] = "New Value";
                  columnName[8] = "Old Value";
                  columnName[9] = "Responsibility Name";
              
                  
                  viewAttribute[0] = "AuditUserName";
                  viewAttribute[1] = "AuditTimestamp";
                  viewAttribute[2] = "AuditLevel";
                  viewAttribute[3] = "OrderNumber";                        
                  viewAttribute[4] = "LineNumber";
                  viewAttribute[5] = "ItemName";
                  viewAttribute[6] = "AttributeName";
                  viewAttribute[7] = "NewAttributeValue";
                  viewAttribute[8] = "OldAttributeValue";
                  viewAttribute[9] = "ResponsibilityName";
              
              
                /* Call the method to export data */
                 XXONGbwUtil csvFile = new XXONGbwUtil();
                 csvFile.writeXLSXFile(pageContext, 
                                        "Audit_Trail_data",      // file name as you wish but without extension
                                         "XXONGbwAuditResultVO1",      // view instance name
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
    private void clearOrderAuditValues(OAPageContext pageContext,OAWebBean webBean)
        {
          
              StringBuffer jsString = new StringBuffer();
                jsString.append( "function clearOrderAuditFunction()");
                jsString.append("{");                
                jsString.append("if(document.getElementById('DateRangeItem').value!==null)document.getElementById('DateRangeItem').selectedIndex=0;");
                jsString.append("if(document.getElementById('UserId').value!==null)document.getElementById('UserId').value='';");
                jsString.append("if(document.getElementById('DateFrom').value!==null)document.getElementById('DateFrom').value='';");
                jsString.append("if(document.getElementById('DateTo').value!==null)document.getElementById('DateTo').value='';"); 
                jsString.append("if(document.getElementById('itemNameLov').value!==null)document.getElementById('itemNameLov').value='';");
                jsString.append("if(document.getElementById('OrderNumber').value!==null)document.getElementById('OrderNumber').value='';");
                jsString.append("if(document.getElementById('AccountCORP').value!==null)document.getElementById('AccountCORP').value='';");
                jsString.append("if(document.getElementById('Site').value!==null)document.getElementById('Site').value='';");           
                jsString.append("if(document.getElementById('DemandType').value!==null)document.getElementById('DemandType').value='';" );
                jsString.append("}" );
                pageContext.putJavaScriptFunction("clearOrderAuditFunction",jsString.toString());
                OAButtonBean clearButton = (OAButtonBean) webBean.findChildRecursive("ClearSo") ;
                String javaSClear = "javascript:clearOrderAuditFunction();";
                clearButton.setOnClick(javaSClear);
         
      }
    private void clearSchCtrlAuditValues(OAPageContext pageContext,OAWebBean webBean)
        {
          
              StringBuffer jsString = new StringBuffer();
                jsString.append( "function clearFunction()");
                jsString.append("{");                
                jsString.append("if(document.getElementById('DateFrom').value!==null)document.getElementById('DateFrom').value='';");
                jsString.append("if(document.getElementById('DateTo').value!==null)document.getElementById('DateTo').value='';"); 
                jsString.append("if(document.getElementById('itemLov').value!==null)document.getElementById('itemLov').value='';");                
                jsString.append("if(document.getElementById('PTIInput').value!==null)document.getElementById('PTIInput').value='';");                
                jsString.append("if(document.getElementById('Pkg').value!==null)document.getElementById('Pkg').value='';");           
                jsString.append("if(document.getElementById('UserId').value!==null)document.getElementById('UserId').value='';");
                jsString.append("if(document.getElementById('DateRangeItem').value!==null)document.getElementById('DateRangeItem').selectedIndex=0;");               
                jsString.append("}" );
                pageContext.putJavaScriptFunction("clearFunction",jsString.toString());
                OAButtonBean clearButton = (OAButtonBean) webBean.findChildRecursive("ClearSc") ;
                String javaSClear = "javascript:clearFunction();";
                clearButton.setOnClick(javaSClear);
         
      }

    
}
