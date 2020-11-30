/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 | Developed by Kishore Marripudi                                            |
 +===========================================================================*/
package oracle.apps.xxon.om.gbw.schctl.webui;

import com.sun.java.util.collections.HashMap;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageCheckBoxBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.nav.OAButtonBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;
import oracle.apps.xxon.om.gbw.schctl.server.XXONGbwScheCtlResultVORowImpl;
import oracle.apps.xxon.om.gbw.schctl.server.XXONGbwSearchValuesVORowImpl;
import oracle.apps.xxon.om.gbw.util.XXONGbwUtil;


/**
 * Controller for ...
 */
public class XXONGbwScheCtlCO extends OAControllerImpl
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
      clearValues(pageContext,webBean);
      XXONGbwUtil utils= new XXONGbwUtil();
      String respAccess=null;
      respAccess=utils.respAccessLevel(am.getOADBTransaction());
      log("respAccess-->"+respAccess);
      if(respAccess!=null&&(respAccess.equalsIgnoreCase("A")||respAccess.equalsIgnoreCase("S")))
            {
              log("give access to page to update and create");
            
            }else{
                  OAAdvancedTableBean advancedTable=(OAAdvancedTableBean)webBean.findChildRecursive("ResultAdvTabRN");
                  OAMessageLovInputBean opn =(OAMessageLovInputBean)advancedTable.findIndexedChildRecursive("item9");
                  opn.setReadOnly(true);
                  OAMessageTextInputBean palLOV =(OAMessageTextInputBean)advancedTable.findIndexedChildRecursive("palItem");
                  palLOV.setReadOnly(true);
                  OAMessageLovInputBean opUnit =(OAMessageLovInputBean)advancedTable.findIndexedChildRecursive("item5");
                  opUnit.setReadOnly(true);
                  OAMessageLovInputBean pkgLOV =(OAMessageLovInputBean)advancedTable.findIndexedChildRecursive("pkgLOV");
                  pkgLOV.setReadOnly(true);
                  OAMessageCheckBoxBean scheMethod =(OAMessageCheckBoxBean)advancedTable.findIndexedChildRecursive("item4");
                  scheMethod.setReadOnly(true);
                  OAButtonBean   saveBtn=(OAButtonBean)webBean.findChildRecursive("saveBtn");
                   saveBtn.setDisabled(true);
                  OAButtonBean   createBtn=(OAButtonBean)webBean.findChildRecursive("CreateBtn");
                  createBtn.setDisabled(true);
                  OAButtonBean   deleteBtn=(OAButtonBean)webBean.findChildRecursive("DeleteBtn");
                  deleteBtn.setDisabled(true);                  
                 }
      String pageType=null;
      pageType=pageContext.getParameter("pageType");
      if(pageType!=null&&pageType.trim().length()>0&&pageType.equals("updatePage"))
      { 
          am.invokeMethod("initSearchValVO");
          am.invokeMethod("recDisInit");
          utils.recordsTobeDisplayed(pageContext,webBean,"ResultAdvTabRN");
          
         String pal   =null;
         String pkg   =null;
         String opn   =null;
          if(pageContext.getSessionValue("pPal")!=null&&pageContext.getSessionValue("pPal")!="")
          {
            pal=(String)pageContext.getSessionValue("pPal");
          }
          if(pageContext.getSessionValue("pPkg")!=null&&pageContext.getSessionValue("pPkg")!="")
          {
            pkg=(String)pageContext.getSessionValue("pPkg");
          }
          if(pageContext.getSessionValue("pOpn")!=null&&pageContext.getSessionValue("pOpn")!="")
          {
            opn=(String)pageContext.getSessionValue("pOpn");
          }
          
          OAMessageLovInputBean  opnMst=(OAMessageLovInputBean)webBean.findChildRecursive("opn");
          opnMst.setValue(pageContext,opn);
          OAMessageTextInputBean  ptiMst=(OAMessageTextInputBean)webBean.findChildRecursive("pal");
          ptiMst.setValue(pageContext,pal);
          OAMessageLovInputBean  pkgMst=(OAMessageLovInputBean)webBean.findChildRecursive("pkg");
          pkgMst.setValue(pageContext,pkg);
          Serializable[] params={"PR"};
          am.invokeMethod("executeResultVO",params);  
          throw new OAException("XXON","XXON_OM_GBW_RECORD_SAVE",
                                           null,//tokens,
                                           OAException.CONFIRMATION,
                                           null);
      }else{
          am.invokeMethod("recDisInit");
          am.invokeMethod("initSearchValVO");          
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
         
      String actionEvents=pageContext.getParameter(EVENT_PARAM);
      String lovInputSourceId=pageContext.getLovInputSourceId();
      String rowReference =  pageContext.getParameter(OAWebBeanConstants.EVENT_SOURCE_ROW_REFERENCE);      
      log("ActionEvents::"+actionEvents);
      log("lovInputSourceId::"+lovInputSourceId); 
      log("rowReference-->"+rowReference);
   
      if(actionEvents.equals("Go"))
      {
          Serializable[] params={"PFR"};
          am.invokeMethod("executeResultVO",params);          
          OAViewObject  serachValuesVO=(OAViewObject)am.findViewObject("XXONGbwSearchValuesVO1");
           XXONGbwSearchValuesVORowImpl row=(XXONGbwSearchValuesVORowImpl)serachValuesVO.first();
           String opn=null;
           String pkg=null;
           String pal=null;
           opn=row.getOpn();
           pkg=row.getPkg();
           pal=row.getPti();
          log("opn-->"+opn);
          log("pal-->"+pal);
          log("pkg-->"+pkg);
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
      }else if(actionEvents.equals("Save"))
      {
         log("inside UpdateSch event");
          am.invokeMethod("updateSchMethod");
          String opn=null;
          String pal=null;
          String pkg=null;
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
          log("opn-->"+opn);
          log("pal-->"+pal);
          log("pkg-->"+pkg);
          HashMap hmap= new HashMap();
          hmap.put("pageType","updatePage");
          hmap.put("pOpn",opn);
          hmap.put("pPal",pal);
          hmap.put("pPkg",pkg);
          pageContext.setForwardURL("OA.jsp?OAFunc=XXON_OM_GBW_SCHCTL",
                                             null,
                                             OAWebBeanConstants.KEEP_MENU_CONTEXT,
                                             null,
                                             hmap,//hashmap
                                             false, // Retain AM
                                             OAWebBeanConstants.ADD_BREAD_CRUMB_NO, // Show breadcrumbs
                                             OAWebBeanConstants.IGNORE_MESSAGES);
      }else if(actionEvents.equals("CreateRow"))
      {
         am.invokeMethod("AddScheMthd");
      
      }else if(actionEvents.equals("Cancel"))
      {
          log("inside Cancel Event:");
          pageContext.forwardImmediately(
                  "OA.jsp?OAFunc=OAHOMEPAGE",                
                   null,
                   OAWebBeanConstants.KEEP_MENU_CONTEXT,
                   null,
                   null,
                   false, // Retain AM
                   OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
      }else if(actionEvents.equals("rowsField"))
      {
          String rowCount = pageContext.getParameter("rowsField");
          if(rowCount!=null)
          {
            pageContext.putSessionValue("RecordsDisplayed",rowCount);
          }
          XXONGbwUtil utils= new XXONGbwUtil();
          utils.recordsTobeDisplayed(pageContext,webBean,"ResultAdvTabRN");
      }else if(actionEvents.equals("tablerefresh"))
             {
                 OAAdvancedTableBean  advTab=(OAAdvancedTableBean)webBean.findChildRecursive("ResultAdvTabRN");
                 advTab.queryData(pageContext, false);
      }else if(actionEvents.equals("Delete"))
             {
                 String s1=pageContext.getMessage("XXON","XXON_OM_GBW_DELETE_MSG",null);
                 am.invokeMethod("DeleteRecord");
                 throw new OAException(s1,OAException.CONFIRMATION);
             }
  if (pageContext.isLovEvent()) 
      { 
          if ("lovValidate".equals(actionEvents) || "lovUpdate".equals(actionEvents))
          {
           log("rowReference-->"+rowReference);
              XXONGbwScheCtlResultVORowImpl row=(XXONGbwScheCtlResultVORowImpl)am.findRowByRef(rowReference);
            if(row!=null)
            {
              String opn=null;
              String pal=null;
              String pkg=null;
              
              opn=(String)row.getAttribute("Opn");
              pal=(String)row.getAttribute("Pti");
              pkg=(String)row.getAttribute("Pkg");
              
              log("opn-->"+opn);
              log("pal-->"+pal);
              log("pkg-->"+pkg);
              
             //
              row.setReadOnlyMpn(Boolean.TRUE);              
              row.setReadOnlyPkg(Boolean.TRUE);
              row.setReadOnlyPti(Boolean.TRUE);
              //
               if(opn!=null)
               { 
                 row.setReadOnlyMpn(Boolean.FALSE);
               }else{
                 row.setReadOnlyPti(Boolean.FALSE);
                 row.setReadOnlyPkg(Boolean.FALSE);
               }
               
               
            }
      }
      
      }
  if("export".equals(actionEvents))
  {     
            try{
                String columnName[]    = new String[6];
                String viewAttribute[] = new String[6];
                   
                    columnName[0] = "OPN";
                    columnName[1] = "PAL";
                    columnName[2] = "Pkg Group Code";                        
                    columnName[3] = "Auto Schedule Method";
                    columnName[4] = "Last Update By";
                    columnName[5] = "Last Update Date";
                    
                    
                    viewAttribute[0] = "Opn";
                    viewAttribute[1] = "Pti";
                    viewAttribute[2] = "Pkg";                        
                    viewAttribute[3] = "SchedulingMethod";
                    viewAttribute[4] = "LastUpdatedBy";
                    viewAttribute[5] = "LastUpdateDate";
                   
                  
                
                XXONGbwUtil csvFile = new XXONGbwUtil();
                csvFile.writeXLSXFile(pageContext, 
                                       "Schedule_Control_data",    // file name as you wish but without extension
                                        "XXONGbwScheCtlResultVO1",  // view instance name
                                        columnName,               // all Column names array
                                        viewAttribute             // viewAttribute Name
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
            jsString.append("if(document.getElementById('opn').value!==null)document.getElementById('opn').value='';" );
            jsString.append("if(document.getElementById('pal').value!==null)document.getElementById('pal').value='';") ;
            jsString.append("if(document.getElementById('pkg').value!==null)document.getElementById('pkg').value='';");
            jsString.append("}" );
            log("jsString-->"+jsString.toString());
            pageContext.putJavaScriptFunction("clearFunction",jsString.toString());
            OAButtonBean clearButton2 = (OAButtonBean) webBean.findChildRecursive("clearBtn") ;
            String javaSClear = "javascript:clearFunction();";
            clearButton2.setOnClick(javaSClear);
    }
}
