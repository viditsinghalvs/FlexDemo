package oracle.apps.xxon.om.gbw.chgord.beans;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class XXONGbwChangeOrdObj implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "APPS.XXON_OM_GBW_CHG_ORD_OBJ";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 12,2,2,2,2,91,91,2,12,2,12,12,12,12,12,12,12,12,12,2,2,12,2 };
  protected static ORADataFactory[] _factory = new ORADataFactory[23];
  protected static final XXONGbwChangeOrdObj _XxonOmGbwChgOrdObjFactory = new XXONGbwChangeOrdObj();

  public static ORADataFactory getORADataFactory()
  { return _XxonOmGbwChgOrdObjFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[23], _sqlType, _factory); }
  public XXONGbwChangeOrdObj()
  { _init_struct(true); }
  public XXONGbwChangeOrdObj(String pStatus, java.math.BigDecimal pOeheaderId, java.math.BigDecimal pOelineId, java.math.BigDecimal pStgheaderId, java.math.BigDecimal pStglineid, java.sql.Timestamp pRequestDate, java.sql.Timestamp pScheduleShipDate, java.math.BigDecimal pOrderedQuantity, String pChangetype, java.math.BigDecimal pOrgid, String pDsRuleName, String pOu, String pBusinessClass, String pCorpCode, String pSiteCode, String pPal2Code, String pItemNumber, String pOrderType, String pCancelReason, java.math.BigDecimal pQuantity, java.math.BigDecimal pOrdernumber, String pLinenumber, java.math.BigDecimal pMpq) throws SQLException
  { _init_struct(true);
    setPStatus(pStatus);
    setPOeheaderId(pOeheaderId);
    setPOelineId(pOelineId);
    setPStgheaderId(pStgheaderId);
    setPStglineid(pStglineid);
    setPRequestDate(pRequestDate);
    setPScheduleShipDate(pScheduleShipDate);
    setPOrderedQuantity(pOrderedQuantity);
    setPChangetype(pChangetype);
    setPOrgid(pOrgid);
    setPDsRuleName(pDsRuleName);
    setPOu(pOu);
    setPBusinessClass(pBusinessClass);
    setPCorpCode(pCorpCode);
    setPSiteCode(pSiteCode);
    setPPal2Code(pPal2Code);
    setPItemNumber(pItemNumber);
    setPOrderType(pOrderType);
    setPCancelReason(pCancelReason);
    setPQuantity(pQuantity);
    setPOrdernumber(pOrdernumber);
    setPLinenumber(pLinenumber);
    setPMpq(pMpq);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(XXONGbwChangeOrdObj o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new XXONGbwChangeOrdObj();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public String getPStatus() throws SQLException
  { return (String) _struct.getAttribute(0); }

  public void setPStatus(String pStatus) throws SQLException
  { _struct.setAttribute(0, pStatus); }


  public java.math.BigDecimal getPOeheaderId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(1); }

  public void setPOeheaderId(java.math.BigDecimal pOeheaderId) throws SQLException
  { _struct.setAttribute(1, pOeheaderId); }


  public java.math.BigDecimal getPOelineId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(2); }

  public void setPOelineId(java.math.BigDecimal pOelineId) throws SQLException
  { _struct.setAttribute(2, pOelineId); }


  public java.math.BigDecimal getPStgheaderId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(3); }

  public void setPStgheaderId(java.math.BigDecimal pStgheaderId) throws SQLException
  { _struct.setAttribute(3, pStgheaderId); }


  public java.math.BigDecimal getPStglineid() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(4); }

  public void setPStglineid(java.math.BigDecimal pStglineid) throws SQLException
  { _struct.setAttribute(4, pStglineid); }


  public java.sql.Timestamp getPRequestDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setPRequestDate(java.sql.Timestamp pRequestDate) throws SQLException
  { _struct.setAttribute(5, pRequestDate); }


  public java.sql.Timestamp getPScheduleShipDate() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(6); }

  public void setPScheduleShipDate(java.sql.Timestamp pScheduleShipDate) throws SQLException
  { _struct.setAttribute(6, pScheduleShipDate); }


  public java.math.BigDecimal getPOrderedQuantity() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(7); }

  public void setPOrderedQuantity(java.math.BigDecimal pOrderedQuantity) throws SQLException
  { _struct.setAttribute(7, pOrderedQuantity); }


  public String getPChangetype() throws SQLException
  { return (String) _struct.getAttribute(8); }

  public void setPChangetype(String pChangetype) throws SQLException
  { _struct.setAttribute(8, pChangetype); }


  public java.math.BigDecimal getPOrgid() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setPOrgid(java.math.BigDecimal pOrgid) throws SQLException
  { _struct.setAttribute(9, pOrgid); }


  public String getPDsRuleName() throws SQLException
  { return (String) _struct.getAttribute(10); }

  public void setPDsRuleName(String pDsRuleName) throws SQLException
  { _struct.setAttribute(10, pDsRuleName); }


  public String getPOu() throws SQLException
  { return (String) _struct.getAttribute(11); }

  public void setPOu(String pOu) throws SQLException
  { _struct.setAttribute(11, pOu); }


  public String getPBusinessClass() throws SQLException
  { return (String) _struct.getAttribute(12); }

  public void setPBusinessClass(String pBusinessClass) throws SQLException
  { _struct.setAttribute(12, pBusinessClass); }


  public String getPCorpCode() throws SQLException
  { return (String) _struct.getAttribute(13); }

  public void setPCorpCode(String pCorpCode) throws SQLException
  { _struct.setAttribute(13, pCorpCode); }


  public String getPSiteCode() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setPSiteCode(String pSiteCode) throws SQLException
  { _struct.setAttribute(14, pSiteCode); }


  public String getPPal2Code() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setPPal2Code(String pPal2Code) throws SQLException
  { _struct.setAttribute(15, pPal2Code); }


  public String getPItemNumber() throws SQLException
  { return (String) _struct.getAttribute(16); }

  public void setPItemNumber(String pItemNumber) throws SQLException
  { _struct.setAttribute(16, pItemNumber); }


  public String getPOrderType() throws SQLException
  { return (String) _struct.getAttribute(17); }

  public void setPOrderType(String pOrderType) throws SQLException
  { _struct.setAttribute(17, pOrderType); }


  public String getPCancelReason() throws SQLException
  { return (String) _struct.getAttribute(18); }

  public void setPCancelReason(String pCancelReason) throws SQLException
  { _struct.setAttribute(18, pCancelReason); }


  public java.math.BigDecimal getPQuantity() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(19); }

  public void setPQuantity(java.math.BigDecimal pQuantity) throws SQLException
  { _struct.setAttribute(19, pQuantity); }


  public java.math.BigDecimal getPOrdernumber() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(20); }

  public void setPOrdernumber(java.math.BigDecimal pOrdernumber) throws SQLException
  { _struct.setAttribute(20, pOrdernumber); }


  public String getPLinenumber() throws SQLException
  { return (String) _struct.getAttribute(21); }

  public void setPLinenumber(String pLinenumber) throws SQLException
  { _struct.setAttribute(21, pLinenumber); }


  public java.math.BigDecimal getPMpq() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(22); }

  public void setPMpq(java.math.BigDecimal pMpq) throws SQLException
  { _struct.setAttribute(22, pMpq); }

}
