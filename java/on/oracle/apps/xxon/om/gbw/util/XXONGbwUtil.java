package oracle.apps.xxon.om.gbw.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;
import oracle.apps.fnd.framework.server.OAViewRowImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.table.OAAdvancedTableBean;
import oracle.apps.fnd.wf.bes.BusinessEvent;

import oracle.jbo.AttributeDef;
import oracle.jbo.Row;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XXONGbwUtil {
    public XXONGbwUtil() {
    }
    
    /**
     * Below Method is to print the log messages
     *@param message for text 
    */
    public void log(String message,OADBTransaction dbTransaction)
    {
       System.out.println(message);
     if(dbTransaction.isLoggingEnabled(1))    
     {         
         dbTransaction.writeDiagnostics(this,message,1);
     }
    
    }
    /**
     * Below Method is to print the log messages
     *@param message for text 
    */
    public void Log(String message,OAPageContext pageContext)
    {
       System.out.println(message);      
     if(pageContext.isLoggingEnabled(1))    
     {         
         pageContext.writeDiagnostics(this,message,1);
     }
    
    }
    public void callBusinessEvent(OADBTransaction oadbTrx,String eventName,String eventKey,String eventData,HashMap eventParams)
    {
        log("Start of callBusinessEvent",oadbTrx);
        log("eventName-->"+eventName,oadbTrx);
        log("eventKey-->"+eventKey,oadbTrx);
        log("eventData-->"+eventData,oadbTrx);
        //StringBuffer eventDataJSON = new StringBuffer(50);

      try{
            BusinessEvent busEvent = new BusinessEvent(eventName, eventKey);
            
            //eventDataJSON.append("{");
             
            if(eventParams!=null && eventParams.size()>0){
                
                Iterator it = eventParams.entrySet().iterator();
                    //int i=0;
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();
                        log("key-->"+pair.getKey(),oadbTrx);
                        log("key value -->"+pair.getValue(),oadbTrx);
                        /*
                        if(i>0)
                         eventDataJSON.append(",'"+(String)pair.getKey()+"'");
                        else
                            eventDataJSON.append("'"+(String)pair.getKey()+"'");
                        i++;
                        eventDataJSON.append(":");
                        eventDataJSON.append("'"+(String)pair.getValue()+"'");
                        */
                        
                        if( ((String)pair.getKey()).equalsIgnoreCase("XXON_OM_SCH_LINE_ID")){
                            //busEvent.setIntProperty((String)pair.getKey(),((Integer)pair.getValue()).intValue()); 
                            busEvent.setLongProperty((String)pair.getKey(),new BigDecimal((String)pair.getValue()).longValue());
                            
                        }else{
                            busEvent.setStringProperty((String)pair.getKey(),(String)pair.getValue());  
                        }
                        
                        
                    }
                //eventDataJSON.append("}");
            }
            
            
          log("biz prop count -->"+busEvent.getPropertyCount(),oadbTrx);
         // busEvent.setData(eventDataJSON.toString());        
          
          busEvent.raise(oadbTrx.getJdbcConnection());  
          oadbTrx.commit();
      }catch (Exception exp)
      {
          throw new OAException("Business event Error::"+exp.getMessage());
      }
        
    }
    /**
    *  Get Current DB Date
    * @return
    */
    public static Timestamp getCurrentDate(OADBTransaction dbTransaction)
    {
        Connection connection= null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sqlString    = null;
        Timestamp currentDateTimeStamp = null;
        try{
            connection = dbTransaction.getJdbcConnection();     
            //sqlString = "SELECT MRP_CALENDAR.DATE_OFFSET("+orgId+",1,TO_DATE('"+dateOpened+"','YYYY-MM-DD'),"+daysToskip+") DYNAMIC_DATE FROM DUAL ";
            // sqlString = "SELECT SYSDATE current_date FROM DUAL ";
           sqlString ="SELECT   TO_TIMESTAMP (\n" + 
           "            TO_CHAR (SYSDATE, 'mm/dd/yyyy hh24:mi:ss.')\n" + 
           "            || TO_CHAR (TO_NUMBER (TO_CHAR (SYSTIMESTAMP, 'FF')) + 250),\n" + 
           "            'mm/dd/yyyy hh24:mi:ss.FF'\n" + 
           "         ) AS CURRENT_DATE\n" + 
           "  FROM   DUAL";
            //System.out.println("sqlString -- > "+sqlString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlString);
            if(resultSet != null){
                 while(resultSet.next())
                 {
                     currentDateTimeStamp = resultSet.getTimestamp("current_date");
                     //dynamicsqlDate = resultSet.getDate("DYNAMIC_DATE");
                     break;
                 }
                resultSet.close();
                statement.close();
            }
        }catch(Exception exception)
        {
        if(dbTransaction.isLoggingEnabled(1))
        {
            System.out.println("Error ---> "+exception.getMessage());
        }
        }
       // SimpleDateFormat sdfDate = new SimpleDateFormat("mm/dd/yyyy HH:mm:ss.SSS");
       //System.out.println("currentDateTimeStamp-->"+currentDateTimeStamp);
        return currentDateTimeStamp;
    }
    /**
    *  Get Current DB Date
    * @return
    */
    public String CurrentDate(OADBTransaction dbTransaction){
        Connection connection= null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sqlString    = null;
        Timestamp currentDateTimeStamp = null;
        try{
            connection = dbTransaction.getJdbcConnection();     
            //sqlString = "SELECT MRP_CALENDAR.DATE_OFFSET("+orgId+",1,TO_DATE('"+dateOpened+"','YYYY-MM-DD'),"+daysToskip+") DYNAMIC_DATE FROM DUAL ";
            // sqlString = "SELECT SYSDATE current_date FROM DUAL ";
           sqlString ="SELECT   TO_TIMESTAMP (\n" + 
           "            TO_CHAR (SYSDATE, 'dd-mm-yyyy hh24:mi:ss.')\n" + 
           "            || TO_CHAR (TO_NUMBER (TO_CHAR (SYSTIMESTAMP, 'FF')) + 250),\n" + 
           "            'dd-mm-yyyy hh24:mi:ss.FF'\n" + 
           "         )\n" + 
           "            AS CURRENT_DATE\n" + 
           "  FROM   DUAL";
            //System.out.println("sqlString -- > "+sqlString);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlString);
            if(resultSet != null){
                 while(resultSet.next()){
                     currentDateTimeStamp = resultSet.getTimestamp("current_date");
                     //dynamicsqlDate = resultSet.getDate("DYNAMIC_DATE");
                     break;
                 }
                resultSet.close();
                statement.close();
            }
        }catch(Exception exception)
        {
            System.out.println("Error ---> "+exception.getMessage());
        }
        DateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss.SSS");
        String outputString = outputFormat.format(currentDateTimeStamp);
        //System.out.println("outputString-->"+outputString);
        return outputString;
    }
   
    public static void recordsTobeDisplayed(OAPageContext pageContext, OAWebBean webBean,String regionName)
    {
            String rowCount = null;
            if(pageContext.getSessionValue("RecordsDisplayed")!=null)
            {
                rowCount=(String)pageContext.getSessionValue("RecordsDisplayed");
            }else{
                rowCount = pageContext.getParameter("rowsField");
                if(regionName.equalsIgnoreCase("auditHeaderAdvRN")) rowCount = pageContext.getParameter("rowsField1");
            }
            
              OAAdvancedTableBean tableBean = (OAAdvancedTableBean)webBean.findChildRecursive(regionName);
            
            if(rowCount != null && !rowCount.equalsIgnoreCase(""))
            {
                int rowsToBeDisplayed = Integer.valueOf(rowCount);
                if(rowsToBeDisplayed <=0)
                {
               // throw new OAException(" No.of Rows to be displayed should be greater than zero.",OAException.ERROR);
                    throw new OAException("XXON","XXON_EDI_RECORDS_DISPLAY_ERR",
                                                     null,//tokens,
                                                     OAException.ERROR,
                                                     null);
                }
                  tableBean.setNumberOfRowsDisplayed(rowsToBeDisplayed);
                
            }else{
                  tableBean.setNumberOfRowsDisplayed(20);
            }
      }
	public static void displayRecords(OAPageContext pageContext, OAWebBean webBean,String regionName)
    {
            String rowCount = pageContext.getParameter("rownumItem");
            OAAdvancedTableBean tableBean = (OAAdvancedTableBean)webBean.findChildRecursive(regionName);
            if(rowCount != null && !rowCount.equalsIgnoreCase(""))
            {
                int rowsToBeDisplayed = Integer.valueOf(rowCount);
                if(rowsToBeDisplayed <=0)
                {
               // throw new OAException(" No.of Rows to be displayed should be greater than zero.",OAException.ERROR);
                    throw new OAException("XXON","XXON_EDI_RECORDS_DISPLAY_ERR",
                                                     null,//tokens,
                                                     OAException.ERROR,
                                                     null);
                }
                tableBean.setNumberOfRowsDisplayed(rowsToBeDisplayed);
            }
            else
            {
                tableBean.setNumberOfRowsDisplayed(10);
            }
      }
      
    //Added by Raghu on 16-mar-2017...
     public static void DisplaySessionRecords(OAPageContext pageContext, OAWebBean webBean,String regionName,String rcount)
     {
             //String rowCount = pageContext.getParameter("rowsField");
             OAAdvancedTableBean tableBean = (OAAdvancedTableBean)webBean.findChildRecursive(regionName);
             if(rcount != null && !rcount.equalsIgnoreCase(""))
             {
                 int rowsToBeDisplayed = Integer.valueOf(rcount);
                 if(rowsToBeDisplayed <=0)
                 {
                // throw new OAException(" No.of Rows to be displayed should be greater than zero.",OAException.ERROR);
                     throw new OAException("XXON","XXON_EDI_RECORDS_DISPLAY_ERR",
                                                      null,//tokens,
                                                      OAException.ERROR,
                                                      null);
                 }
                 tableBean.setNumberOfRowsDisplayed(rowsToBeDisplayed);
             }else{
                 tableBean.setNumberOfRowsDisplayed(10);
             }
       }
	  
    public String respAccessLevel(OADBTransaction dbTransaction) 
          {
              String RespName=null;
              RespName=dbTransaction.getResponsibilityName();
              int respId=dbTransaction.getResponsibilityId(); 
              log("RespName-->"+RespName,dbTransaction);
              log("respId-->"+respId,dbTransaction);
              String Disable=null;
                  PreparedStatement preparedstatement = null;
                  ResultSet resultset = null;
            Connection conn =dbTransaction.getJdbcConnection();
              try
              {
                          
                  String query_String="SELECT xxon_om_gbw_utils_pkg.get_resp_access(:1,:2) FROM DUAL";
                  preparedstatement = conn.prepareStatement(query_String);
                  preparedstatement.setInt(1, respId);
                  preparedstatement.setString(2, RespName);
                  resultset = preparedstatement.executeQuery();
                  if(resultset.next())
                            {
                                 Disable =resultset.getString(1);
                               
                                resultset.close();
                                preparedstatement.close(); 
                            }
                
              }
              catch(Exception e) 
              {
                  e.printStackTrace();
                  throw new OAException("Preparedstatement Error-->"+e.getMessage(),OAException.ERROR);
              }

              return Disable;
          }   
    /**
       * Get Default OAF SysDate
      
     */
      public static oracle.jbo.domain.Date getDafaultOafDate(OADBTransaction dbTransaction){
         oracle.jbo.domain.Date currDate=dbTransaction.getCurrentDBDate();
         return currDate;
     }
    public void downloadCsvFile(OAPageContext pageContext, String view_inst_name, String file_name_without_ext, String max_size, String hidden_attrib_list[], String voFieldNames[])
    {  
        OAViewObject v = (OAViewObject)pageContext.getRootApplicationModule().findViewObject(view_inst_name);
        if(v == null)
        {
            throw new OAException("Could not find View object instance "+view_inst_name+" in root AM.");
        }
        if(v.getFetchedRowCount() == 0)
        {
            throw new OAException("There is no data to export.");
        }
        String file_name =null;
        if(file_name_without_ext != null && !"".equals(file_name_without_ext))
        {
            file_name = file_name_without_ext;
        }
        HttpServletResponse response = (HttpServletResponse)pageContext.getRenderingContext().getServletResponse();
        response.setContentType("application/text");
        response.setHeader("Content-Disposition", "attachment; filename="+file_name+".csv");
        ServletOutputStream pw = null;
        try
        {
            pw = response.getOutputStream();
            int j = 0;
            int k = 0;
            boolean bb = true;             
            if(max_size == null || "".equals(max_size))
            {
                k = Integer.parseInt(pageContext.getProfile("VO_MAX_FETCH_SIZE"));
                bb = false;
            } else
            if("MAX".equals(max_size))
            {
                bb = true;
            } else
            {
                k = Integer.parseInt(max_size);
                bb = false;
            }
            AttributeDef a[] = v.getAttributeDefs();
            StringBuffer cc = new StringBuffer();
            ArrayList exist_list = new ArrayList();
            for(int l = 0; l < a.length; l++)
            {
                boolean zx = true;
                if(hidden_attrib_list != null)
                {
                    for(int z = 0; z < hidden_attrib_list.length; z++)
                    {
                        if(a[l].getName().equals(hidden_attrib_list[z]))
                        {
                            zx = false;
                            exist_list.add(String.valueOf(a[l].getIndex()));                           
                        }
                    }

                }
            }

            for(int l = 0; l < voFieldNames.length; l++)
            {
                boolean zx = true;
                if(zx)
                {
                    cc.append("\""+voFieldNames[l]+"\"");
                    cc.append(",");
                }
            }

            String header_row = cc.toString();
            pw.println(header_row);
            OAViewRowImpl row = (OAViewRowImpl)v.first();
            do
            {
                if(row == null)
                {
                    break;
                }
                j++;
                StringBuffer b = new StringBuffer();
                for(int i = 0; i < v.getAttributeCount(); i++)
                {
                    boolean cv = true;
                    for(int u = 0; u < exist_list.size(); u++)
                    {
                        if(String.valueOf(i).equals(exist_list.get(u).toString()))
                        {
                            cv = false;
                        }
                    }

                    if(cv)
                    {
                        Object o = row.getAttribute(i);
                        if(o != null)
                        {
                            if(o.getClass().equals(Class.forName("oracle.jbo.domain.Date")))
                            {
                                oracle.jbo.domain.Date dt = (oracle.jbo.domain.Date)o;
                                java.sql.Date ts = dt.dateValue();
                                SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                                String convertedDateString = displayDateFormat.format(ts);
                                b.append("\"" + convertedDateString + "\"");
                            } else
                            {
                                b.append("\"" + o.toString() + "\"");
                            }
                        } else
                        {
                            b.append("\"\"");
                        }
                        b.append(",");
                    }
                }

                String final_row = b.toString();
                pw.println(final_row);
                if(!bb && j == k)
                {
                    break;
                }
                row = (OAViewRowImpl)v.next();
            } while(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new OAException("Unexpected Exception occurred.Exception Details :"+e.toString());
        }
        pageContext.setDocumentRendered(false);
        try
        {
            pw.flush();
            pw.close();
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
            throw new OAException("Unexpected Exception occurred.Exception Details :" + ioexception.toString());
        }         
    }/*End of  Export  method*/
    
     //new Export Button Validation...
      public  void writeXLSXFile(OAPageContext pageContext,String file_name_without_ext,String view_inst_name,String columnName[],String viewAttribute[]) throws IOException 
      {
          OAViewObjectImpl vo = (OAViewObjectImpl) pageContext.getRootApplicationModule().findViewObject(view_inst_name); 
            if(vo.getFetchedRowCount() == 0)
            {
                throw new OAException("There is no data to export.",OAException.ERROR);
            }
            String file_name=null;        
             if(file_name_without_ext != null && !"".equals(file_name_without_ext))
             {
                 file_name = file_name_without_ext;
             }else{
                 file_name="export";
             }
          String sheetName = "Exported_data";//name of sheet
          HttpServletResponse response = (HttpServletResponse) pageContext.getRenderingContext().getServletResponse();     
          // Set excel property
          response.setHeader("Header-Name", "Codecombination");
          response.setHeader("Content-Length", "100000");
          response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
          response.setHeader("Content-Disposition","attachment; filename="+file_name+".xlsx");
          response.setHeader("Cache-Control","max-age=0");
            oracle.jbo.domain.Number toNumberVlaue=null;
            String toStringVlaue=null;
          XSSFWorkbook wb = new XSSFWorkbook();
          XSSFSheet sheet = wb.createSheet(sheetName);
           // sheet.setColumnWidth(0, 8000);
            //Create Header 
              XSSFRow hdr_row=sheet.createRow(0);
                for(int i = 0; i < columnName.length; i++)
                {
                    XSSFCell  hdr_var= hdr_row.createCell(i);
                    hdr_var.setCellValue(""+ columnName[i] +"");
               }
             //Create Lines    
             int f=1;
          try
          {
              for(OAViewRowImpl row = (OAViewRowImpl) vo.first(); row != null;row = (OAViewRowImpl) vo.next())
              {
                 XSSFRow row1 = sheet.createRow(f);
                         for(int i = 0; i < viewAttribute.length; i++)
                         {                          
                             XSSFCell cell = row1.createCell(i);
                             Object o = row.getAttribute(viewAttribute[i]);
                            if(o != null)
                              {
                                  
                                 if(o.getClass().equals(Class.forName("oracle.jbo.domain.Date")))
                                  {
                                      oracle.jbo.domain.Date dt = (oracle.jbo.domain.Date)o;
                                      java.sql.Date ts = dt.dateValue();
                                      SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                                      String convertedDateString = displayDateFormat.format(ts);
                                      cell.setCellValue(convertedDateString);
                                     
                                 } else if(o.getClass().equals(Class.forName("oracle.jbo.domain.Number")))
                                 {
                                     
                                     toNumberVlaue=(oracle.jbo.domain.Number)row.getAttribute(viewAttribute[i]);
                                     
                                     if(toNumberVlaue!=null)
                                     {
                                       toStringVlaue=String.valueOf(toNumberVlaue);
                                     }
                                     cell.setCellValue(toStringVlaue);
                                 }else{
                                     cell.setCellValue((String)row.getAttribute(viewAttribute[i]));
                                 }
                              }
                         }
                  f=f+1;
                } //end of for loop.
          ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
          wb.write(outByteStream);
          byte[] outArray = outByteStream.toByteArray();
          response.setContentLength(outArray.length);
          ServletOutputStream outStream = response.getOutputStream();
          outStream.write(outArray);
          outStream.flush();
          outStream.close();
         }    
         catch(Exception e)
         {
           e.printStackTrace();
           throw new OAException("Unexpected Exception occurred.Exception Details :"+e.toString());
         }
        } //End of new Export button validation.
    public void showMaxFecthSizeWarningMsg(OADBTransaction oadb,String viewInstName)
    {
        OAViewObject vo = (OAViewObject)oadb.getRootApplicationModule().findViewObject(viewInstName);
        Row  row=vo.first();
        int count=0;
        while(row!=null)
        {
            count++;
            if(count==2)
            {
              //break;
            }
          row=vo.next();
        }
    
    }
}
