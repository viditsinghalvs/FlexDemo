package oracle.apps.xxon.om.gbw.baklogview.beans;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class XXONGbwRescheFrObj implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "APPS.XXON_OM_GBW_RESCHE_FR_OBJ";
  public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

  protected MutableStruct _struct;

  protected static int[] _sqlType =  { 2,2,2,12,91,91,12,12,2,2,2,2,2,2,12,12 };
  protected static ORADataFactory[] _factory = new ORADataFactory[16];
  protected static final XXONGbwRescheFrObj _XxonOmGbwRescheFrObjFactory = new XXONGbwRescheFrObj();

  public static ORADataFactory getORADataFactory()
  { return _XxonOmGbwRescheFrObjFactory; }
  /* constructors */
  protected void _init_struct(boolean init)
  { if (init) _struct = new MutableStruct(new Object[16], _sqlType, _factory); }
  public XXONGbwRescheFrObj()
  { _init_struct(true); }
  public XXONGbwRescheFrObj(java.math.BigDecimal headerId, java.math.BigDecimal lineId, java.math.BigDecimal reservtnId, String orderNumber, java.sql.Timestamp rsd, java.sql.Timestamp ssd, String changeReason, String demandType, java.math.BigDecimal splitBy, java.math.BigDecimal orderedQuantity, java.math.BigDecimal splitFromLineId, java.math.BigDecimal inventoryItemId, java.math.BigDecimal createdBy, java.math.BigDecimal lastUpdateLogin, String accessLevel, String rushOrderFlag) throws SQLException
  { _init_struct(true);
    setHeaderId(headerId);
    setLineId(lineId);
    setReservtnId(reservtnId);
    setOrderNumber(orderNumber);
    setRsd(rsd);
    setSsd(ssd);
    setChangeReason(changeReason);
    setDemandType(demandType);
    setSplitBy(splitBy);
    setOrderedQuantity(orderedQuantity);
    setSplitFromLineId(splitFromLineId);
    setInventoryItemId(inventoryItemId);
    setCreatedBy(createdBy);
    setLastUpdateLogin(lastUpdateLogin);
    setAccessLevel(accessLevel);
    setRushOrderFlag(rushOrderFlag);
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _struct.toDatum(c, _SQL_NAME);
  }


  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  { return create(null, d, sqlType); }
  protected ORAData create(XXONGbwRescheFrObj o, Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    if (o == null) o = new XXONGbwRescheFrObj();
    o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
    return o;
  }
  /* accessor methods */
  public java.math.BigDecimal getHeaderId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(0); }

  public void setHeaderId(java.math.BigDecimal headerId) throws SQLException
  { _struct.setAttribute(0, headerId); }


  public java.math.BigDecimal getLineId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(1); }

  public void setLineId(java.math.BigDecimal lineId) throws SQLException
  { _struct.setAttribute(1, lineId); }


  public java.math.BigDecimal getReservtnId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(2); }

  public void setReservtnId(java.math.BigDecimal reservtnId) throws SQLException
  { _struct.setAttribute(2, reservtnId); }


  public String getOrderNumber() throws SQLException
  { return (String) _struct.getAttribute(3); }

  public void setOrderNumber(String orderNumber) throws SQLException
  { _struct.setAttribute(3, orderNumber); }


  public java.sql.Timestamp getRsd() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(4); }

  public void setRsd(java.sql.Timestamp rsd) throws SQLException
  { _struct.setAttribute(4, rsd); }


  public java.sql.Timestamp getSsd() throws SQLException
  { return (java.sql.Timestamp) _struct.getAttribute(5); }

  public void setSsd(java.sql.Timestamp ssd) throws SQLException
  { _struct.setAttribute(5, ssd); }


  public String getChangeReason() throws SQLException
  { return (String) _struct.getAttribute(6); }

  public void setChangeReason(String changeReason) throws SQLException
  { _struct.setAttribute(6, changeReason); }


  public String getDemandType() throws SQLException
  { return (String) _struct.getAttribute(7); }

  public void setDemandType(String demandType) throws SQLException
  { _struct.setAttribute(7, demandType); }


  public java.math.BigDecimal getSplitBy() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(8); }

  public void setSplitBy(java.math.BigDecimal splitBy) throws SQLException
  { _struct.setAttribute(8, splitBy); }


  public java.math.BigDecimal getOrderedQuantity() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(9); }

  public void setOrderedQuantity(java.math.BigDecimal orderedQuantity) throws SQLException
  { _struct.setAttribute(9, orderedQuantity); }


  public java.math.BigDecimal getSplitFromLineId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(10); }

  public void setSplitFromLineId(java.math.BigDecimal splitFromLineId) throws SQLException
  { _struct.setAttribute(10, splitFromLineId); }


  public java.math.BigDecimal getInventoryItemId() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(11); }

  public void setInventoryItemId(java.math.BigDecimal inventoryItemId) throws SQLException
  { _struct.setAttribute(11, inventoryItemId); }


  public java.math.BigDecimal getCreatedBy() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(12); }

  public void setCreatedBy(java.math.BigDecimal createdBy) throws SQLException
  { _struct.setAttribute(12, createdBy); }


  public java.math.BigDecimal getLastUpdateLogin() throws SQLException
  { return (java.math.BigDecimal) _struct.getAttribute(13); }

  public void setLastUpdateLogin(java.math.BigDecimal lastUpdateLogin) throws SQLException
  { _struct.setAttribute(13, lastUpdateLogin); }


  public String getAccessLevel() throws SQLException
  { return (String) _struct.getAttribute(14); }

  public void setAccessLevel(String accessLevel) throws SQLException
  { _struct.setAttribute(14, accessLevel); }


  public String getRushOrderFlag() throws SQLException
  { return (String) _struct.getAttribute(15); }

  public void setRushOrderFlag(String rushOrderFlag) throws SQLException
  { _struct.setAttribute(15, rushOrderFlag); }

}
