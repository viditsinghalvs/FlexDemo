package oracle.apps.xxon.om.gbw.baklogview.beans;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class XXONGbwRescheFrObjRef implements ORAData, ORADataFactory
{
  public static final String _SQL_BASETYPE = "APPS.XXON_OM_GBW_RESCHE_FR_OBJ";
  public static final int _SQL_TYPECODE = OracleTypes.REF;

  REF _ref;

private static final XXONGbwRescheFrObjRef _XxonOmGbwRescheFrObjRefFactory = new XXONGbwRescheFrObjRef();

  public static ORADataFactory getORADataFactory()
  { return _XxonOmGbwRescheFrObjRefFactory; }
  /* constructor */
  public XXONGbwRescheFrObjRef()
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
    XXONGbwRescheFrObjRef r = new XXONGbwRescheFrObjRef();
    r._ref = (REF) d;
    return r;
  }

  public static XXONGbwRescheFrObjRef cast(ORAData o) throws SQLException
  {
     if (o == null) return null;
     try { return (XXONGbwRescheFrObjRef) getORADataFactory().create(o.toDatum(null), OracleTypes.REF); }
     catch (Exception exn)
     { throw new SQLException("Unable to convert "+o.getClass().getName()+" to XxonOmGbwRescheFrObjRef: "+exn.toString()); }
  }

  public XXONGbwRescheFrObj getValue() throws SQLException
  {
     return (XXONGbwRescheFrObj)XXONGbwRescheFrObj.getORADataFactory().create(
       _ref.getSTRUCT(), OracleTypes.REF);
  }

  public void setValue(XXONGbwRescheFrObj c) throws SQLException
  {
    _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
  }
}
