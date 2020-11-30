package oracle.apps.xxon.om.gbw.baklogview.beans;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.jpub.runtime.MutableArray;

public class XXONGbwRescheFrTab implements ORAData, ORADataFactory
{
  public static final String _SQL_NAME = "APPS.XXON_OM_GBW_RESCHE_FR_TAB";
  public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

  MutableArray _array;

private static final XXONGbwRescheFrTab _XXONGbwRescheFrTabFactory = new XXONGbwRescheFrTab();

  public static ORADataFactory getORADataFactory()
  { return _XXONGbwRescheFrTabFactory; }
  /* constructors */
  public XXONGbwRescheFrTab()
  {
    this((XXONGbwRescheFrObj[])null);
  }

  public XXONGbwRescheFrTab(XXONGbwRescheFrObj[] a)
  {
    _array = new MutableArray(2002, a, XXONGbwRescheFrObj.getORADataFactory());
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
    XXONGbwRescheFrTab a = new XXONGbwRescheFrTab();
    a._array = new MutableArray(2002, (ARRAY) d, XXONGbwRescheFrObj.getORADataFactory());
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
  public XXONGbwRescheFrObj[] getArray() throws SQLException
  {
    return (XXONGbwRescheFrObj[]) _array.getObjectArray(
      new XXONGbwRescheFrObj[_array.length()]);
  }

  public XXONGbwRescheFrObj[] getArray(long index, int count) throws SQLException
  {
    return (XXONGbwRescheFrObj[]) _array.getObjectArray(index,
      new XXONGbwRescheFrObj[_array.sliceLength(index, count)]);
  }

  public void setArray(XXONGbwRescheFrObj[] a) throws SQLException
  {
    _array.setObjectArray(a);
  }

  public void setArray(XXONGbwRescheFrObj[] a, long index) throws SQLException
  {
    _array.setObjectArray(a, index);
  }

  public XXONGbwRescheFrObj getElement(long index) throws SQLException
  {
    return (XXONGbwRescheFrObj) _array.getObjectElement(index);
  }

  public void setElement(XXONGbwRescheFrObj a, long index) throws SQLException
  {
    _array.setObjectElement(a, index);
  }

}
