/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package oracle.apps.xxon.om.gbw.shipord.webui;

import java.io.Serializable;

import java.text.SimpleDateFormat;

import java.util.Locale;

import java.util.concurrent.TimeUnit;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OAFormValueBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageCheckBoxBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageDateFieldBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.nav.OAButtonBean;
import oracle.apps.xxon.om.gbw.util.XXONGbwUtil;


/**
 * Controller for ...
 */
public class XXONGbwShippedOrdersCO extends OAControllerImpl
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
      am.invokeMethod("recDisInit");
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
      String action = pageContext.getParameter(EVENT_PARAM); 
      log("Action: "+action);
      
      OAMessageChoiceBean dateRangeBean = (OAMessageChoiceBean)webBean.findChildRecursive("DateRange");
      OAMessageLovInputBean opnBean = (OAMessageLovInputBean)webBean.findChildRecursive("OPN");
      OAMessageTextInputBean orderNumberBean = (OAMessageTextInputBean)webBean.findChildRecursive("OrderNumber");
      OAMessageTextInputBean palBean = (OAMessageTextInputBean)webBean.findChildRecursive("PAL");
      OAMessageLovInputBean coiBean = (OAMessageLovInputBean)webBean.findChildRecursive("COI");
      OAMessageDateFieldBean dateFromBean = (OAMessageDateFieldBean )webBean.findChildRecursive("DateFrom");
      OAMessageDateFieldBean dateToBean = (OAMessageDateFieldBean )webBean.findChildRecursive("DateTo");          
      OAMessageTextInputBean pickReferenceBean = (OAMessageTextInputBean)webBean.findChildRecursive("PickReference");
      OAMessageLovInputBean packageBean = (OAMessageLovInputBean)webBean.findChildRecursive("Package");
      OAMessageLovInputBean corpCodeBean = (OAMessageLovInputBean)webBean.findChildRecursive("CorpCode");
      OAMessageLovInputBean cust5CodeBean = (OAMessageLovInputBean)webBean.findChildRecursive("Cust5Code");
      OAMessageCheckBoxBean sampleOrderesBean = (OAMessageCheckBoxBean)webBean.findChildRecursive("SampleOrderes");
      OAMessageCheckBoxBean consumptionOrdersBean = (OAMessageCheckBoxBean)webBean.findChildRecursive("ConsumptionOrders");
      OAFormValueBean        orgId=(OAFormValueBean)webBean.findChildRecursive("OrgIdFV");
      
     
      if ("go".equals(action)) 
      {
      
          String dateRange = (String)dateRangeBean.getValue(pageContext);
          String opn = (String)opnBean.getValue(pageContext);        
          String orderNumber = (String)orderNumberBean.getValue(pageContext);                
          String pal = (String)palBean.getValue(pageContext);    
          String coi = (String)coiBean.getValue(pageContext);        
          String pickReference = (String)pickReferenceBean.getValue(pageContext);        
          String pkg = (String)packageBean.getValue(pageContext);
          String operatingUnit = (String)orgId.getValue(pageContext);
          String corpCode = (String)corpCodeBean.getValue(pageContext);
          String cust5Code = (String)cust5CodeBean.getValue(pageContext);
          String sampleOrderes = (String)sampleOrderesBean.getValue(pageContext);
          String consumptionOrders = (String)consumptionOrdersBean.getValue(pageContext);
          java.util.Date dateFromUnformated = (java.util.Date)dateFromBean.getValue(pageContext);
          java.util.Date dateToUnformated = (java.util.Date)dateToBean.getValue(pageContext);
          
         if((dateFromUnformated!=null&&dateToUnformated!=null)&&(dateRange!=null&&dateRange.trim().length()>0))
          {
              String s1=null;
              s1=pageContext.getMessage("XXON","XXON_OM_GBW_SHPORD_SEARCH_ERR1",null);
              throw new OAException(s1,OAException.ERROR);
             
          }
          if((dateRange!=null&&dateRange.trim().length()>0)&&(dateFromUnformated!=null||dateToUnformated!=null))
          {
              String s1=null;
              s1=pageContext.getMessage("XXON","XXON_OM_GBW_SHPORD_SEARCH_ERR1",null);
              throw new OAException(s1,OAException.ERROR);
          }
          
          if((dateFromUnformated!=null&&dateToUnformated!=null)||(dateRange!=null&&dateRange.trim().length()>0))
          {
            log("just to print");
          }else{
              String s1=null;
              s1=pageContext.getMessage("XXON","XXON_OM_GBW_SHPORD_SEARCH_ERR2",null);
              throw new OAException(s1,OAException.ERROR);
          }
          if(dateFromUnformated!=null&&dateToUnformated!=null)
          {
              if(dateFromUnformated.getTime()>dateToUnformated.getTime())
              {
                  String s1=am.getOADBTransaction().getMessage("XXON","XXON_OM_GBW_SHPRD_INVALID_DATE",null);
                   //s1="Enter valid Ship From Date and Ship To Date.";
                  throw new OAException(s1,OAException.ERROR);
              }
              long duration  = dateFromUnformated.getTime() - dateToUnformated.getTime();
              long Days = TimeUnit.MILLISECONDS.toDays(duration);
              log("Days-->"+Days);
              if(Math.abs(Days)>90)
              {
                  String s1=pageContext.getMessage("XXON","XXON_OM_GBW_SHPORD_DATE_RANGE",null);
                  throw new OAException(s1,OAException.ERROR);
                  
              }
          }
          if ((dateFromUnformated != null) && (dateToUnformated != null))
          {
                if (dateFromUnformated.after(dateToUnformated)) 
                {
                    String s1=pageContext.getMessage("XXON","XXON_OM_GBW_SHPORD_DATE_ERR",null);
                    throw new OAException(s1,OAException.ERROR);
                }
            }
              
           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
              
           String dateFrom = null;
           if (dateFromUnformated != null) { dateFrom = format.format(dateFromUnformated); }
                      
           String dateTo = null;
           if (dateToUnformated != null) { dateTo = format.format(dateToUnformated); }
              
              
              
           if ((dateRange == null) && (opn == null) && 
               (orderNumber == null) && (pal == null) && (coi == null) && 
               (pickReference == null) && (pkg == null) && 
               (operatingUnit == null) && (corpCode == null) && 
               (cust5Code == null) && (sampleOrderes.equals("N")) && 
               (consumptionOrders.equals("N")) && (dateFrom == null) && 
               (dateTo == null)) 
               {
                    String s1=pageContext.getMessage("XXON","XXON_OM_GBW_SEARCH_ERR",null);
                    OAException message = new OAException(s1, OAException.ERROR);
                    pageContext.putDialogMessage(message);
              }else {
               Serializable[] parameters = 
               { dateRange, opn, orderNumber, pal, coi, pickReference, 
                 pkg, operatingUnit, corpCode, cust5Code, sampleOrderes, 
                 consumptionOrders, dateFrom, dateTo };
               Class paramTypes[] = 
               { String.class, String.class, String.class, String.class, 
                 String.class, String.class, String.class, String.class, 
                 String.class, String.class, String.class, String.class, 
                 String.class, String.class };
               am.invokeMethod("executeSearch", parameters, paramTypes);
            }        
      }else if("cancel".equals(action)) {
          am.clearVOCaches(null, true);        
          pageContext.forwardImmediately(
                  "OA.jsp?OAFunc=OAHOMEPAGE",
                  null,
                  OAWebBeanConstants.KEEP_MENU_CONTEXT,
                  null,
                  null,
                  true, // Retain AM
                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO);                     
      }else if(action.equalsIgnoreCase("rowsField"))
      {
          String rowCount = pageContext.getParameter("rowsField");
          if(rowCount!=null)
          {
            pageContext.putSessionValue("RecordsDisplayed",rowCount);
          }
          XXONGbwUtil utils= new XXONGbwUtil();
          utils.recordsTobeDisplayed(pageContext,webBean,"ResultAdvTabRN");
      }else if("export".equals(action)) {   
                try{
                    String columnName[]    = new String[19];
                    String viewAttribute[] = new String[19];
                       
                        columnName[0] = "Order Number";
                        columnName[1] = "Line#";
                        columnName[2] = "Order Type";                        
                        columnName[3] = "OPN";
                        columnName[4] = "PAL";
                        columnName[5] = "Pkg Group Code";
                        columnName[6] = "Product Category";
                        columnName[7] = "Corp Code";
                        columnName[8] = "Cust5 Code";
                        columnName[9] = "End Corp";
                        columnName[10] = "RSD";
                        columnName[11] = "SSD";
                        columnName[12] = "Quantity";
                        columnName[13] = "Pick Reference";
                        columnName[14] = "Pick Release Date";
                        columnName[15] = "Ship Confirm Date";
                        columnName[16] = "Invoice Number";
                        columnName[17] = "Operating Unit";
                        columnName[18] = "Way Bill Number";
                        
                        
                    viewAttribute[0] = "OrderNumber";
                    viewAttribute[1] = "LineNumber";
                    viewAttribute[2] = "OrderType";                        
                    viewAttribute[3] = "Opn";
                    viewAttribute[4] = "Pal";
                    viewAttribute[5] = "PackageDesc";
                    viewAttribute[6] = "ProductCategory";
                    viewAttribute[7] = "CorpCode";
                    viewAttribute[8] = "Cust5Code";
                    viewAttribute[9] = "Coi";
                    viewAttribute[10] = "Rsd";
                    viewAttribute[11] = "Ssd";
                    viewAttribute[12] = "Quantity";
                    viewAttribute[13] = "PickReference";
                    viewAttribute[14] = "PickReleaseDate";
                    viewAttribute[15] = "ShipConfirmDate";
                    viewAttribute[16] = "InvoiceNumber";
                    viewAttribute[17] = "OperatingUnit";
                    viewAttribute[18] = "WayBillNumber";
                    
                      /* Call the method to export data */
                       XXONGbwUtil csvFile = new XXONGbwUtil();
                       csvFile.writeXLSXFile(pageContext, 
                                              "Shipped_Orders_Data",      // file name as you wish but without extension
                                               "XXONGbwShippedOrdersVO1",      // view instance name
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
                jsString.append("if(document.getElementById('DateRange').value!==null)document.getElementById('DateRange').selectedIndex=0;" );
                jsString.append("if(document.getElementById('OPN').value!==null)document.getElementById('OPN').value='';") ;
                jsString.append("if(document.getElementById('OrderNumber').value!==null)document.getElementById('OrderNumber').value='';");
                jsString.append("if(document.getElementById('PAL').value!==null)document.getElementById('PAL').value='';") ;
                jsString.append("if(document.getElementById('COI').value!==null)document.getElementById('COI').value='';") ;
                jsString.append("if(document.getElementById('DateFrom').value!==null)document.getElementById('DateFrom').value='';") ;
                jsString.append("if(document.getElementById('DateTo').value!==null)document.getElementById('DateTo').value='';") ;
                jsString.append("if(document.getElementById('PickReference').value!==null)document.getElementById('PickReference').value='';");
                jsString.append("if(document.getElementById('Package').value!==null)document.getElementById('Package').value='';") ;
                jsString.append("if(document.getElementById('OperatingUnit').value!==null)document.getElementById('OperatingUnit').value='';") ;
                jsString.append("if(document.getElementById('CorpCode').value!==null)document.getElementById('CorpCode').value='';");
                jsString.append("if(document.getElementById('Cust5Code').value!==null)document.getElementById('Cust5Code').value='';") ;
                jsString.append("if(document.getElementById('OrgIdFV').value!==null)document.getElementById('OrgIdFV').value='';") ;
                jsString.append("document.getElementById('SampleOrderes').checked=false;") ;
                jsString.append("document.getElementById('ConsumptionOrders').checked=false;") ;                
                jsString.append("}" );
               log("jsString-->"+jsString.toString());
                pageContext.putJavaScriptFunction("clearFunction",jsString.toString());
                OAButtonBean clearButton2 = (OAButtonBean) webBean.findChildRecursive("ClearBtn") ;
                String javaSClear = "javascript:clearFunction();";
                clearButton2.setOnClick(javaSClear);
      }
  
}
