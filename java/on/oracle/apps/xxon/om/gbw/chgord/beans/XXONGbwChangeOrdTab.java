package oracle.apps.xxon.om.gbw.chgord.beans;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.jpub.runtime.MutableArray;

public class XXONGbwChangeOrdTab implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "APPS.XXON_OM_GBW_CHG_ORD_TAB";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final XXONGbwChangeOrdTab _XxonOmGbwChgOrdTabFactory = new XXONGbwChangeOrdTab();

  public static ORADataFactory getORADataFactory()
  { return _XxonOmGbwChgOrdTabFactory; }
  /* constructors */
  public XXONGbwChangeOrdTab()
  {
    this((XXONGbwChangeOrdObj[])null);
  }

  public XXONGbwChangeOrdTab(XXONGbwChangeOrdObj[] a)
  {
    _array = new MutableArray(2002, a, XXONGbwChangeOrdObj.getORADataFactory());
  }

  /* ORAData interface */
  public Datum toDatum(Connection c) throws SQLException
  {
    return _array.toDatum(c, _SQL_NAME);
  }

  /* ORADataFactory interface */
  public ORAData create(Datum d, int sqlType) throws SQLException
  {
    if (d == null) return null; 
    XXONGbwChangeOrdTab a = new XXONGbwChangeOrdTab();
    a._array = new MutableArray(2002, (ARRAY) d, XXONGbwChangeOrdObj.getORADataFactory());
    return a;
  }

  public int length() throws SQLException
  {
    return _array.length();
  }

  public int getBaseType() throws SQLException
  {
    return _array.getBaseType();
  }

  public String getBaseTypeName() throws SQLException
  {
    return _array.getBaseTypeName();
  }

  public ArrayDescriptor getDescriptor() throws SQLException
  {
    return _array.getDescriptor();
  }

  /* array accessor methods */
  public XXONGbwChangeOrdObj[] getArray() throws SQLException
  {
    return (XXONGbwChangeOrdObj[])_array.getObjectArray(
      new XXONGbwChangeOrdObj[_array.length()]);
  }

  public XXONGbwChangeOrdObj[] getArray(long index, int count) throws SQLException
  {
    return (XXONGbwChangeOrdObj[])_array.getObjectArray(index,
      new XXONGbwChangeOrdObj[_array.sliceLength(index, count)]);
  }

  public void setArray(XXONGbwChangeOrdObj[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(XXONGbwChangeOrdObj[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public XXONGbwChangeOrdObj getElement(long index) throws SQLException
  {
    return (XXONGbwChangeOrdObj) _array.getObjectElement(index);
  }

  public void setElement(XXONGbwChangeOrdObj a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
