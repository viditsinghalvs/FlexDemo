package oracle.apps.xxon.om.gbw.chgord.beans;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class XXONGbwChangeOrdObjRef implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "APPS.XXON_OM_GBW_CHG_ORD_OBJ";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final XXONGbwChangeOrdObjRef _XxonOmGbwChgOrdObjRefFactory = new XXONGbwChangeOrdObjRef();

  public static ORADataFactory getORADataFactory()
  { return _XxonOmGbwChgOrdObjRefFactory; }
  /* constructor */
  public XXONGbwChangeOrdObjRef()
  {
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _ref;
  }

  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    XXONGbwChangeOrdObjRef r = new XXONGbwChangeOrdObjRef();
    r._ref = (REF) d;
    return r;
  }

  public static XXONGbwChangeOrdObjRef cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (XXONGbwChangeOrdObjRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to XxonOmGbwChgOrdObjRef: "+exn.toString()); }
  }

  public XXONGbwChangeOrdObj getValue() throws SQLException
  {
     return (XXONGbwChangeOrdObj)XXONGbwChangeOrdObj.getORADataFactory().create(
       _ref.getSTRUCT(), OracleTypes.REF);
  }

  public void setValue(XXONGbwChangeOrdObj c) throws SQLException
  {
    _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
  }
}
