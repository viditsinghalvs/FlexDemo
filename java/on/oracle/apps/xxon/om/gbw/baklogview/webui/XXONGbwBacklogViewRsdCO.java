/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 | Developed by Kishore Marripudi                                            |
 +===========================================================================*/
package oracle.apps.xxon.om.gbw.baklogview.webui;

import com.sun.java.util.collections.HashMap;

import java.io.Serializable;

import java.util.Hashtable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OABoundValueEnterOnKeyPress;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OAFormValueBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageCheckBoxBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageDateFieldBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.nav.OAButtonBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;
import oracle.apps.fnd.framework.webui.beans.table.OAColumnBean;
import oracle.apps.fnd.framework.webui.beans.table.OAMultipleSelectionBean;
import oracle.apps.xxon.om.gbw.baklogview.server.XXONGbwSearchCriteriaValuesVORowImpl;
import oracle.apps.xxon.om.gbw.util.XXONGbwUtil;


/**
 * Controller for ...
 */
 
public class XXONGbwBacklogViewRsdCO extends OAControllerImpl
{
  public static final String RCS_ID="$Header$";
  public static final boolean RCS_ID_RECORDED =
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");
    HashMap hmap= new HashMap();
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
     // new change
     OAPageLayoutBean page = pageContext.getPageLayoutBean();
          Hashtable applyParams = new Hashtable();
          applyParams.put ("SearchBtn", "VAL1");
          page.setAttributeValue(
                OAWebBeanConstants.ON_KEY_PRESS_ATTR,
                new OABoundValueEnterOnKeyPress(pageContext,
                      "DefaultFormName", // enclosing form name
                      applyParams, // request parameters
                      false, // client unvalidated
                      false)); // server unvalidated
    // End
      clearValues(pageContext,webBean);
      XXONGbwUtil utils= new XXONGbwUtil();
      String respAccess=null;
      respAccess=utils.respAccessLevel(am.getOADBTransaction());
      log("respAccess-->"+respAccess);
      OAAdvancedTableBean advancedTable=(OAAdvancedTableBean)webBean.findChildRecursive("ResultAdvTabRN");
       OAColumnBean  rOrderFlag=(OAColumnBean)advancedTable.findChildRecursive("rOrderFlag");
       OAColumnBean  rd=(OAColumnBean)advancedTable.findChildRecursive("rd");
       OAColumnBean  sad=(OAColumnBean)advancedTable.findChildRecursive("sad");
     OAMessageLovInputBean rescheReason =(OAMessageLovInputBean)advancedTable.findIndexedChildRecursive("RescheReasonLov");     
   if(respAccess!=null&&(respAccess.equalsIgnoreCase("S")||respAccess.equalsIgnoreCase("P")))
      {
        OAColumnBean  mpdate=(OAColumnBean)advancedTable.findChildRecursive("MPDate");   
         mpdate.setRendered(true);
          rd.setRendered(true);
          sad.setRendered(true);
          rOrderFlag.setRendered(true);
      }else {      
          // advanced table columns
           rescheReason.setReadOnly(true);           
            OAMessageDateFieldBean ssd =(OAMessageDateFieldBean)advancedTable.findIndexedChildRecursive("itemSSD");
            ssd.setReadOnly(true);
            OAMessageDateFieldBean rsd =(OAMessageDateFieldBean)advancedTable.findIndexedChildRecursive("itemRSD");
            rsd.setReadOnly(true);
            OAMessageTextInputBean quantity =(OAMessageTextInputBean)advancedTable.findIndexedChildRecursive("item3");
            quantity.setReadOnly(true);
            OAMultipleSelectionBean multipleSelectionBean =(OAMultipleSelectionBean)advancedTable.getTableSelection();
            OAButtonBean   updateBtnButton=(OAButtonBean)webBean.findChildRecursive("updateBtn");
           // make buttons readOnly
             OAButtonBean   scherevButton=(OAButtonBean)webBean.findChildRecursive("scherev");             
             if(respAccess!=null&&respAccess.equals("M"))
             {
                 scherevButton.setDisabled(false);
                 multipleSelectionBean.setRendered(true);
                 rd.setRendered(true);
                 sad.setRendered(true);
                 rOrderFlag.setRendered(true);
                 updateBtnButton.setDisabled(false);
             }else{
                 scherevButton.setDisabled(true);
                 multipleSelectionBean.setRendered(false);
                 rd.setRendered(false);
                 sad.setRendered(false);
                 rOrderFlag.setRendered(false);
                 updateBtnButton.setDisabled(true);
             }
            OAButtonBean   rsdssdButton=(OAButtonBean)webBean.findChildRecursive("rsdssd");
            rsdssdButton.setDisabled(true);           
           
      }     
      String pageType=null;
      String actionType=null;
      pageType=pageContext.getParameter("pageType");
      actionType=pageContext.getParameter("ActionType");
      log("pageType-->"+pageType);
      log("actionType-->"+actionType);
      if(pageType!=null&&pageType.trim().length()>0&&pageType.equals("updatePage"))
      {
            am.invokeMethod("initCriteriaValuesVO");
            am.invokeMethod("recDisInit");
            utils.recordsTobeDisplayed(pageContext,webBean,"ResultAdvTabRN");
            loadSessionValues(pageContext,"Y",webBean);
            Serializable params[]={actionType};
            am.invokeMethod("ExecuteResultVO",params); 
            throw new OAException("XXON","XXON_OM_GBW_RECORD_SAVE",
                                           null,//tokens,
                                           OAException.CONFIRMATION,
                                           null);
      
      }else{      
          am.invokeMethod("initCriteriaValuesVO");
          am.invokeMethod("recDisInit");
        }
        
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
      String actionEvent=pageContext.getParameter(EVENT_PARAM);
      String lovInputSourceId=pageContext.getLovInputSourceId();
      String rowReference =  pageContext.getParameter(OAWebBeanConstants.EVENT_SOURCE_ROW_REFERENCE);
      log("ActionEvents::"+actionEvent);
      log("lovInputSourceId::"+lovInputSourceId); 
      log("rowReference-->"+rowReference);
      XXONGbwUtil utils= new XXONGbwUtil();
      String respAccess=null;
      respAccess=utils.respAccessLevel(am.getOADBTransaction());
      log("respAccess-->"+respAccess);
      setIndexToDateRangeBean(pageContext,webBean,null);
     if("Go".equalsIgnoreCase(actionEvent)){      
          setIndexToDateRangeBean(pageContext,webBean,null);
          utils.recordsTobeDisplayed(pageContext,webBean,"ResultAdvTabRN");
          Serializable params[]={"Go"};
          am.invokeMethod("ExecuteResultVO",params);         
          keepSearchValuesInSession(am,pageContext,webBean);            
      }else if("FilterRows".equalsIgnoreCase(actionEvent)){
          setIndexToDateRangeBean(pageContext,webBean,null);
          utils.recordsTobeDisplayed(pageContext,webBean,"ResultAdvTabRN");
          Serializable params[]={"Go"};
          am.invokeMethod("ExecuteResultVO",params); 
          keepSearchValuesInSession(am,pageContext,webBean);
      }else if("Cancel".equalsIgnoreCase(actionEvent)){
          log("inside Cancel Event:");
          pageContext.forwardImmediately(
                  "OA.jsp?OAFunc=OAHOMEPAGE",                
                   null,
                   OAWebBeanConstants.KEEP_MENU_CONTEXT,
                   null,
                   null,
                   false, // Retain AM
                   OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
      }else if("updateRecords".equalsIgnoreCase(actionEvent)){      
            String msgList=null;
            msgList=(String)am.invokeMethod("validateRecords"); 
        log("msgList-->"+msgList);
          if(msgList!=null&&msgList.trim().length()>0)
          {
            callOADialogPage(pageContext,msgList);
          }else{
              am.invokeMethod("passDataToObject");   
              loadSessionValues(pageContext,"N",webBean);
              hmap.put("ActionType","updateRecords");
              pageContext.setForwardURL("OA.jsp?OAFunc=XXON_OM_GBW_GBLBAK_VIEW_FR",
                                                 null,
                                                 OAWebBeanConstants.KEEP_MENU_CONTEXT,
                                                 null,
                                                 hmap,//hashmap
                                                 false, // Retain AM
                                                 OAWebBeanConstants.ADD_BREAD_CRUMB_NO, // Show breadcrumbs
                                                 OAWebBeanConstants.IGNORE_MESSAGES);
          }
      }else if (pageContext.getParameter("SaveYesButton") != null){
          am.invokeMethod("passDataToObject");   
          loadSessionValues(pageContext,"N",webBean);
          hmap.put("ActionType","updateRecords");
          pageContext.setForwardURL("OA.jsp?OAFunc=XXON_OM_GBW_GBLBAK_VIEW_FR",
                                             null,
                                             OAWebBeanConstants.KEEP_MENU_CONTEXT,
                                             null,
                                             hmap,//hashmap
                                             false, // Retain AM
                                             OAWebBeanConstants.ADD_BREAD_CRUMB_NO, // Show breadcrumbs
                                             OAWebBeanConstants.IGNORE_MESSAGES);
      
      }else if (pageContext.getParameter("SaveNoButton") != null)
      {
          pageContext.setForwardURL("OA.jsp?OAFunc=XXON_OM_GBW_GBLBAK_VIEW_FR",
                                             null,
                                             OAWebBeanConstants.KEEP_MENU_CONTEXT,
                                             null,
                                             null,//hashmap
                                             true, // Retain AM
                                             OAWebBeanConstants.ADD_BREAD_CRUMB_NO, // Show breadcrumbs
                                             OAWebBeanConstants.IGNORE_MESSAGES);
      }else if("scheRev".equalsIgnoreCase(actionEvent)){
          am.invokeMethod("scheRev");
          loadSessionValues(pageContext,"N",webBean);
          hmap.put("ActionType","scheRev");
          pageContext.setForwardURL("OA.jsp?OAFunc=XXON_OM_GBW_GBLBAK_VIEW_FR",
                                             null,
                                             OAWebBeanConstants.KEEP_MENU_CONTEXT,
                                             null,
                                             hmap,//hashmap
                                             false, // Retain AM
                                             OAWebBeanConstants.ADD_BREAD_CRUMB_NO, // Show breadcrumbs
                                             OAWebBeanConstants.IGNORE_MESSAGES);
      
      }else if("RSDeqSSD".equalsIgnoreCase(actionEvent)){
               log("actionEvents RSDeqSSD");
                am.invokeMethod("rsdEqSsd");
                loadSessionValues(pageContext,"N",webBean);
                hmap.put("ActionType","rsdEqSsd");
                pageContext.setForwardURL("OA.jsp?OAFunc=XXON_OM_GBW_GBLBAK_VIEW_FR",
                                                   null,
                                                   OAWebBeanConstants.KEEP_MENU_CONTEXT,
                                                   null,
                                                   hmap,//hashmap
                                                   false, // Retain AM
                                                   OAWebBeanConstants.ADD_BREAD_CRUMB_NO, // Show breadcrumbs
                                                   OAWebBeanConstants.IGNORE_MESSAGES);
            
       }else if("rowsField".equalsIgnoreCase(actionEvent)){
                  String rowCount = pageContext.getParameter("rowsField");
                  if(rowCount!=null)
                  {
                    pageContext.putSessionValue("RecordsDisplayed",rowCount);
                  }
                  utils.recordsTobeDisplayed(pageContext,webBean,"ResultAdvTabRN");
       }else if("tablerefresh".equalsIgnoreCase(actionEvent))
       {
                 OAAdvancedTableBean  advTab=(OAAdvancedTableBean)webBean.findChildRecursive("ResultAdvTabRN");
                 advTab.queryData(pageContext, false);
                 Serializable param[]={respAccess,null};
                 am.invokeMethod("setCumulativeQty",param); 
       }else if("export".equalsIgnoreCase(actionEvent))
       {          
        try{
            String columnName[]    = null;
            String viewAttribute[] = null;
             if(respAccess!=null&&(respAccess.equalsIgnoreCase("S")||respAccess.equalsIgnoreCase("P"))) 
             {
                 columnName    = new String[25];
                 viewAttribute = new String[25];
                 
                columnName[0] = "OPN";
                columnName[1] = "Corp Code";
                columnName[2] = "Cust5 Code";                        
                columnName[3] = "Order Hold";
                columnName[4] = "Order Type";                
                columnName[5] = "Legacy SO";
                columnName[6] = "Legacy Line#";
                columnName[7] = "PO#";
                columnName[8] = "Order#";
                columnName[9] = "Line#";
                columnName[10] = "RD";
                columnName[11] = "RSD";
                columnName[12] = "SSD";
                columnName[13] = "SAD";
                columnName[14] = "Reschedule Reason";
                columnName[15] = "SSD#";
                columnName[16] ="Quantity";
                columnName[17] ="Cumulative Qty";
                columnName[18] ="Pegging Detail";
                columnName[19] ="Plan Date";
                columnName[20] ="CSR";
                columnName[21] ="Split Flag";
                columnName[22] ="SCA";
                columnName[23] ="SIR";
                columnName[24] ="Rush Order";
                 
                 viewAttribute[0] = "Opn";
                 viewAttribute[1] = "CustomerCode";
                 viewAttribute[2] = "Cust5Code";                        
                 viewAttribute[3] = "OrderHold";
                 viewAttribute[4] = "OrderType";                
                 viewAttribute[5] = "LegacySo";
                 viewAttribute[6] = "LegacyLineNumber";
                 viewAttribute[7] = "PoNumber";
                 viewAttribute[8] = "OrderNumber";
                 viewAttribute[9] = "LineNumber"; 
                 viewAttribute[10] = "Rd"; 
                 viewAttribute[11] = "Rsd";
                 viewAttribute[12] = "Ssd";
                 viewAttribute[13] = "Sad";
                 viewAttribute[14] = "RescheduleReason";
                 viewAttribute[15] = "SsdResceduleCounter";
                 viewAttribute[16] ="Quantity";
                 viewAttribute[17] ="CumulativeQuantity";
                 viewAttribute[18] ="PeggingDetail";
                 viewAttribute[19] ="MpDate";
                 viewAttribute[20] ="Csr";
                 viewAttribute[21] ="SplitFlag";
                 viewAttribute[22] ="ItemPlanner";
                 viewAttribute[23] ="SchedulingReturn";
                 viewAttribute[24] ="RushOrderFlag";
            
             }else if(respAccess!=null&&respAccess.equalsIgnoreCase("M")) {
                 columnName    = new String[23];
                 viewAttribute = new String[23];
                 
                 columnName[0] = "OPN";
                 columnName[1] = "Corp Code";
                 columnName[2] = "Cust5 Code";
                 columnName[3] = "Order Hold";
                 columnName[4] = "Order Type";
                 columnName[5] = "Legacy SO";
                 columnName[6] = "Legacy Line#";
                 columnName[7] = "PO#";
                 columnName[8] = "Order#";
                 columnName[9] = "Line#";
                 columnName[10] = "RD";
                 columnName[11] = "RSD";
                 columnName[12] = "SSD";
                 columnName[13] = "SAD";
                 columnName[14] = "Reschedule Reason";
                 columnName[15] = "SSD#";
                 columnName[16] ="Quantity";
                 columnName[17] ="Cumulative Qty";
                 columnName[18] ="Pegging Detail";
                 columnName[19] ="CSR";
                 columnName[20] ="Split Flag";
                 columnName[21] ="SIR";
                 columnName[22] ="Rush Order";
                 
                 viewAttribute[0] = "Opn";
                 viewAttribute[1] = "CustomerCode";
                 viewAttribute[2] = "Cust5Code";                        
                 viewAttribute[3] = "OrderHold";
                 viewAttribute[4] = "OrderType";                
                 viewAttribute[5] = "LegacySo";
                 viewAttribute[6] = "LegacyLineNumber";
                 viewAttribute[7] = "PoNumber";
                 viewAttribute[8] = "OrderNumber";
                 viewAttribute[9] = "LineNumber"; 
                 viewAttribute[10] = "Rd";
                 viewAttribute[11] = "Rsd";
                 viewAttribute[12] = "Ssd";
                 viewAttribute[13] = "Sad";
                 viewAttribute[14] = "RescheduleReason";
                 viewAttribute[15] = "SsdResceduleCounter";
                 viewAttribute[16] ="Quantity";
                 viewAttribute[17] ="CumulativeQuantity";
                 viewAttribute[18] ="PeggingDetail";
                 viewAttribute[19] ="Csr";
                 viewAttribute[20] ="SplitFlag";
                 viewAttribute[21] ="SchedulingReturn";
                 viewAttribute[22] ="RushOrderFlag";
             }else{
                 columnName    = new String[21];
                 viewAttribute = new String[21];
                 
                 columnName[0] = "OPN";
                 columnName[1] = "Corp Code";
                 columnName[2] = "Cust5 Code";
                 columnName[3] = "Order Hold";
                 columnName[4] = "Order Type";
                 columnName[5] = "Legacy SO";
                 columnName[6] = "Legacy Line#";
                 columnName[7] = "PO#";
                 columnName[8] = "Order#";
                 columnName[9] = "Line#";
                 columnName[10] = "RSD";
                 columnName[11] = "SSD";
                 columnName[12] = "Reschedule Reason";
                 columnName[13] = "SSD#";
                 columnName[14] ="Quantity";
                 columnName[15] ="Cumulative Qty";
                 columnName[16] ="Pegging Detail";
                 columnName[17] ="CSR";
                 columnName[18] ="Split Flag";
                 columnName[19] ="SCA";
                 columnName[20] ="SIR";
                 
                 viewAttribute[0] = "Opn";
                 viewAttribute[1] = "CustomerCode";
                 viewAttribute[2] = "Cust5Code";                        
                 viewAttribute[3] = "OrderHold";
                 viewAttribute[4] = "OrderType";                
                 viewAttribute[5] = "LegacySo";
                 viewAttribute[6] = "LegacyLineNumber"; 
                 viewAttribute[7] = "PoNumber"; 
                 viewAttribute[8] = "OrderNumber";
                 viewAttribute[9] = "LineNumber"; 
                 viewAttribute[10] = "Rsd";
                 viewAttribute[11] = "Ssd";
                 viewAttribute[12] = "RescheduleReason";
                 viewAttribute[13] = "SsdResceduleCounter";
                 viewAttribute[14] ="Quantity";
                 viewAttribute[15] ="CumulativeQuantity";
                 viewAttribute[16] ="PeggingDetail";
                 viewAttribute[17] ="Csr";
                 viewAttribute[18] ="SplitFlag";
                 viewAttribute[19] ="ItemPlanner";
                 viewAttribute[20] ="SchedulingReturn";
             }
            XXONGbwUtil csvFile = new XXONGbwUtil();
            csvFile.writeXLSXFile(pageContext, 
                                   "Global_Backlog_View_Reschedule",    // file name as you wish but without extension
                                    "XXONGbwBaklogViewResultVO1",  // view instance name
                                    columnName,               // all Column names array
                                    viewAttribute             // viewAttribute Name
                                    );
            }catch(OAException exception){
                          throw exception;
                      }catch(Exception exception){
                          throw new OAException("Error while exporting the data to excel -->"+exception.getMessage(),OAException.ERROR);
                      }
         } else if(("lovPrepare".equals(actionEvent))||("lovValidate".equals(actionEvent))||("lovUpdate".equals(actionEvent)))
         {
              log("lov event-->");              
             if ("cust5code".equalsIgnoreCase(lovInputSourceId)) 
              {
                
                 String corpCode=null;
                 corpCode=pageContext.getParameter("CorpCode");
                 log("corpCode-->"+corpCode);
                 if(corpCode!=null&&corpCode.trim().length()>0)
                 {
                   Serializable param[]={corpCode};
                   am.invokeMethod("exeCust5CodeLOV",param);
                 }else{
                     am.invokeMethod("exeCust5CodeLOV");
                 }
              }
          }else if("sort".equalsIgnoreCase(actionEvent))
          {    
               log("inside sort event-->");
               Serializable param[]={respAccess,null};
               am.invokeMethod("setCumulativeQty",param);
          }else if("goto".equalsIgnoreCase(actionEvent))
          {    
               log("inside sort and goto event just to handle enter key-->");              
          }else if (pageContext.getParameter("SearchBtn")!=null)
          {
              log("inside SearchBt::");
              setIndexToDateRangeBean(pageContext,webBean,null);
              utils.recordsTobeDisplayed(pageContext,webBean,"ResultAdvTabRN");
              Serializable params[]={"Go"};
              am.invokeMethod("ExecuteResultVO",params);        
              keepSearchValuesInSession(am,pageContext,webBean); 
          }
     
      
  }  // End of PFR
    /**
     * Below Method is to print the log messages
     *@param Message for text 
    */
    private void log(String Message)
    {
        XXONGbwUtil utils= new XXONGbwUtil();
        utils.log(Message,am.getOADBTransaction());      
    }

    private void loadSessionValues(OAPageContext pageContext,String reloadPage,OAWebBean webBean)
    {
        String opn=null;
        String pal=null;
        String pkg=null;
        String coi=null;
        String corpcode=null;
        String cust5code=null;
        String demandbank=null;
        String ordernumber=null;
        String unscheDemands=null;
        String mismachedDates=null;
        String oUnit=null;      
        String oUnitName=null;
        String orderType=null;
        String legacySo=null;
        String lineNumber=null;
        String dateRange=null;
        String poNumber=null;
        String scheduledDemands=null;
        String excludeFr=null;
        if(pageContext.getSessionValue("pOpn")!=null&&pageContext.getSessionValue("pOpn")!="")
        {
          opn=(String)pageContext.getSessionValue("pOpn");
        }
        if(pageContext.getSessionValue("pPal")!=null&&pageContext.getSessionValue("pPal")!="")
        {
          pal=(String)pageContext.getSessionValue("pPal");
        }
        if(pageContext.getSessionValue("pPkg")!=null&&pageContext.getSessionValue("pPkg")!="")
        {
          pkg=(String)pageContext.getSessionValue("pPkg");
        }
        if(pageContext.getSessionValue("pCoi")!=null&&pageContext.getSessionValue("pCoi")!="")
        {
          coi=(String)pageContext.getSessionValue("pCoi");
        }
        if(pageContext.getSessionValue("pDemandbank")!=null&&pageContext.getSessionValue("pDemandbank")!="")
        {
          demandbank=(String)pageContext.getSessionValue("pDemandbank");
        }
        if(pageContext.getSessionValue("pOrdernumber")!=null&&pageContext.getSessionValue("pOrdernumber")!="")
        {
          ordernumber=(String)pageContext.getSessionValue("pOrdernumber");
        }
        if(pageContext.getSessionValue("pCorpcode")!=null&&pageContext.getSessionValue("pCorpcode")!="")
        {
          corpcode=(String)pageContext.getSessionValue("pCorpcode");
        }
        if(pageContext.getSessionValue("pCust5code")!=null&&pageContext.getSessionValue("pCust5code")!="")
        {
          cust5code=(String)pageContext.getSessionValue("pCust5code");
        }     
        if(pageContext.getSessionValue("pUnscheDemands")!=null&&pageContext.getSessionValue("pUnscheDemands")!="")
        {
          unscheDemands=(String)pageContext.getSessionValue("pUnscheDemands");
        }
        if(pageContext.getSessionValue("pMismachedDates")!=null&&pageContext.getSessionValue("pMismachedDates")!="")
        {
          mismachedDates=(String)pageContext.getSessionValue("pMismachedDates");
        }
        if(pageContext.getSessionValue("pOUnit")!=null&&pageContext.getSessionValue("pOUnit")!="")
        {
          oUnit=(String)pageContext.getSessionValue("pOUnit");
        }
        if(pageContext.getSessionValue("pOUnitName")!=null&&pageContext.getSessionValue("pOUnitName")!="")
        {
          oUnitName=(String)pageContext.getSessionValue("pOUnitName");
        }
        if(pageContext.getSessionValue("pOrderType")!=null&&pageContext.getSessionValue("pOrderType")!="")
         {
           orderType=(String)pageContext.getSessionValue("pOrderType");
         }
        if(pageContext.getSessionValue("pLegacySo")!=null&&pageContext.getSessionValue("pLegacySo")!="")
         {
           legacySo=(String)pageContext.getSessionValue("pLegacySo");
         }
        if(pageContext.getSessionValue("pLineNumber")!=null&&pageContext.getSessionValue("pLineNumber")!="")
         {
           lineNumber=(String)pageContext.getSessionValue("pLineNumber");
         }
        if(pageContext.getSessionValue("pDateRange")!=null&&pageContext.getSessionValue("pDateRange")!="")
         {
           dateRange=(String)pageContext.getSessionValue("pDateRange");
         }
        
        if(pageContext.getSessionValue("pPoNumber")!=null&&pageContext.getSessionValue("pPoNumber")!="")
         {
           poNumber=(String)pageContext.getSessionValue("pPoNumber");
         }
         //
        if(pageContext.getSessionValue("pScheduledDemands")!=null&&pageContext.getSessionValue("pScheduledDemands")!="")
         {
           scheduledDemands=(String)pageContext.getSessionValue("pScheduledDemands");
         }
        if(pageContext.getSessionValue("pExcludeFr")!=null&&pageContext.getSessionValue("pExcludeFr")!="")
         {
           excludeFr=(String)pageContext.getSessionValue("pExcludeFr");
         }
        
        hmap.put("pageType","updatePage");
        hmap.put("pOpn",opn);
        hmap.put("pPal",pal);
        hmap.put("pPkg",pkg);
        hmap.put("pCoi",coi);
        hmap.put("pDemandbank",demandbank);
        hmap.put("pOrdernumber",ordernumber);
        hmap.put("pCorpcode",corpcode);
        hmap.put("pCust5code",cust5code);
        hmap.put("pUnscheDemands",unscheDemands);
        hmap.put("pMismachedDates",mismachedDates);
        hmap.put("pOUnit",oUnit);
        hmap.put("pOUnitName",oUnitName);
        hmap.put("pOrderType",orderType);
        hmap.put("pLegacySo",legacySo);
        hmap.put("pLineNumber",lineNumber);
        hmap.put("pDateRange",dateRange);
        hmap.put("pPoNumber",poNumber);
        hmap.put("pScheduleDemands",scheduledDemands);
        hmap.put("pExcludeFr",excludeFr);
    if(reloadPage!=null&&reloadPage.equals("Y"))
    {
      OAMessageLovInputBean   opnBean=(OAMessageLovInputBean)webBean.findChildRecursive("Opn");
        opnBean.setValue(pageContext,opn);
        OAMessageLovInputBean   pkgBean=(OAMessageLovInputBean)webBean.findChildRecursive("Pkg");
          pkgBean.setValue(pageContext,pkg);
        OAMessageTextInputBean   palBean=(OAMessageTextInputBean)webBean.findChildRecursive("Pal");
          palBean.setValue(pageContext,pal);
        OAMessageLovInputBean   ccBean=(OAMessageLovInputBean)webBean.findChildRecursive("CorpCode");
          ccBean.setValue(pageContext,corpcode);
        OAMessageLovInputBean   c5Bean=(OAMessageLovInputBean)webBean.findChildRecursive("cust5code");
          c5Bean.setValue(pageContext,cust5code);
        OAMessageTextInputBean   orderNumberBean=(OAMessageTextInputBean)webBean.findChildRecursive("orderNumber");
          orderNumberBean.setValue(pageContext,ordernumber);
        OAMessageLovInputBean   demandBankBean=(OAMessageLovInputBean)webBean.findChildRecursive("DemandBank");
          demandBankBean.setValue(pageContext,demandbank);
        OAMessageLovInputBean   coiBean=(OAMessageLovInputBean)webBean.findChildRecursive("coi");
          coiBean.setValue(pageContext,coi);
        OAMessageCheckBoxBean   unscheduledDemands=(OAMessageCheckBoxBean)webBean.findChildRecursive("unscheduledDemands");
          unscheduledDemands.setValue(pageContext,unscheDemands);
        OAMessageCheckBoxBean   mismatchedDemands=(OAMessageCheckBoxBean)webBean.findChildRecursive("mismatchedDemands");
          mismatchedDemands.setValue(pageContext,mismachedDates);
        OAMessageLovInputBean   opunitName=(OAMessageLovInputBean)webBean.findChildRecursive("opunit");
          opunitName.setValue(pageContext,oUnitName);
        OAFormValueBean opunit=(OAFormValueBean)webBean.findChildRecursive("SorgId");
          opunit.setValue(pageContext,oUnit);
        OAMessageLovInputBean   orderTypeLovBean=(OAMessageLovInputBean)webBean.findChildRecursive("orderType");
          orderTypeLovBean.setValue(pageContext,orderType);
        OAMessageTextInputBean   legacySoBean=(OAMessageTextInputBean)webBean.findChildRecursive("legacySo");
          legacySoBean.setValue(pageContext,legacySo);
        OAMessageTextInputBean   lineNumBean=(OAMessageTextInputBean)webBean.findChildRecursive("lineNum");
          lineNumBean.setValue(pageContext,lineNumber);
             setIndexToDateRangeBean(pageContext,webBean,dateRange);
        OAMessageTextInputBean   poNumberBean=(OAMessageTextInputBean)webBean.findChildRecursive("PoNumber");
          poNumberBean.setValue(pageContext,poNumber);
        OAMessageCheckBoxBean   scheduledDemandsBean=(OAMessageCheckBoxBean)webBean.findChildRecursive("ScheduledDemands");
          scheduledDemandsBean.setValue(pageContext,scheduledDemands);
        OAMessageCheckBoxBean   excludeFrBean=(OAMessageCheckBoxBean)webBean.findChildRecursive("ExcludeFr");
          excludeFrBean.setValue(pageContext,excludeFr);
    }
    }
  private void clearValues(OAPageContext pageContext,OAWebBean webBean)
    {
      StringBuffer jsString = new StringBuffer();
                  jsString.append( "function clearFunction()");
                  jsString.append("{");
                  jsString.append("if(document.getElementById('Opn').value!==null)document.getElementById('Opn').value='';" );
                  jsString.append("if(document.getElementById('Pal').value!==null)document.getElementById('Pal').value='';") ;
                  jsString.append("if(document.getElementById('Pkg').value!==null)document.getElementById('Pkg').value='';");
                  jsString.append("if(document.getElementById('CorpCode').value!==null)document.getElementById('CorpCode').value='';") ;
                  jsString.append("if(document.getElementById('cust5code').value!==null)document.getElementById('cust5code').value='';") ;
                  jsString.append("if(document.getElementById('orderNumber').value!==null)document.getElementById('orderNumber').value='';") ;
                  jsString.append("if(document.getElementById('DemandBank').value!==null)document.getElementById('DemandBank').value='';") ;
                  jsString.append("if(document.getElementById('coi').value!==null)document.getElementById('coi').value='';");
                  jsString.append("if(document.getElementById('PoNumber').value!==null)document.getElementById('PoNumber').value='';");
                  jsString.append("if(document.getElementById('DateRange').value!==null)document.getElementById('DateRange').selectedIndex =0;");
                  jsString.append("document.getElementById('unscheduledDemands').checked =false;") ;
                  jsString.append("document.getElementById('mismatchedDemands').checked =false;") ;
                  jsString.append("document.getElementById('ScheduledDemands').checked =false;") ;
                  jsString.append("document.getElementById('ExcludeFr').checked =false;") ;
                  jsString.append("document.getElementById('opunit').value='';" );
                  jsString.append("document.getElementById('orderType').value='';" );
                  jsString.append("document.getElementById('legacySo').value='';" );
                  jsString.append("document.getElementById('lineNum').value='';" );
                  jsString.append("}" );
                  log("jsString-->"+jsString.toString());
                  pageContext.putJavaScriptFunction("clearFunction",jsString.toString());
                  OAButtonBean clearButton2 = (OAButtonBean) webBean.findChildRecursive("ClearBtn") ;
                  String javaSClear = "javascript:clearFunction();";
                  clearButton2.setOnClick(javaSClear);
  }
    
    private void keepSearchValuesInSession(OAApplicationModule am,OAPageContext pageContext,OAWebBean webBean)
    {
        OAViewObject  serachValuesVO=(OAViewObject)am.findViewObject("XXONGbwSearchCriteriaValuesVO1");
        XXONGbwSearchCriteriaValuesVORowImpl criteriaValuesVORow=(XXONGbwSearchCriteriaValuesVORowImpl)serachValuesVO.first();
        String opn=null;
        String pal=null;
        String pkg=null;
        String coi=null;
        String corpcode=null;
        String cust5code=null;
        String demandbank=null;
        String ordernumber=null;
        String unscheDemands=null;
        String mismachedDates=null;
        String oUnit=null;
        String orderType=null;
        String ouName=null;
        String legacySo=null;
        String lineNumber=null;
        String dateRange=null;
        String poNumber=null;
        String scheduledDemands=null;
        String excludeFr=null;
        opn=criteriaValuesVORow.getOpn();
        pal=criteriaValuesVORow.getPal();
        pkg=criteriaValuesVORow.getPkg();
        coi=criteriaValuesVORow.getCoi();
        corpcode=criteriaValuesVORow.getCorpCode();
        cust5code=criteriaValuesVORow.getCust5Code();
        demandbank=criteriaValuesVORow.getDemandBank();
        ordernumber=criteriaValuesVORow.getOrderNumber();
        unscheDemands=criteriaValuesVORow.getUnscheduledDemands();
        mismachedDates=criteriaValuesVORow.getMismatchedDemands();
        oUnit=criteriaValuesVORow.getOrgId();
        ouName=criteriaValuesVORow.getOpUnit();
        orderType=criteriaValuesVORow.getOrderType();
        legacySo=criteriaValuesVORow.getlegacySo();
        lineNumber=criteriaValuesVORow.getlineNumber();
        dateRange=criteriaValuesVORow.getDateRange();
        poNumber=criteriaValuesVORow.getPoNumber();
        scheduledDemands=criteriaValuesVORow.getScheduledDemands();
        excludeFr=criteriaValuesVORow.getExcludeFr();   
        if(opn!=null&&opn.trim().length()>0)
        {
          pageContext.putSessionValue("pOpn",opn);
        }else{
          pageContext.putSessionValue("pOpn","");
        }
        if(pkg!=null&&pkg.trim().length()>0)
        {
          pageContext.putSessionValue("pPkg",pkg);
        }else{
          pageContext.putSessionValue("pPkg","");
        }
        if(pal!=null&&pal.trim().length()>0)
        {
          pageContext.putSessionValue("pPal",pal);
        }else{
          pageContext.putSessionValue("pPal","");
        }
        if(coi!=null&&coi.trim().length()>0)
        {
          pageContext.putSessionValue("pCoi",coi);
        }else{
          pageContext.putSessionValue("pCoi","");
        }
        if(corpcode!=null&&corpcode.trim().length()>0)
        {
          pageContext.putSessionValue("pCorpcode",corpcode);
        }else{
          pageContext.putSessionValue("pCorpcode","");
        }
        if(cust5code!=null&&cust5code.trim().length()>0)
        {
          pageContext.putSessionValue("pCust5code",cust5code);
        }else{
          pageContext.putSessionValue("pCust5code","");
        }       
        if(demandbank!=null&&demandbank.trim().length()>0)
        {
          pageContext.putSessionValue("pDemandbank",demandbank);
        }else{
          pageContext.putSessionValue("pDemandbank","");
        }
        if(ordernumber!=null&&ordernumber.trim().length()>0)
        {
          pageContext.putSessionValue("pOrdernumber",ordernumber);
        }else{
          pageContext.putSessionValue("pOrdernumber","");
        }
        if(unscheDemands!=null&&unscheDemands.trim().length()>0)
        {
          pageContext.putSessionValue("pUnscheDemands",unscheDemands);
        }else{
          pageContext.putSessionValue("pUnscheDemands","");
        }
        if(mismachedDates!=null&&mismachedDates.trim().length()>0)
        {
          pageContext.putSessionValue("pMismachedDates",mismachedDates);
        }else{
          pageContext.putSessionValue("pMismachedDates","");
        }
        if(ouName!=null&&oUnit!=null&&oUnit.trim().length()>0)
        {
          pageContext.putSessionValue("pOUnit",oUnit);
          pageContext.putSessionValue("pOUnitName",ouName);
        }else{
          pageContext.putSessionValue("pOUnit","");
          pageContext.putSessionValue("pOUnitName","");
        }
       if(orderType!=null&&orderType.trim().length()>0)
       {
           pageContext.putSessionValue("pOrderType",orderType);
       }else{
           pageContext.putSessionValue("pOrderType","");
       }
        if(legacySo!=null&&legacySo.trim().length()>0)
        {
            pageContext.putSessionValue("pLegacySo",legacySo);
        }else{
            pageContext.putSessionValue("pLegacySo","");
        }
        if(lineNumber!=null&&lineNumber.trim().length()>0)
        {
            pageContext.putSessionValue("pLineNumber",lineNumber);
        }else{
            pageContext.putSessionValue("pLineNumber","");
        }
        if(dateRange!=null&&dateRange.trim().length()>0)
        {
            pageContext.putSessionValue("pDateRange",dateRange);
        }else{
            pageContext.putSessionValue("pDateRange","");
        }
        if(poNumber!=null&&poNumber.trim().length()>0)
        {
            pageContext.putSessionValue("pPoNumber",poNumber);
        }else{
            pageContext.putSessionValue("pPoNumber","");
        }
        if(scheduledDemands!=null&&scheduledDemands.trim().length()>0)
        {
            pageContext.putSessionValue("pScheduledDemands",scheduledDemands);
        }else{
            pageContext.putSessionValue("pScheduledDemands","");
        }
        if(excludeFr!=null&&excludeFr.trim().length()>0)
        {
            pageContext.putSessionValue("pExcludeFr",excludeFr);
        }else{
            pageContext.putSessionValue("pExcludeFr","");
        }
      
    }
 public void callOADialogPage(OAPageContext pageContext,String messageList)
 {
     log("start of callOADialogPage");
     log("message List-->"+messageList);
     OADialogPage dialogPage = null; 
     dialogPage = new OADialogPage(OAException.WARNING,new OAException(messageList),null,"","");
     dialogPage.setReuseMenu(false); 
     String yes = pageContext.getMessage("AK", "FWK_TBX_T_YES", null);
     String no = pageContext.getMessage("AK", "FWK_TBX_T_NO", null);  
     
     dialogPage.setOkButtonItemName("SaveYesButton");
     dialogPage.setNoButtonItemName("SaveNoButton"); 
     
     dialogPage.setOkButtonToPost(true);
     dialogPage.setNoButtonToPost(true);      
     dialogPage.setPostToCallingPage(true); 
     
     dialogPage.setOkButtonLabel(yes); 
     dialogPage.setNoButtonLabel(no); 
     pageContext.redirectToDialogPage(dialogPage);
 }
  public void setIndexToDateRangeBean(OAPageContext pageContext,OAWebBean webBean,String dateRangeSession)
  {
      log("start of setIndexToDateRangeBean-->");
      log("dateRangeSession-->"+dateRangeSession);
      OAMessageChoiceBean   dateRangeBean=(OAMessageChoiceBean)webBean.findChildRecursive("DateRange");
      String dateRange=null;      
       if(dateRangeSession==null)
         {
            dateRange=(String)dateRangeBean.getValue(pageContext);            
         }else{
             dateRange=dateRangeSession;
         }
            log("dateRange-->"+dateRange);
            Serializable params[]={dateRange};
            String rowindex=(String)am.invokeMethod("getDateRangeIndex",params);
            log("rowindex-->"+rowindex);
            int dateRangeIndex=0;   
            dateRangeIndex=Integer.valueOf(rowindex);
            log("dateRangeIndex-->"+dateRangeIndex);
            dateRangeBean.setValue(pageContext,dateRange);
            dateRangeBean.setSelectedIndex(dateRangeIndex);
    
  }
}
