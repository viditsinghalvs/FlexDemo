package oracle.apps.xxon.om.gbw.popup.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XXONGbwOpnItemDetailsVORowImpl extends OAViewRowImpl {
    public static final int LEADTIME = 0;
    public static final int MAXQUANTITYLEADTIME = 1;
    public static final int MPQ = 2;
    public static final int OPN = 3;
    public static final int INVENTORYITEMID = 4;
    public static final int MPN = 5;
    public static final int PAL = 6;
    public static final int ITEMSTATUSDESC = 7;
    public static final int ITEMSTATUSCODE = 8;
    public static final int ITEMDESCRIPTION = 9;
    public static final int SOQ = 10;
    public static final int POQ = 11;
    public static final int ORGANIZATIONID = 12;
    public static final int AGILELIFECYCLESTATUS = 13;
    public static final int PLANNERNAME = 14;
    public static final int PACKAGEGROUPCODE = 15;
    public static final int PRODUCTCATEGORY = 16;

    /**This is the default constructor (do not remove)
     */
    public XXONGbwOpnItemDetailsVORowImpl() {
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

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case LEADTIME:
            return getLeadTime();
        case MAXQUANTITYLEADTIME:
            return getMaxQuantityLeadTime();
        case MPQ:
            return getMpq();
        case OPN:
            return getOpn();
        case INVENTORYITEMID:
            return getInventoryItemId();
        case MPN:
            return getMpn();
        case PAL:
            return getPal();
        case ITEMSTATUSDESC:
            return getItemStatusDesc();
        case ITEMSTATUSCODE:
            return getItemStatusCode();
        case ITEMDESCRIPTION:
            return getItemDescription();
        case SOQ:
            return getSoq();
        case POQ:
            return getPoq();
        case ORGANIZATIONID:
            return getOrganizationId();
        case AGILELIFECYCLESTATUS:
            return getAgileLifeCycleStatus();
        case PLANNERNAME:
            return getPlannerName();
        case PACKAGEGROUPCODE:
            return getPackageGroupCode();
        case PRODUCTCATEGORY:
            return getProductCategory();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case LEADTIME:
            setLeadTime((Number)value);
            return;
        case MAXQUANTITYLEADTIME:
            setMaxQuantityLeadTime((Number)value);
            return;
        case MPQ:
            setMpq((Number)value);
            return;
        case OPN:
            setOpn((String)value);
            return;
        case INVENTORYITEMID:
            setInventoryItemId((Number)value);
            return;
        case MPN:
            setMpn((String)value);
            return;
        case PAL:
            setPal((String)value);
            return;
        case ITEMSTATUSDESC:
            setItemStatusDesc((String)value);
            return;
        case ITEMSTATUSCODE:
            setItemStatusCode((String)value);
            return;
        case ITEMDESCRIPTION:
            setItemDescription((String)value);
            return;
        case SOQ:
            setSoq((String)value);
            return;
        case POQ:
            setPoq((String)value);
            return;
        case ORGANIZATIONID:
            setOrganizationId((Number)value);
            return;
        case AGILELIFECYCLESTATUS:
            setAgileLifeCycleStatus((String)value);
            return;
        case PLANNERNAME:
            setPlannerName((String)value);
            return;
        case PACKAGEGROUPCODE:
            setPackageGroupCode((String)value);
            return;
        case PRODUCTCATEGORY:
            setProductCategory((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
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

    /**Gets the attribute value for the calculated attribute ItemStatusDesc
     */
    public String getItemStatusDesc() {
        return (String) getAttributeInternal(ITEMSTATUSDESC);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ItemStatusDesc
     */
    public void setItemStatusDesc(String value) {
        setAttributeInternal(ITEMSTATUSDESC, value);
    }

    /**Gets the attribute value for the calculated attribute ItemStatusCode
     */
    public String getItemStatusCode() {
        return (String) getAttributeInternal(ITEMSTATUSCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ItemStatusCode
     */
    public void setItemStatusCode(String value) {
        setAttributeInternal(ITEMSTATUSCODE, value);
    }

    /**Gets the attribute value for the calculated attribute ItemDescription
     */
    public String getItemDescription() {
        return (String) getAttributeInternal(ITEMDESCRIPTION);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ItemDescription
     */
    public void setItemDescription(String value) {
        setAttributeInternal(ITEMDESCRIPTION, value);
    }

    /**Gets the attribute value for the calculated attribute Soq
     */
    public String getSoq() {
        return (String) getAttributeInternal(SOQ);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Soq
     */
    public void setSoq(String value) {
        setAttributeInternal(SOQ, value);
    }

    /**Gets the attribute value for the calculated attribute Poq
     */
    public String getPoq() {
        return (String) getAttributeInternal(POQ);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Poq
     */
    public void setPoq(String value) {
        setAttributeInternal(POQ, value);
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

    /**Gets the attribute value for the calculated attribute AgileLifeCycleStatus
     */
    public String getAgileLifeCycleStatus() {
        return (String) getAttributeInternal(AGILELIFECYCLESTATUS);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute AgileLifeCycleStatus
     */
    public void setAgileLifeCycleStatus(String value) {
        setAttributeInternal(AGILELIFECYCLESTATUS, value);
    }

    /**Gets the attribute value for the calculated attribute PlannerName
     */
    public String getPlannerName() {
        return (String) getAttributeInternal(PLANNERNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PlannerName
     */
    public void setPlannerName(String value) {
        setAttributeInternal(PLANNERNAME, value);
    }

    /**Gets the attribute value for the calculated attribute PackageGroupCode
     */
    public String getPackageGroupCode() {
        return (String) getAttributeInternal(PACKAGEGROUPCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PackageGroupCode
     */
    public void setPackageGroupCode(String value) {
        setAttributeInternal(PACKAGEGROUPCODE, value);
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
}
