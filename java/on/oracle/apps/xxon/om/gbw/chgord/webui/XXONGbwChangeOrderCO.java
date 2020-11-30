/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package oracle.apps.xxon.om.gbw.chgord.webui;
import com.sun.java.util.collections.HashMap;

import java.io.Serializable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.nav.OAButtonBean;
import oracle.apps.xxon.om.gbw.chgord.server.XXONGbwChangeOrderAMImpl;
import oracle.apps.xxon.om.gbw.util.XXONGbwUtil;
import java.util.Hashtable;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.framework.webui.OABoundValueEnterOnKeyPress;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.form.OAFormValueBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;


/**
 * Controller for ...
 */
public class XXONGbwChangeOrderCO extends OAControllerImpl 
{
    public static final String RCS_ID = "$Header$";
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
        //Check for back navigation
        if (!pageContext.isBackNavigationFired(false))
        {
          TransactionUnitHelper.startTransactionUnit(pageContext, "MpqTrxn"); 
          if ((pageContext.getParameter("SaveYesButton") == null)  && pageContext.getParameter("SaveNoButton") == null)
          {
             super.processRequest(pageContext, webBean);
          }
        
        XXONGbwChangeOrderAMImpl am = (XXONGbwChangeOrderAMImpl)pageContext.getApplicationModule(webBean);
        String h_mpq=null;
        String pageType = pageContext.getParameter("pageType");
        // Enter key press validation..
        OAPageLayoutBean page = pageContext.getPageLayoutBean();
        Hashtable applyParams = new Hashtable();
        applyParams.put ("searchBtn", "VAL1");
        page.setAttributeValue(OAWebBeanConstants.ON_KEY_PRESS_ATTR,
                               new OABoundValueEnterOnKeyPress(pageContext,
                               "DefaultFormName", // enclosing form name
                               applyParams, // request parameters
                               false, // client unvalidated
                               false)); // server unvalidated
                               
        //Defaulting Planner Id details..
        XXONGbwUtil utils = new XXONGbwUtil();
        String userId = am.getOADBTransaction().getUserName();
        log("User or Planner Id :"+userId,pageContext);
        OAMessageTextInputBean planner=(OAMessageTextInputBean)webBean.findChildRecursive("plannerItem");        
        if(planner!=null) 
        {
            planner.setValue(pageContext,userId);
        }
        
        //Clear Button Validation..
         clearValues(pageContext,webBean);         
        
        //Based on Responsibility Access,buttons disable validation..  
        String respAccess = utils.respAccessLevel(am.getOADBTransaction()); 
         //String respAccess="M";
        log((new StringBuilder()).append("respAccess-->").append(respAccess).toString(),pageContext);
        am.executeVO();
        am.getXXONGbwDummyVO1().first().setAttribute("CSREnableCol",Boolean.FALSE);
        am.getXXONGbwDummyVO1().first().setAttribute("PlannerEnableCol",Boolean.FALSE);
        am.getXXONGbwDummyVO1().first().setAttribute("PlannerDisableCol",Boolean.TRUE);
        if (respAccess != null && (respAccess.equalsIgnoreCase("S") ||respAccess.equalsIgnoreCase("P"))) 
        {
                    log("Super User Access", pageContext); //Planner 
                    am.getXXONGbwDummyVO1().first().setAttribute("PlannerEnableCol",Boolean.TRUE);
                    am.getXXONGbwDummyVO1().first().setAttribute("PlannerDisableCol",Boolean.FALSE); 
                    OAButtonBean cancelCRBtn =(OAButtonBean)webBean.findChildRecursive("cancrBtn");
                    if (cancelCRBtn != null) cancelCRBtn.setRendered(false);
        } else if (respAccess != null && respAccess.equalsIgnoreCase("M")) 
        {
                    log("User Access", pageContext); //CSR
                    OAButtonBean rejectBtn =(OAButtonBean)webBean.findChildRecursive("rejectBtn");
                    if (rejectBtn != null) rejectBtn.setRendered(false);
                    OAButtonBean approveUpdateBtn = (OAButtonBean)webBean.findChildRecursive("updApprBtn");
                    if (approveUpdateBtn != null) approveUpdateBtn.setText("Update");                     
        } else 
        {
                    log("Manager Access", pageContext); //Read Only Inquiry                                
                    OAButtonBean rejectBtn = (OAButtonBean)webBean.findChildRecursive("rejectBtn");
                    if (rejectBtn != null) rejectBtn.setDisabled(true);
                    OAButtonBean approveUpdateBtn = (OAButtonBean)webBean.findChildRecursive("updApprBtn");
                    if (approveUpdateBtn != null) approveUpdateBtn.setDisabled(true);
                    OAButtonBean cancelCRBtn =(OAButtonBean)webBean.findChildRecursive("cancrBtn");
                    if (cancelCRBtn != null) cancelCRBtn.setRendered(false);;
                    am.getXXONGbwDummyVO1().first().setAttribute("CSREnableCol",Boolean.TRUE);      
        }
        
        //Below if condition code is for setting no. of rows per page validation...
        String rcount = null;
        if (pageContext.getSessionValue("ROWS") != null && pageContext.getSessionValue("ROWS") != "") 
        {            
            rcount = (String)pageContext.getSessionValue("ROWS");
            utils.DisplaySessionRecords(pageContext, webBean, "resultsAdvRN",rcount);
            am.recDisInit();            
        } 
        
        //Below if condition is for refreshing the page after update/approve/reject by sorting the search results accurately by Planner.
         String h_param1=pageContext.getParameter("p1");
         String h_param2=pageContext.getParameter("p2");
         String h_param3=pageContext.getParameter("p3");
         String h_param4=pageContext.getParameter("p4");
         String h_param5=pageContext.getParameter("p5");
         String h_param7=pageContext.getParameter("p7");
         String h_param8=pageContext.getParameter("p8");       
         String executeSearch=pageContext.getParameter("p9"); 
         String h_plannerId=pageContext.getParameter("plan_id");
         String h_orgname=pageContext.getParameter("org_name");  //Added on 30-jan-18 for fixing defect 991
         String h_param6=pageContext.getParameter("p6"); //OrgId
         h_mpq=pageContext.getParameter("ENFORCE_MPQ");         
         com.sun.java.util.collections.ArrayList exceptions = new com.sun.java.util.collections.ArrayList();
         //MessageToken[] tokens={new MessageToken("ORDERNUMBER", orderNumber.toString()),new MessageToken("LINENUMBER",   lineNumber)};
         String h_legacyOrd=pageContext.getParameter("legacy_order");
         String h_lineNum=pageContext.getParameter("line_number");
         String h_poNum=pageContext.getParameter("cust_po_num");
          
        OAMessageLovInputBean  v_opn=(OAMessageLovInputBean)webBean.findChildRecursive("opnItem");
        OAMessageLovInputBean  v_corpcode=(OAMessageLovInputBean)webBean.findChildRecursive("corpCodeItem");
        OAMessageLovInputBean  v_cust5code=(OAMessageLovInputBean)webBean.findChildRecursive("cust5codeItem");
        OAMessageTextInputBean v_pal=(OAMessageTextInputBean)webBean.findChildRecursive("palItem");
        OAMessageTextInputBean v_ordnum=(OAMessageTextInputBean)webBean.findChildRecursive("ordernumItem");
        OAMessageLovInputBean  v_operUnit=(OAMessageLovInputBean)webBean.findChildRecursive("operatinunitItem");
        OAMessageLovInputBean  v_pkg=(OAMessageLovInputBean)webBean.findChildRecursive("pkgItem");
        OAMessageTextInputBean v_legacyOrd=(OAMessageTextInputBean)webBean.findChildRecursive("legacyorderItem");
        OAMessageTextInputBean v_lineNum=(OAMessageTextInputBean)webBean.findChildRecursive("lineItem");
        OAMessageTextInputBean v_poNum=(OAMessageTextInputBean)webBean.findChildRecursive("custponumItem");
        OAFormValueBean v_OrgId=(OAFormValueBean)webBean.findChildRecursive("orgIdFV");
         
        log("OrgId @PR --->"+h_param6,pageContext); 
        log("Orgname @PR --->"+h_orgname,pageContext);        
    //Below code is for refreshing the form after mpq yes/no button click.
        if (pageType != null && pageType.equals("SEARCH_PAGE"))
        {
            String d_opn=pageContext.getParameter("s_opn");
            String d_corpcode=pageContext.getParameter("s_corpcode");
            String d_cust5code=pageContext.getParameter("s_cust5code");
            String d_pal=pageContext.getParameter("s_pal");
            String d_ordNum=pageContext.getParameter("s_ordNum");            
            String d_package=pageContext.getParameter("s_package");            
            String d_legacyOrd=pageContext.getParameter("s_legacyOrd"); 
            String d_lineNum=pageContext.getParameter("s_lineNum");
            String d_executeSearch=pageContext.getParameter("s_ExecuteSearch");
            String d_poNum=pageContext.getParameter("s_poNum");
            String d_operUnit=pageContext.getParameter("s_operUnit");  
            String d_orgName=pageContext.getParameter("s_orgName");  //added by raghu on 2-may-2018
            log("d_operUnit===>"+d_operUnit,pageContext);
            log("d_orgName===>"+d_orgName,pageContext);
            
            if((d_opn!=null ||d_corpcode!=null ||d_cust5code!=null ||d_pal!=null ||d_ordNum!=null||d_operUnit!=null||d_orgName!=null||d_package!=null||respAccess!=null||d_legacyOrd!=null||d_lineNum!=null)&& "Y".equalsIgnoreCase(d_executeSearch))
            {
                if(d_opn!=null)v_opn.setValue(pageContext,d_opn);            
                if(d_corpcode!=null)v_corpcode.setValue(pageContext,d_corpcode);            
                if(d_cust5code!=null)v_cust5code.setValue(pageContext,d_cust5code);            
                if(d_pal!=null)v_pal.setValue(pageContext,d_pal);            
                if(d_ordNum!=null)v_ordnum.setValue(pageContext,d_ordNum);            
                if(d_operUnit!=null)v_OrgId.setValue(pageContext,d_operUnit); 
                if(d_orgName!=null)v_operUnit.setValue(pageContext,d_orgName);  //added by raghu on 2-may-2018          
                if(d_package!=null)v_pkg.setValue(pageContext,d_package); 
                if(d_legacyOrd!=null)v_legacyOrd.setValue(pageContext,d_legacyOrd);            
                if(d_lineNum!=null)v_lineNum.setValue(pageContext,d_lineNum); 
                if(d_poNum!=null)v_poNum.setValue(pageContext,d_poNum); 
                log("From dialog page confirmation",pageContext);
                log("OrgId--->"+d_operUnit,pageContext);
                log("OrgName--->"+d_orgName,pageContext);
                am.searchOrders(d_opn,d_corpcode,d_cust5code,d_pal,d_ordNum,d_operUnit,d_package,respAccess,null,d_legacyOrd,d_lineNum,d_poNum);
                throw new OAException("XXON", "XXON_OM_GBW_RECORD_SAVE",null,OAException.CONFIRMATION,null); 
            }
        }
        else if((h_param1!=null || h_param2!=null ||h_param3!=null ||h_param4!=null ||h_param5!=null ||h_param6!=null || h_param7!=null || h_plannerId!=null || h_orgname!=null || h_legacyOrd!=null || h_lineNum!=null || h_poNum!=null)&& "Y".equalsIgnoreCase(executeSearch))
        {
            
            if(h_param1!=null)v_opn.setValue(pageContext,h_param1);            
            if(h_param2!=null)v_corpcode.setValue(pageContext,h_param2);            
            if(h_param3!=null)v_cust5code.setValue(pageContext,h_param3);            
            if(h_param4!=null)v_pal.setValue(pageContext,h_param4);            
            if(h_param5!=null)v_ordnum.setValue(pageContext,h_param5);            
            if(h_orgname!=null)v_operUnit.setValue(pageContext,h_orgname); 
            if(h_param6!=null) v_OrgId.setValue(pageContext,h_param6); 
            if(h_param7!=null)v_pkg.setValue(pageContext,h_param7);            
            if(planner!=null) planner.setValue(pageContext,h_plannerId);             
            if(h_legacyOrd!=null)v_legacyOrd.setValue(pageContext,h_legacyOrd);            
            if(h_lineNum!=null)v_lineNum.setValue(pageContext,h_lineNum);  
            if(h_poNum!=null)v_poNum.setValue(pageContext,h_poNum);
            log("From main page----",pageContext);
            
            log("OrgName----->"+h_orgname,pageContext);
            log("OrgId------->"+h_param6,pageContext);
            am.searchOrders(h_param1, h_param2, h_param3, h_param4, h_param5,h_param6, h_param7, h_param8, h_plannerId,h_legacyOrd,h_lineNum,h_poNum); 
            throw new OAException("XXON", "XXON_OM_GBW_RECORD_SAVE",null,OAException.CONFIRMATION,null);
         }        
        }
        else
        {
          if (!TransactionUnitHelper.isTransactionUnitInProgress(pageContext, "MpqTrxn",    true))
          {
            OADialogPage dialogPage = new OADialogPage(NAVIGATION_ERROR);
            pageContext.redirectToDialogPage(dialogPage);
           }
        }
    }

    /**
     * Procedure to handle form submissions for form elements in
     * a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processFormRequest(OAPageContext pageContext,OAWebBean webBean) 
    {
        super.processFormRequest(pageContext, webBean);
        XXONGbwChangeOrderAMImpl am =(XXONGbwChangeOrderAMImpl)pageContext.getApplicationModule(webBean);
        XXONGbwUtil utils = new XXONGbwUtil();         
        String respAccess = utils.respAccessLevel(am.getOADBTransaction());        
        //String respAccess="M";
        String eventName = pageContext.getParameter(OAWebBeanConstants.EVENT_PARAM);    
        String p_opn = null;
        String p_corpcode = null;
        String p_package = null;
        String p_cust5code = null;
        String p_pal = null;
        String p_ordNum = null;        
        String p_plannerId=null;
        String P_Status = null;
        String p_rowsField = null;        
        String p_legacyOrd = null;
        String p_lineNum = null;
        String p_poNum = null;
        String p_orgName = null;
        String p_operatingUnit = null;

        p_opn = pageContext.getParameter("opnItem");
        p_corpcode = pageContext.getParameter("corpCodeItem");
        p_package = pageContext.getParameter("pkgItem");
        p_cust5code = pageContext.getParameter("cust5codeItem");
        p_pal = pageContext.getParameter("palItem");
        p_ordNum = pageContext.getParameter("ordernumItem");        
        p_plannerId=pageContext.getParameter("plannerItem");        
        p_legacyOrd=pageContext.getParameter("legacyorderItem");
        p_lineNum=pageContext.getParameter("lineItem");
        p_poNum=pageContext.getParameter("custponumItem");
        p_orgName=pageContext.getParameter("operatinunitItem");
        p_operatingUnit = pageContext.getParameter("orgIdFV");
        
        
        if(p_orgName==null || p_orgName.equals(""))
        {
            p_operatingUnit=null;
        }
        
        HashMap hmap=new HashMap();
        hmap.put("p1",p_opn);
        hmap.put("p2",p_corpcode);
        hmap.put("p3",p_cust5code);
        hmap.put("p4",p_pal);
        hmap.put("p5",p_ordNum);
        hmap.put("p7",p_package);  
        hmap.put("p8",respAccess);
        hmap.put("plan_id",p_plannerId);
        hmap.put("legacy_order",p_legacyOrd);
        hmap.put("line_number",p_lineNum);
        hmap.put("cust_po_num",p_poNum);
        hmap.put("org_name",p_orgName);  //Added on 30-jan-18 for fixing defect 991
        hmap.put("p6",p_operatingUnit);  //OrgId
        
       //Calling Sales Order workbench oracle standard form when click on Order Number.
      /*  if(eventName.equals("SalesOrder")) 
        {
            log("In Sales Order Workbench validation",pageContext);
            String AppShortName =pageContext.getApplicationShortName(); //="ONT";
            String respKey = am.RespKey();  //"151 _OM_USER"; 
            //form:ONT:151 _OM_USER:STANDARD:ONT_OEXOEORD:DESKTOP_HEADER_ID={@HeaderId}
            String p_hdrId=pageContext.getParameter("HEADER_ID");
                        
            log("AppShortName , RespKey, HeaderId ---->"+AppShortName+","+respKey+","+p_hdrId, pageContext);
            String destination="form:"+AppShortName+":"+respKey+":STANDARD:ONT_OEXOEORD:DESKTOP_HEADER_ID="+p_hdrId+"";  //{@p_hdrId}
            pageContext.forwardImmediatelyToForm(destination);
        }*/
        
       //GO Button validation method..
       if ("SEARCH".equals(pageContext.getParameter(EVENT_PARAM)))
       {
           log("XXON PFR Search Button Validation", pageContext);
           log("OrgId @PFR--->"+p_operatingUnit,pageContext); 
           log("OrgName @PFR--->"+p_orgName,pageContext); 
           am.searchOrders(p_opn, p_corpcode, p_cust5code, p_pal, p_ordNum,p_operatingUnit, p_package, respAccess,p_plannerId,p_legacyOrd,p_lineNum,p_poNum);           
       }
      //Below code for No. of Rows PER Page event fire....
       else if ("DisplayRows".equals(pageContext.getParameter(EVENT_PARAM)))
        {
            p_rowsField = pageContext.getParameter("rownumItem");
            if (p_rowsField != null) 
            {
                pageContext.putSessionValue("ROWS", p_rowsField);
            }
            utils.displayRecords(pageContext, webBean, "resultsAdvRN");
        }
      //Main Page Cancel button validation method.
        else if ("cancel".equals(pageContext.getParameter(EVENT_PARAM)))
        {
            log("XXON PFR Cancel Button Validation", pageContext);
            pageContext.forwardImmediately("OA.jsp?OAFunc=OAHOMEPAGE", null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, null, false, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
        //Approve/Update button validation method..
        else if ("approve".equals(pageContext.getParameter(EVENT_PARAM)))
        {               
                        boolean forwardPage = Boolean.TRUE;
                        if(respAccess != null && (respAccess.equalsIgnoreCase("S") ||respAccess.equalsIgnoreCase("P"))) 
                        {
                            P_Status = "APPROVED";    
                            //am.passingPlannerDataToObject(Ord_Status); 
                            OADialogPage dialogPage2 = null;
                            String newSSDViolation =  (String)am.custShutDownPeriod(P_Status,pageContext); 
                            if(newSSDViolation!=null && newSSDViolation.length() > 0) 
                            {                                
                                dialogPage2 = new OADialogPage(OAException.WARNING,new OAException(newSSDViolation),null,"","");
                                dialogPage2.setReuseMenu(false); 
                                String yes = pageContext.getMessage("AK", "FWK_TBX_T_YES", null);
                                String no = pageContext.getMessage("AK", "FWK_TBX_T_NO", null);
                                dialogPage2.setOkButtonItemName("newSsdYesButton");
                                dialogPage2.setNoButtonItemName("newSsdNoButton");
                                dialogPage2.setOkButtonToPost(true);
                                dialogPage2.setNoButtonToPost(true); 
                                dialogPage2.setPostToCallingPage(true);
                                dialogPage2.setOkButtonLabel(yes); 
                                dialogPage2.setNoButtonLabel(no);
                                
                                java.util.Hashtable formParams2 = new java.util.Hashtable(1); 
                                formParams2.put("p_opn", p_opn); 
                                formParams2.put("p_corpcode", p_corpcode);
                                formParams2.put("p_cust5code", p_cust5code);
                                formParams2.put("p_package", p_package);                                
                                formParams2.put("p_pal", p_pal);
                                formParams2.put("p_ordNum", p_ordNum);                               
                                formParams2.put("p_legacyOrd", p_legacyOrd);
                                formParams2.put("p_lineNum", p_lineNum);
                                formParams2.put("P_Status",P_Status);
                                formParams2.put("p_ExecuteSearch","Y");
                                formParams2.put("p_poNum",p_poNum);
                                formParams2.put("p_orgName",p_orgName); 
                                formParams2.put("p_operatingUnit",p_operatingUnit);  
                                dialogPage2.setFormParameters(formParams2);  
                                forwardPage = Boolean.FALSE;
                                pageContext.redirectToDialogPage(dialogPage2);
                            }
                        } 
                        else 
                        {
                            log("XXON PFR Update Button Validation in Else block", pageContext);
                            P_Status = "APPROVAL_PENDING";
                           String qtyViolation =  (String)am.DataValidation(P_Status,hmap,pageContext);
                           log("qtyViolation @ PFR Update Button Validation in Else block ==>"+qtyViolation, pageContext);
                           OADialogPage dialogPage = null;
                           if(qtyViolation != null && qtyViolation.length() > 0)
                           {
                                 dialogPage = new OADialogPage(OAException.WARNING,new OAException(qtyViolation),null,"","");
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
                                 
                                 java.util.Hashtable formParams = new java.util.Hashtable(1); 
                                 if(p_opn!=null)formParams.put("p_opn", p_opn); 
                                 if(p_corpcode!=null)formParams.put("p_corpcode", p_corpcode);
                                 if(p_cust5code!=null)formParams.put("p_cust5code", p_cust5code);
                                 if(p_package!=null)formParams.put("p_package", p_package);
                                 if(p_pal!=null)formParams.put("p_pal", p_pal);
                                 if(p_ordNum!=null)formParams.put("p_ordNum", p_ordNum);                               
                                 if(p_legacyOrd!=null)formParams.put("p_legacyOrd", p_legacyOrd);
                                 if(p_lineNum!=null)formParams.put("p_lineNum", p_lineNum);
                                 if(P_Status!=null)formParams.put("P_Status",P_Status);
                                 formParams.put("p_ExecuteSearch","Y");
                                 if(p_poNum!=null)formParams.put("p_poNum",p_poNum);
                                 if(p_orgName!=null)formParams.put("p_orgName",p_orgName);  //Added on 2-may-2018 by raghu
                                 if(p_operatingUnit!=null)formParams.put("p_operatingUnit", p_operatingUnit);                               
                                 dialogPage.setFormParameters(formParams);  
                                 forwardPage = Boolean.FALSE;
                                 pageContext.redirectToDialogPage(dialogPage);
                           }                          
                         } 
                            if(forwardPage){
                                    hmap.put("p9","Y");
                                    pageContext.forwardImmediately("OA.jsp?OAFunc=XXON_OM_GBW_CHG_ORDER_F", null, 
                                                                    OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                                                    null, hmap, false, 
                                                                    OAWebBeanConstants.ADD_BREAD_CRUMB_NO);  
                            }
        }
        //MPQ Quantity dialog page's Yes/No button validation method...
        else if (pageContext.getParameter("SaveYesButton") != null) 
        {
            log("In Dialog Page's yes button validation method",pageContext);
            String Ord_Status=pageContext.getParameter("P_Status");
            am.passingCsrDataToObject(Ord_Status); 
            HashMap hm = new HashMap();
            hm.put("pageType", "SEARCH_PAGE"); 
            hm.put("s_opn",pageContext.getParameter("p_opn"));
            hm.put("s_corpcode", pageContext.getParameter("p_corpcode"));
            hm.put("s_cust5code",pageContext.getParameter("p_cust5code"));
            hm.put("s_package", pageContext.getParameter("p_package"));            
            hm.put("s_pal", pageContext.getParameter("p_pal"));
            hm.put("s_ordNum", pageContext.getParameter("p_ordNum"));
            hm.put("s_legacyOrd", pageContext.getParameter("p_legacyOrd"));
            hm.put("s_lineNum", pageContext.getParameter("p_lineNum"));
            hm.put("s_ExecuteSearch",pageContext.getParameter("p_ExecuteSearch"));
            hm.put("s_poNum",pageContext.getParameter("p_poNum"));
            hm.put("s_orgName",pageContext.getParameter("p_orgName"));  //added by raghu on 2-may-2018
            hm.put("s_operUnit",pageContext.getParameter("p_operatingUnit"));
            pageContext.setForwardURL("OA.jsp?OAFunc=XXON_OM_GBW_CHG_ORDER_F"
                                           ,null //not needed as we are retaining menu context
                                           ,OAWebBeanConstants.KEEP_MENU_CONTEXT
                                           ,null //not needed as we are retaining menu context
                                           ,hm //pass the hashMap that contains values for all of the parameters
                                           ,false //retain AM
                                           ,OAWebBeanConstants.ADD_BREAD_CRUMB_NO
                                           ,OAWebBeanConstants.IGNORE_MESSAGES);
        }
        else if (pageContext.getParameter("SaveNoButton") != null)
        {   
            hmap.put("p9","Y");
            pageContext.setForwardURL("OA.jsp?OAFunc=XXON_OM_GBW_CHG_ORDER_F"
                                            ,null //not needed as we are retaining menu context
                                            ,OAWebBeanConstants.KEEP_MENU_CONTEXT
                                            ,null //not needed as we are retaining menu context
                                            ,hmap //pass the hashMap that contains values for all of the parameters
                                            ,true //retain AM
                                            ,OAWebBeanConstants.ADD_BREAD_CRUMB_NO
                                            ,OAWebBeanConstants.IGNORE_MESSAGES); 
        }
        
        //newSSD Dialog page's Yes/No button's validation.
         else if (pageContext.getParameter("newSsdYesButton") != null) 
         {
             log("In newSSD Dialog Page's yes button validation method",pageContext);
             String Ord_Status=pageContext.getParameter("P_Status");
             am.passingPlannerDataToObject(Ord_Status); 
             HashMap hm = new HashMap();
             hm.put("pageType", "SEARCH_PAGE"); 
             hm.put("s_opn",pageContext.getParameter("p_opn"));
             hm.put("s_corpcode", pageContext.getParameter("p_corpcode"));
             hm.put("s_cust5code",pageContext.getParameter("p_cust5code"));
             hm.put("s_package", pageContext.getParameter("p_package"));             
             hm.put("s_pal", pageContext.getParameter("p_pal"));
             hm.put("s_ordNum", pageContext.getParameter("p_ordNum"));
             hm.put("s_legacyOrd", pageContext.getParameter("p_legacyOrd"));
             hm.put("s_lineNum", pageContext.getParameter("p_lineNum"));
             hm.put("s_ExecuteSearch",pageContext.getParameter("p_ExecuteSearch"));
             hm.put("s_poNum",pageContext.getParameter("p_poNum"));
             hm.put("s_orgName",pageContext.getParameter("p_orgName")); 
             hm.put("s_operUnit",pageContext.getParameter("p_operatingUnit"));
             pageContext.setForwardURL("OA.jsp?OAFunc=XXON_OM_GBW_CHG_ORDER_F"
                                            ,null //not needed as we are retaining menu context
                                            ,OAWebBeanConstants.KEEP_MENU_CONTEXT
                                            ,null //not needed as we are retaining menu context
                                            ,hm  // pass the hashMap that contains values for all of the parameters
                                            ,false //retain AM
                                            ,OAWebBeanConstants.ADD_BREAD_CRUMB_NO
                                            ,OAWebBeanConstants.IGNORE_MESSAGES);
         }
         else if (pageContext.getParameter("newSsdNoButton") != null)
         {   
             hmap.put("p9","Y");
             pageContext.setForwardURL("OA.jsp?OAFunc=XXON_OM_GBW_CHG_ORDER_F"
                                             ,null //not needed as we are retaining menu context
                                             ,OAWebBeanConstants.KEEP_MENU_CONTEXT
                                             ,null //not needed as we are retaining menu context
                                             ,hmap //pass the hashMap that contains values for all of the parameters
                                             ,true //retain AM
                                             ,OAWebBeanConstants.ADD_BREAD_CRUMB_NO
                                             ,OAWebBeanConstants.IGNORE_MESSAGES); 
         }
        
        //Reject button validation method.
        else if ("reject".equals(pageContext.getParameter(EVENT_PARAM)))
        {            
                P_Status = "REJECTED";
                pageContext.writeDiagnostics(this,"XXON PFR Reject Button Validation",1);
                am.passingPlannerDataToObject(P_Status);
                hmap.put("p9","Y");
                pageContext.forwardImmediately("OA.jsp?OAFunc=XXON_OM_GBW_CHG_ORDER_F", null, 
                                               OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                               null, hmap, false, 
                                               OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
        //Cancel change order request button validation method...
        else if ("CANCELCR".equals(pageContext.getParameter(EVENT_PARAM)))
        {
            P_Status = "CANCELLED";
            pageContext.writeDiagnostics(this,"XXON PFR Cancel Button Validation",1);
            am.cancelChangeOrder(P_Status);
            hmap.put("p9","Y");
            pageContext.forwardImmediately("OA.jsp?OAFunc=XXON_OM_GBW_CHG_ORDER_F", null, 
                                           OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                           null, hmap, false, 
                                           OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
        }
        else if ("lovPrepare".equals(pageContext.getParameter(EVENT_PARAM))||("lovValidate".equals(pageContext.getParameter(EVENT_PARAM)))||("lovUpdate".equals(pageContext.getParameter(EVENT_PARAM))))
        {
            log("lov events-->",pageContext); 
        }
        else if ("sort".equals(pageContext.getParameter(EVENT_PARAM))||("goto".equals(pageContext.getParameter(EVENT_PARAM)))||("DisplayRows".equals(pageContext.getParameter(EVENT_PARAM))))
        {
            log("page level events-->",pageContext); 
        }
        else if (pageContext.getParameter("searchBtn") != null)
        {
            am.searchOrders(p_opn, p_corpcode, p_cust5code, p_pal, p_ordNum,p_operatingUnit, p_package, respAccess,p_plannerId,p_legacyOrd,p_lineNum,p_poNum);               
        } 
    }
    private void clearValues(OAPageContext pageContext,OAWebBean webBean)
    {
          StringBuffer jsString = new StringBuffer();
                      jsString.append( "function clearFunction()");
                      jsString.append("{");
                      jsString.append("if(document.getElementById('opnItem').value!==null)document.getElementById('opnItem').value='';" );
                      jsString.append("if(document.getElementById('corpCodeItem').value!==null)document.getElementById('corpCodeItem').value='';") ;
                      jsString.append("if(document.getElementById('cust5codeItem').value!==null)document.getElementById('cust5codeItem').value='';");
                      jsString.append("if(document.getElementById('palItem').value!==null)document.getElementById('palItem').value='';") ;
                      jsString.append("if(document.getElementById('ordernumItem').value!==null)document.getElementById('ordernumItem').value='';") ;                      
                      jsString.append("if(document.getElementById('operatinunitItem').value!==null)document.getElementById('operatinunitItem').value='';") ;
                      jsString.append("if(document.getElementById('pkgItem').value!==null)document.getElementById('pkgItem').value='';"); 
                      jsString.append("if(document.getElementById('legacyorderItem').value!==null)document.getElementById('legacyorderItem').value='';"); 
                      jsString.append("if(document.getElementById('custponumItem').value!==null)document.getElementById('custponumItem').value='';"); 
                      jsString.append("if(document.getElementById('lineItem').value!==null)document.getElementById('lineItem').value='';"); 
                      jsString.append("if(document.getElementById('plannerItem').value!==null)document.getElementById('plannerItem').value='';");
                      jsString.append("}" );
                     //log("jsString-->"+jsString.toString());
                      pageContext.putJavaScriptFunction("clearFunction",jsString.toString());
                      OAButtonBean clearButton2 = (OAButtonBean) webBean.findChildRecursive("clrBtn") ;
                      String javaSClear = "javascript:clearFunction();";
                      clearButton2.setOnClick(javaSClear);
      } 

    private void log(String Message, OAPageContext pageContext)
    {
        XXONGbwUtil utils = new XXONGbwUtil();
        utils.Log(Message, pageContext);
    }

}