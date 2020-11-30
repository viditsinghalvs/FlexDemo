package oracle.apps.xxon.om.gbw.baklogview.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XXONGbwBaklogViewResultVORowImpl extends OAViewRowImpl {
    public static final int OPN = 0;
    public static final int CUSTOMERCODE = 1;
    public static final int CUST5CODE = 2;
    public static final int ORDERHOLD = 3;
    public static final int ORDERTYPE = 4;
    public static final int ORDERNUMBER = 5;
    public static final int LINENUMBER = 6;
    public static final int RSD = 7;
    public static final int SSD = 8;
    public static final int RESCHEDULEREASON = 9;
    public static final int QUANTITY = 10;
    public static final int CUMULATIVEQUANTITY = 11;
    public static final int PEGGINGDETAIL = 12;
    public static final int PRODUCTCATEGORY = 13;
    public static final int SCHEDULINGRETURN = 14;
    public static final int MPDATE = 15;
    public static final int CSR = 16;
    public static final int SPLITFLAG = 17;
    public static final int ITEMPLANNER = 18;
    public static final int RSDDB = 19;
    public static final int SSDDB = 20;
    public static final int QUANTITYDB = 21;
    public static final int COI = 22;
    public static final int PKG = 23;
    public static final int PAL = 24;
    public static final int OPERATINGUNIT = 25;
    public static final int DEMANDTYPE = 26;
    public static final int STATUS = 27;
    public static final int INVENTORYITEMID = 28;
    public static final int LINEID = 29;
    public static final int HEADERID = 30;
    public static final int MPN = 31;
    public static final int SELECT = 32;
    public static final int ORDERHOLDDISABLE = 33;
    public static final int RESERVTNID = 34;
    public static final int ORDERTYPECODE = 35;
    public static final int DEMANDBANK = 36;
    public static final int LEADTIME = 37;
    public static final int MAXQUANTITYLEADTIME = 38;
    public static final int MPQ = 39;
    public static final int ORGANIZATIONID = 40;
    public static final int LEGACYSO = 41;
    public static final int RUSHORDERFLAG = 42;
    public static final int RUSHORDERFLAGDB = 43;
    public static final int REASONCODE = 44;
    public static final int ISRENDERED = 45;
    public static final int SAD = 46;
    public static final int RD = 47;
    public static final int SADRDRENDER = 48;
    public static final int ISSCARENDER = 49;
    public static final int ISRUSHORDFLAGREADONLY = 50;
    public static final int LEGACYLINENUMBER = 51;
    public static final int SITEUSECODE = 52;
    public static final int SSDRESCEDULECOUNTER = 53;
    public static final int ISROFLAGRENDER = 54;
    public static final int ISROCBRENDER = 55;
    public static final int PONUMBER = 56;

    /**This is the default constructor (do not remove)
     */
    public XXONGbwBaklogViewResultVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute Mpn
     */
    public String getMpn() {
        return (String) getAttributeInternal(MPN);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Mpn
     */
    public void setMpn(String value) {
        setAttributeInternal(MPN, value);
    }

    /**Gets the attribute value for the calculated attribute CustomerCode
     */
    public String getCustomerCode() {
        return (String) getAttributeInternal(CUSTOMERCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CustomerCode
     */
    public void setCustomerCode(String value) {
        setAttributeInternal(CUSTOMERCODE, value);
    }

    /**Gets the attribute value for the calculated attribute OrderHold
     */
    public String getOrderHold() {
        return (String) getAttributeInternal(ORDERHOLD);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute OrderHold
     */
    public void setOrderHold(String value) {
        setAttributeInternal(ORDERHOLD, value);
    }

    /**Gets the attribute value for the calculated attribute OrderType
     */
    public String getOrderType() {
        return (String) getAttributeInternal(ORDERTYPE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute OrderType
     */
    public void setOrderType(String value) {
        setAttributeInternal(ORDERTYPE, value);
    }

    /**Gets the attribute value for the calculated attribute DemandType
     */
    public String getDemandType() {
        return (String) getAttributeInternal(DEMANDTYPE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute DemandType
     */
    public void setDemandType(String value) {
        setAttributeInternal(DEMANDTYPE, value);
    }

    /**Gets the attribute value for the calculated attribute OrderNumber
     */
    public String getOrderNumber() {
        return (String) getAttributeInternal(ORDERNUMBER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute OrderNumber
     */
    public void setOrderNumber(String value) {
        setAttributeInternal(ORDERNUMBER, value);
    }

    /**Gets the attribute value for the calculated attribute Rsd
     */
    public Date getRsd() {
        return (Date) getAttributeInternal(RSD);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Rsd
     */
    public void setRsd(Date value) {
        setAttributeInternal(RSD, value);
    }

    /**Gets the attribute value for the calculated attribute Ssd
     */
    public Date getSsd() {
        return (Date) getAttributeInternal(SSD);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Ssd
     */
    public void setSsd(Date value) {
        setAttributeInternal(SSD, value);
    }

    /**Gets the attribute value for the calculated attribute RescheduleReason
     */
    public String getRescheduleReason() {
        return (String) getAttributeInternal(RESCHEDULEREASON);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute RescheduleReason
     */
    public void setRescheduleReason(String value) {
        setAttributeInternal(RESCHEDULEREASON, value);
    }

    /**Gets the attribute value for the calculated attribute Quantity
     */
    public Number getQuantity() {
        return (Number) getAttributeInternal(QUANTITY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Quantity
     */
    public void setQuantity(Number value) {
        setAttributeInternal(QUANTITY, value);
    }

    /**Gets the attribute value for the calculated attribute PeggingDetail
     */
    public String getPeggingDetail() {
        return (String) getAttributeInternal(PEGGINGDETAIL);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PeggingDetail
     */
    public void setPeggingDetail(String value) {
        setAttributeInternal(PEGGINGDETAIL, value);
    }

    /**Gets the attribute value for the calculated attribute ProductCategory
     */
    public String getProductCategory() {
        return (String) getAttributeInternal(PRODUCTCATEGORY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ProductCategory
     */
    public void setProductCategory(String value) {
        setAttributeInternal(PRODUCTCATEGORY, value);
    }

    /**Gets the attribute value for the calculated attribute MpDate
     */
    public Date getMpDate() {
        return (Date) getAttributeInternal(MPDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute MpDate
     */
    public void setMpDate(Date value) {
        setAttributeInternal(MPDATE, value);
    }

    /**Gets the attribute value for the calculated attribute Opn
     */
    public String getOpn() {
        return (String) getAttributeInternal(OPN);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Opn
     */
    public void setOpn(String value) {
        setAttributeInternal(OPN, value);
    }

    /**Gets the attribute value for the calculated attribute Coi
     */
    public String getCoi() {
        return (String) getAttributeInternal(COI);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Coi
     */
    public void setCoi(String value) {
        setAttributeInternal(COI, value);
    }

    /**Gets the attribute value for the calculated attribute SchedulingReturn
     */
    public String getSchedulingReturn() {
        return (String) getAttributeInternal(SCHEDULINGRETURN);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute SchedulingReturn
     */
    public void setSchedulingReturn(String value) {
        setAttributeInternal(SCHEDULINGRETURN, value);
    }

    /**Gets the attribute value for the calculated attribute Csr
     */
    public String getCsr() {
        return (String) getAttributeInternal(CSR);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Csr
     */
    public void setCsr(String value) {
        setAttributeInternal(CSR, value);
    }

    /**Gets the attribute value for the calculated attribute SplitFlag
     */
    public String getSplitFlag() {
        return (String) getAttributeInternal(SPLITFLAG);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute SplitFlag
     */
    public void setSplitFlag(String value) {
        setAttributeInternal(SPLITFLAG, value);
    }

    /**Gets the attribute value for the calculated attribute Pkg
     */
    public String getPkg() {
        return (String) getAttributeInternal(PKG);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Pkg
     */
    public void setPkg(String value) {
        setAttributeInternal(PKG, value);
    }

    /**Gets the attribute value for the calculated attribute Pal
     */
    public String getPal() {
        return (String) getAttributeInternal(PAL);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Pal
     */
    public void setPal(String value) {
        setAttributeInternal(PAL, value);
    }

    /**Gets the attribute value for the calculated attribute OperatingUnit
     */
    public Number getOperatingUnit() {
        return (Number) getAttributeInternal(OPERATINGUNIT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute OperatingUnit
     */
    public void setOperatingUnit(Number value) {
        setAttributeInternal(OPERATINGUNIT, value);
    }

    /**Gets the attribute value for the calculated attribute Cust5Code
     */
    public String getCust5Code() {
        return (String) getAttributeInternal(CUST5CODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Cust5Code
     */
    public void setCust5Code(String value) {
        setAttributeInternal(CUST5CODE, value);
    }

    /**Gets the attribute value for the calculated attribute Status
     */
    public String getStatus() {
        return (String) getAttributeInternal(STATUS);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Status
     */
    public void setStatus(String value) {
        setAttributeInternal(STATUS, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case OPN:
            return getOpn();
        case CUSTOMERCODE:
            return getCustomerCode();
        case CUST5CODE:
            return getCust5Code();
        case ORDERHOLD:
            return getOrderHold();
        case ORDERTYPE:
            return getOrderType();
        case ORDERNUMBER:
            return getOrderNumber();
        case LINENUMBER:
            return getLineNumber();
        case RSD:
            return getRsd();
        case SSD:
            return getSsd();
        case RESCHEDULEREASON:
            return getRescheduleReason();
        case QUANTITY:
            return getQuantity();
        case CUMULATIVEQUANTITY:
            return getCumulativeQuantity();
        case PEGGINGDETAIL:
            return getPeggingDetail();
        case PRODUCTCATEGORY:
            return getProductCategory();
        case SCHEDULINGRETURN:
            return getSchedulingReturn();
        case MPDATE:
            return getMpDate();
        case CSR:
            return getCsr();
        case SPLITFLAG:
            return getSplitFlag();
        case ITEMPLANNER:
            return getItemPlanner();
        case RSDDB:
            return getRsdDb();
        case SSDDB:
            return getSsdDb();
        case QUANTITYDB:
            return getQuantityDb();
        case COI:
            return getCoi();
        case PKG:
            return getPkg();
        case PAL:
            return getPal();
        case OPERATINGUNIT:
            return getOperatingUnit();
        case DEMANDTYPE:
            return getDemandType();
        case STATUS:
            return getStatus();
        case INVENTORYITEMID:
            return getInventoryItemId();
        case LINEID:
            return getLineId();
        case HEADERID:
            return getHeaderId();
        case MPN:
            return getMpn();
        case SELECT:
            return getselect();
        case ORDERHOLDDISABLE:
            return getOrderHoldDisable();
        case RESERVTNID:
            return getReservtnId();
        case ORDERTYPECODE:
            return getOrderTypeCode();
        case DEMANDBANK:
            return getDemandBank();
        case LEADTIME:
            return getLeadTime();
        case MAXQUANTITYLEADTIME:
            return getMaxQuantityLeadTime();
        case MPQ:
            return getMpq();
        case ORGANIZATIONID:
            return getOrganizationId();
        case LEGACYSO:
            return getLegacySo();
        case RUSHORDERFLAG:
            return getRushOrderFlag();
        case RUSHORDERFLAGDB:
            return getRushOrderFlagDb();
        case REASONCODE:
            return getreasonCode();
        case ISRENDERED:
            return getisRendered();
        case SAD:
            return getSad();
        case RD:
            return getRd();
        case SADRDRENDER:
            return getSadRdRender();
        case ISSCARENDER:
            return getisScaRender();
        case ISRUSHORDFLAGREADONLY:
            return getisRushOrdFlagReadOnly();
        case LEGACYLINENUMBER:
            return getLegacyLineNumber();
        case SITEUSECODE:
            return getSiteUseCode();
        case SSDRESCEDULECOUNTER:
            return getSsdResceduleCounter();
        case ISROFLAGRENDER:
            return getisRoFlagRender();
        case ISROCBRENDER:
            return getisRoCbRender();
        case PONUMBER:
            return getPoNumber();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case OPN:
            setOpn((String)value);
            return;
        case CUSTOMERCODE:
            setCustomerCode((String)value);
            return;
        case CUST5CODE:
            setCust5Code((String)value);
            return;
        case ORDERHOLD:
            setOrderHold((String)value);
            return;
        case ORDERTYPE:
            setOrderType((String)value);
            return;
        case ORDERNUMBER:
            setOrderNumber((String)value);
            return;
        case LINENUMBER:
            setLineNumber((String)value);
            return;
        case RSD:
            setRsd((Date)value);
            return;
        case SSD:
            setSsd((Date)value);
            return;
        case RESCHEDULEREASON:
            setRescheduleReason((String)value);
            return;
        case QUANTITY:
            setQuantity((Number)value);
            return;
        case CUMULATIVEQUANTITY:
            setCumulativeQuantity((String)value);
            return;
        case PEGGINGDETAIL:
            setPeggingDetail((String)value);
            return;
        case PRODUCTCATEGORY:
            setProductCategory((String)value);
            return;
        case SCHEDULINGRETURN:
            setSchedulingReturn((String)value);
            return;
        case MPDATE:
            setMpDate((Date)value);
            return;
        case CSR:
            setCsr((String)value);
            return;
        case SPLITFLAG:
            setSplitFlag((String)value);
            return;
        case ITEMPLANNER:
            setItemPlanner((String)value);
            return;
        case RSDDB:
            setRsdDb((Date)value);
            return;
        case SSDDB:
            setSsdDb((Date)value);
            return;
        case QUANTITYDB:
            setQuantityDb((Number)value);
            return;
        case COI:
            setCoi((String)value);
            return;
        case PKG:
            setPkg((String)value);
            return;
        case PAL:
            setPal((String)value);
            return;
        case OPERATINGUNIT:
            setOperatingUnit((Number)value);
            return;
        case DEMANDTYPE:
            setDemandType((String)value);
            return;
        case STATUS:
            setStatus((String)value);
            return;
        case INVENTORYITEMID:
            setInventoryItemId((Number)value);
            return;
        case LINEID:
            setLineId((Number)value);
            return;
        case HEADERID:
            setHeaderId((Number)value);
            return;
        case MPN:
            setMpn((String)value);
            return;
        case SELECT:
            setselect((String)value);
            return;
        case ORDERHOLDDISABLE:
            setOrderHoldDisable((Boolean)value);
            return;
        case RESERVTNID:
            setReservtnId((Number)value);
            return;
        case ORDERTYPECODE:
            setOrderTypeCode((String)value);
            return;
        case DEMANDBANK:
            setDemandBank((String)value);
            return;
        case LEADTIME:
            setLeadTime((Number)value);
            return;
        case MAXQUANTITYLEADTIME:
            setMaxQuantityLeadTime((Number)value);
            return;
        case MPQ:
            setMpq((Number)value);
            return;
        case ORGANIZATIONID:
            setOrganizationId((Number)value);
            return;
        case LEGACYSO:
            setLegacySo((String)value);
            return;
        case RUSHORDERFLAG:
            setRushOrderFlag((String)value);
            return;
        case RUSHORDERFLAGDB:
            setRushOrderFlagDb((String)value);
            return;
        case REASONCODE:
            setreasonCode((String)value);
            return;
        case ISRENDERED:
            setisRendered((Boolean)value);
            return;
        case SAD:
            setSad((Date)value);
            return;
        case RD:
            setRd((Date)value);
            return;
        case SADRDRENDER:
            setSadRdRender((Boolean)value);
            return;
        case ISSCARENDER:
            setisScaRender((Boolean)value);
            return;
        case ISRUSHORDFLAGREADONLY:
            setisRushOrdFlagReadOnly((Boolean)value);
            return;
        case LEGACYLINENUMBER:
            setLegacyLineNumber((String)value);
            return;
        case SITEUSECODE:
            setSiteUseCode((String)value);
            return;
        case SSDRESCEDULECOUNTER:
            setSsdResceduleCounter((Number)value);
            return;
        case ISROFLAGRENDER:
            setisRoFlagRender((Boolean)value);
            return;
        case ISROCBRENDER:
            setisRoCbRender((Boolean)value);
            return;
        case PONUMBER:
            setPoNumber((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }


    /**Gets the attribute value for the calculated attribute ItemPlanner
     */
    public String getItemPlanner() {
        return (String) getAttributeInternal(ITEMPLANNER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ItemPlanner
     */
    public void setItemPlanner(String value) {
        setAttributeInternal(ITEMPLANNER, value);
    }

    /**Gets the attribute value for the calculated attribute InventoryItemId
     */
    public Number getInventoryItemId() {
        return (Number) getAttributeInternal(INVENTORYITEMID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute InventoryItemId
     */
    public void setInventoryItemId(Number value) {
        setAttributeInternal(INVENTORYITEMID, value);
    }

    /**Gets the attribute value for the calculated attribute LineId
     */
    public Number getLineId() {
        return (Number) getAttributeInternal(LINEID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LineId
     */
    public void setLineId(Number value) {
        setAttributeInternal(LINEID, value);
    }

    /**Gets the attribute value for the calculated attribute HeaderId
     */
    public Number getHeaderId() {
        return (Number) getAttributeInternal(HEADERID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute HeaderId
     */
    public void setHeaderId(Number value) {
        setAttributeInternal(HEADERID, value);
    }

    /**Gets the attribute value for the calculated attribute select
     */
    public String getselect() {
        return (String) getAttributeInternal(SELECT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute select
     */
    public void setselect(String value) {
        setAttributeInternal(SELECT, value);
    }


    /**Gets the attribute value for the calculated attribute ReservtnId
     */
    public Number getReservtnId() {
        return (Number) getAttributeInternal(RESERVTNID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ReservtnId
     */
    public void setReservtnId(Number value) {
        setAttributeInternal(RESERVTNID, value);
    }

    /**Gets the attribute value for the calculated attribute OrderHoldDisable
     */
    public Boolean getOrderHoldDisable() 
    {
      
       return (Boolean) getAttributeInternal(ORDERHOLDDISABLE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute OrderHoldDisable
     */
    public void setOrderHoldDisable(Boolean value) {
        setAttributeInternal(ORDERHOLDDISABLE, value);
    }

    /**Gets the attribute value for the calculated attribute QuantityDb
     */
    public Number getQuantityDb() {
        return (Number) getAttributeInternal(QUANTITYDB);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute QuantityDb
     */
    public void setQuantityDb(Number value) {
        setAttributeInternal(QUANTITYDB, value);
    }

    /**Gets the attribute value for the calculated attribute DemandBank
     */
    public String getDemandBank() {
        return (String) getAttributeInternal(DEMANDBANK);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute DemandBank
     */
    public void setDemandBank(String value) {
        setAttributeInternal(DEMANDBANK, value);
    }

    /**Gets the attribute value for the calculated attribute RsdDb
     */
    public Date getRsdDb() {
        return (Date) getAttributeInternal(RSDDB);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute RsdDb
     */
    public void setRsdDb(Date value) {
        setAttributeInternal(RSDDB, value);
    }

    /**Gets the attribute value for the calculated attribute SsdDb
     */
    public Date getSsdDb() {
        return (Date) getAttributeInternal(SSDDB);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute SsdDb
     */
    public void setSsdDb(Date value) {
        setAttributeInternal(SSDDB, value);
    }

    /**Gets the attribute value for the calculated attribute LineNumber
     */
    public String getLineNumber() {
        return (String) getAttributeInternal(LINENUMBER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LineNumber
     */
    public void setLineNumber(String value) {
        setAttributeInternal(LINENUMBER, value);
    }

    /**Gets the attribute value for the calculated attribute OrderTypeCode
     */
    public String getOrderTypeCode() {
        return (String) getAttributeInternal(ORDERTYPECODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute OrderTypeCode
     */
    public void setOrderTypeCode(String value) {
        setAttributeInternal(ORDERTYPECODE, value);
    }

    /**Gets the attribute value for the calculated attribute LeadTime
     */
    public Number getLeadTime() {
        return (Number) getAttributeInternal(LEADTIME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LeadTime
     */
    public void setLeadTime(Number value) {
        setAttributeInternal(LEADTIME, value);
    }

    /**Gets the attribute value for the calculated attribute MaxQuantityLeadTime
     */
    public Number getMaxQuantityLeadTime() {
        return (Number) getAttributeInternal(MAXQUANTITYLEADTIME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute MaxQuantityLeadTime
     */
    public void setMaxQuantityLeadTime(Number value) {
        setAttributeInternal(MAXQUANTITYLEADTIME, value);
    }

    /**Gets the attribute value for the calculated attribute Mpq
     */
    public Number getMpq() {
        return (Number) getAttributeInternal(MPQ);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Mpq
     */
    public void setMpq(Number value) {
        setAttributeInternal(MPQ, value);
    }

    /**Gets the attribute value for the calculated attribute OrganizationId
     */
    public Number getOrganizationId() {
        return (Number) getAttributeInternal(ORGANIZATIONID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute OrganizationId
     */
    public void setOrganizationId(Number value) {
        setAttributeInternal(ORGANIZATIONID, value);
    }

    /**Gets the attribute value for the calculated attribute CumulativeQuantity
     */
    public String getCumulativeQuantity() {
        return (String) getAttributeInternal(CUMULATIVEQUANTITY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CumulativeQuantity
     */
    public void setCumulativeQuantity(String value) {
        setAttributeInternal(CUMULATIVEQUANTITY, value);
    }

    /**Gets the attribute value for the calculated attribute LegacySo
     */
    public String getLegacySo() {
        return (String) getAttributeInternal(LEGACYSO);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LegacySo
     */
    public void setLegacySo(String value) {
        setAttributeInternal(LEGACYSO, value);
    }


    /**Gets the attribute value for the calculated attribute RushOrderFlag
     */
    public String getRushOrderFlag() {
        return (String) getAttributeInternal(RUSHORDERFLAG);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute RushOrderFlag
     */
    public void setRushOrderFlag(String value) {
        setAttributeInternal(RUSHORDERFLAG, value);
    }

    /**Gets the attribute value for the calculated attribute RushOrderFlagDb
     */
    public String getRushOrderFlagDb() {
        return (String) getAttributeInternal(RUSHORDERFLAGDB);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute RushOrderFlagDb
     */
    public void setRushOrderFlagDb(String value) {
        setAttributeInternal(RUSHORDERFLAGDB, value);
    }

    /**Gets the attribute value for the calculated attribute reasonCode
     */
    public String getreasonCode() {
        return (String) getAttributeInternal(REASONCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute reasonCode
     */
    public void setreasonCode(String value) {
        setAttributeInternal(REASONCODE, value);
    }

    /**Gets the attribute value for the calculated attribute isRendered
     */
    public Boolean getisRendered() {
        return (Boolean) getAttributeInternal(ISRENDERED);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute isRendered
     */
    public void setisRendered(Boolean value) {
        setAttributeInternal(ISRENDERED, value);
    }

    /**Gets the attribute value for the calculated attribute Sad
     */
    public Date getSad() {
        return (Date) getAttributeInternal(SAD);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Sad
     */
    public void setSad(Date value) {
        setAttributeInternal(SAD, value);
    }

    /**Gets the attribute value for the calculated attribute Rd
     */
    public Date getRd() {
        return (Date) getAttributeInternal(RD);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Rd
     */
    public void setRd(Date value) {
        setAttributeInternal(RD, value);
    }

    /**Gets the attribute value for the calculated attribute SadRdRender
     */
    public Boolean getSadRdRender() {
        return (Boolean) getAttributeInternal(SADRDRENDER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute SadRdRender
     */
    public void setSadRdRender(Boolean value) {
        setAttributeInternal(SADRDRENDER, value);
    }

    /**Gets the attribute value for the calculated attribute isScaRender
     */
    public Boolean getisScaRender() {
        return (Boolean) getAttributeInternal(ISSCARENDER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute isScaRender
     */
    public void setisScaRender(Boolean value) {
        setAttributeInternal(ISSCARENDER, value);
    }

    /**Gets the attribute value for the calculated attribute isRushOrdFlagReadOnly
     */
    public Boolean getisRushOrdFlagReadOnly() {
        return (Boolean) getAttributeInternal(ISRUSHORDFLAGREADONLY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute isRushOrdFlagReadOnly
     */
    public void setisRushOrdFlagReadOnly(Boolean value) {
        setAttributeInternal(ISRUSHORDFLAGREADONLY, value);
    }

    /**Gets the attribute value for the calculated attribute LegacyLineNumber
     */
    public String getLegacyLineNumber() {
        return (String) getAttributeInternal(LEGACYLINENUMBER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LegacyLineNumber
     */
    public void setLegacyLineNumber(String value) {
        setAttributeInternal(LEGACYLINENUMBER, value);
    }

    /**Gets the attribute value for the calculated attribute SiteUseCode
     */
    public String getSiteUseCode() {
        return (String) getAttributeInternal(SITEUSECODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute SiteUseCode
     */
    public void setSiteUseCode(String value) {
        setAttributeInternal(SITEUSECODE, value);
    }


    /**Gets the attribute value for the calculated attribute SsdResceduleCounter
     */
    public Number getSsdResceduleCounter() {
        return (Number) getAttributeInternal(SSDRESCEDULECOUNTER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute SsdResceduleCounter
     */
    public void setSsdResceduleCounter(Number value) {
        setAttributeInternal(SSDRESCEDULECOUNTER, value);
    }

    /**Gets the attribute value for the calculated attribute isRoFlagRender
     */
    public Boolean getisRoFlagRender() {
        return (Boolean) getAttributeInternal(ISROFLAGRENDER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute isRoFlagRender
     */
    public void setisRoFlagRender(Boolean value) {
        setAttributeInternal(ISROFLAGRENDER, value);
    }

    /**Gets the attribute value for the calculated attribute isRoCbRender
     */
    public Boolean getisRoCbRender() {
        return (Boolean) getAttributeInternal(ISROCBRENDER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute isRoCbRender
     */
    public void setisRoCbRender(Boolean value) {
        setAttributeInternal(ISROCBRENDER, value);
    }

    /**Gets the attribute value for the calculated attribute PoNumber
     */
    public String getPoNumber() {
        return (String) getAttributeInternal(PONUMBER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PoNumber
     */
    public void setPoNumber(String value) {
        setAttributeInternal(PONUMBER, value);
    }
}