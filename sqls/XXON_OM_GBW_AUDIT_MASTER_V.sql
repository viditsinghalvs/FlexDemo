  /*=============================================================================================*
   | Copyright  2000 - TRINITI Corporation.  All rights reserved.                                 |
   |                                                                                              |
   | This product (both software programs and documentation) is protected by US and international |
   | copyright laws. Unauthorized reproduction, reuse, distribution, de-compilation, or reverse   |
   | engineering of this program, or any portion of it is prohibited and may result in severe     |
   | civil and criminal penalties, prosecuted to the maximum extent allowable under law.          |
   |                                                                                              |
   | Program Documentation is licensed solely to support Triniti products and not for any other   |
   | purpose. The information contained in this document is subject to change without notice.     |
   |                                                                                              |
   | Triniti Corporation does not warrant that this document is error free. If you find any       |
   | errors in the documentation, please report them to us in writing.  Except as may be          |
   | expressly permitted in your license agreement, no part of these documents may be reproduced  |
   | or transmitted in any form or by any means, electronic or mechanical, for any purpose,       |
   | without the express written permission of Triniti Corporation.                               |
   *=============================================================================================*/
   /* *********************************************************************************************/
   /* HISTORY      :                                                                             */
   /* Program      : XXON_OM_GBW_AUDIT_MASTER_V
(.sql                                              */
   /* Date         : 10-JUL-2017                                                                 */
   /* Application  : Custom Application                                                          */
   /* Purpose      : Oracle AR                                                                   */
   /* Prepared By  : Kishore Marripudi                                                           */
   /* Reviewed By  :                                                                             */
   /* *********************************************************************************************
   | Change History :                                                                            |
   |---------------------------------------------------------------------------------------------|
   | Version      Name                   Date          Description of Change                     |
   |---------   --------------         ------------    ----------------------------------------- |
   | 1.0.0        KISHORE M              10-JUL-2017     Initial Creation                        |
   |                                                                                             |
   ***********************************************************************************************/
CREATE OR REPLACE VIEW APPS.XXON_OM_GBW_AUDIT_MASTER_V
(
   AUDIT_USER_NAME,
   AUDIT_TIMESTAMP,
   ORDER_NUMBER,
   LEGACY_ORDER_NUMBER,
   PACKAGE_CODE,
   PAL,
   INVENTORY_ITEM_ID,
   OPN,
   CORP,
   CUST5CODE,
   ATTRIBUTE_NAME,
   NEW_ATTRIBUTE_VALUE,
   OLD_ATTRIBUTE_VALUE,
   OPERATING_UNIT,
   DEMAND_TYPE,
   OPERATING_UNIT_NAME,
   ORDER_TYPE,
   LINE_NUMBER,
   AUDIT_LEVEL
)
AS
-- scheduling controls
   (SELECT   fu.user_name audit_user_name,
             sca.hist_creation_date audit_timestamp,
             NULL "ORDER_NUMBER",
             NULL "LEGACY_ORDER_NUMBER",
             sc.package_cd "PACKAGE_CODE",
             sc.pti "PAL",
             sc.inventory_item_id "INVENTORY_ITEM_ID",
             xxon_om_gbw_commons_pkg.get_opn (
                p_inventory_item_id   => sc.inventory_item_id
             )
                "OPN",
             NULL "CORP",
             NULL "CUST5CODE",
             DECODE (sca.attribute_name,
                     'SCHEDULING_METHOD', 'Scheduling Method',
                     sca.attribute_name)
                attribute_name,
             sca.new_attribute_value,
             sca.old_attribute_value,
             NULL "OPERATING_UNIT",
             NULL "DEMAND_TYPE",
             NULL "OPERATING_UNIT_NAME",
             NULL "ORDER_TYPE",
             NULL "LINE_NUMBER",
             NULL "AUDIT_LEVEL"
      FROM   xxon_om_audit_attr_history sca,
             xxon_om_sch_controls sc,
             fnd_user fu
     WHERE       sca.entity_name = 'XXON_OM_SCH_CONTROLS'
             AND sca.entity_key = sc.sch_control_id(+)
             AND sca.user_id = fu.user_id
    UNION ALL
-- forecast reservations
    SELECT   fu.user_name audit_user_name,
             fra.hist_creation_date audit_timestamp,
             fr.header_number "ORDER_NUMBER",
             NULL "LEGACY_ORDER_NUMBER",
             xxon_om_gbw_commons_pkg.get_package_code (
                p_mpn                 => TRIM (fr.mpn),
                p_inventory_item_id   => NULL
             )
                "PACKAGE_CODE",
             fr.pti4 "PAL",
             xxon_om_gbw_commons_pkg.get_inventory_item_id (
                p_mpn   => TRIM (fr.mpn)
             )
                "INVENTORY_ITEM_ID",
             fr.mpn "OPN",
             fr.corp_cd "CORP",
             fr.reservtn_cust_cd "CUST5CODE",
             DECODE (fra.attribute_name,
                     'SCHEDULE_SHIP_DATE', 'Schedule Ship Date',
                     fra.attribute_name)
                attribute_name,
             fra.new_attribute_value,
             fra.old_attribute_value,
             NULL "OPERATING_UNIT",
             'FR' "DEMAND_TYPE",
             NULL "OPERATING_UNIT_NAME",
             NULL "ORDER_TYPE",
             fr.work_week_num || '.' || fr.line_number "LINE_NUMBER",
             NULL "AUDIT_LEVEL"
      FROM   xxon_om_audit_attr_history fra,
             xxon_om_fcst_reservations fr,
             fnd_user fu
     WHERE       fra.entity_name = 'XXON_OM_FCST_RESERVATIONS'
             AND fra.entity_key = fr.reservtn_id(+)
             AND fra.user_id = fu.user_id
-- bop lines    
    UNION ALL
    SELECT   fu.user_name audit_user_name,
                     xobla.hist_creation_date audit_timestamp,
                     xobl.purchase_order "ORDER_NUMBER",
                     NULL "LEGACY_ORDER_NUMBER",
                     xxon_om_gbw_commons_pkg.get_package_code(
                          p_mpn => xobl.part_number,
                          p_inventory_item_id => xobl.inventory_item_id
                     ) "PACKAGE_CODE",
                     xobl.pal_code "PAL",
                     xobl.inventory_item_id "INVENTORY_ITEM_ID",
                     nvl(xobl.part_number,xobl.cpn) "OPN",
                     xobl.end_corp_code "CORP",
                     xobl.end_cust5_code "CUST5CODE",
                     DECODE (xobla.attribute_name,
                             'QUANTITY_REQUESTED', 'Quantity',
                             'CITI_NUMBER', 'Citi Number',
                             'PO_LINE_NUMBER', 'PO Line Number',
                             'CPN', 'CPN',
                             'PRICE_REQUESTED', 'Price',
                             xobla.attribute_name)
                        attribute_name,
                     xobla.new_attribute_value,
                     xobla.old_attribute_value,
                     xobl.org_id "OPERATING_UNIT",
                     'SO' "DEMAND_TYPE",
                     NULL "OPERATING_UNIT_NAME",
                     xxon_om_gbw_commons_pkg.get_ordertype_from_id(
                     p_order_type_id => xobh.order_type_id
                     ) "ORDER_TYPE",
                     xobl.po_line_number || '.' || nvl(xobl.po_shipment_number,1) "LINE_NUMBER",
                     'STG-LINE' "AUDIT_LEVEL"
              FROM   xxon_om_audit_attr_history xobla,
                     xxon_om_bop_lines_stg xobl,
                     xxon_om_bop_headers_stg xobh,
                     fnd_user fu
             WHERE       xobla.entity_name = 'XXON_OM_BOP_LINES_STG'
                     AND xobla.entity_key = xobl.stg_line_id(+)
                     AND xobh.stg_header_id = xobl.stg_header_id
             AND xobla.user_id = fu.user_id
-- bop headers
    UNION ALL
    SELECT   fu.user_name audit_user_name,
                     xobha.hist_creation_date audit_timestamp,
                     xobh.purchase_order "ORDER_NUMBER",
                     NULL "LEGACY_ORDER_NUMBER",
                     NULL "PACKAGE_CODE",
                     NULL "PAL",
                     NULL "INVENTORY_ITEM_ID",
                     NULL "OPN",
                     NULL "CORP",
                     NULL "CUST5CODE",
                     DECODE (xobha.attribute_name,
                             'SHIP_TO_5CODE', 'Ship To',
                             'BILL_TO_5CODE', 'Bill To',
                             'SOLD_TO_5CODE', 'Sold To',
                             'PURCHASE_ORDER', 'PO Number'
                             ,xobha.attribute_name)
                        attribute_name,
                     xobha.new_attribute_value,
                     xobha.old_attribute_value,
                     xobh.org_id "OPERATING_UNIT",
                     'SO' "DEMAND_TYPE",
                     NULL "OPERATING_UNIT_NAME",
                     xxon_om_gbw_commons_pkg.get_ordertype_from_id(
                     p_order_type_id => xobh.order_type_id
                     ) "ORDER_TYPE",
                     NULL "LINE_NUMBER",
                     'STG-HEADER' "AUDIT_LEVEL"
              FROM   xxon_om_audit_attr_history xobha,
                     xxon_om_bop_headers_stg xobh,
                     fnd_user fu
             WHERE       xobha.entity_name = 'XXON_OM_BOP_HEADERS_STG'
                     AND xobha.entity_key = xobh.stg_header_id(+)
             AND xobha.user_id = fu.user_id
    UNION ALL
-- sales orders
    SELECT oaad.user_name audit_user_name
      ,oaad.hist_creation_date audit_timestamp
      ,to_char(oaad.order_number) "ORDER_NUMBER"
       ,NULL /*xxon_om_gbw_commons_pkg.get_entity_att_value(p_entity_short_name => opev.entity_short_name
                                                   ,p_entity_number     => oaad.entity_number
                                                   ,p_entity_attribute  => 'LEGACY_ORDER_NUMBER')*/ "LEGACY_ORDER_NUMBER"
       ,xxon_om_gbw_commons_pkg.get_entity_att_value(p_entity_short_name => opev.entity_short_name
                                                   ,p_entity_number     => oaad.entity_number
                                                   ,p_entity_attribute  => 'PACKAGE_CODE') "PACKAGE_CODE"
       ,xxon_om_gbw_commons_pkg.get_entity_att_value(p_entity_short_name => opev.entity_short_name
                                                   ,p_entity_number     => oaad.entity_number
                                                   ,p_entity_attribute  => 'PAL') "PAL"
       ,to_number(xxon_om_gbw_commons_pkg.get_entity_att_value(p_entity_short_name => opev.entity_short_name
                                                   ,p_entity_number     => oaad.entity_number
                                                   ,p_entity_attribute  => 'INVENTORY_ITEM_ID')) "INVENTORY_ITEM_ID"
       ,xxon_om_gbw_commons_pkg.get_entity_att_value(p_entity_short_name => opev.entity_short_name
                                                   ,p_entity_number     => oaad.entity_number
                                                   ,p_entity_attribute  => 'OPN') "OPN"
       ,xxon_om_gbw_commons_pkg.get_entity_att_value(p_entity_short_name => opev.entity_short_name
                                                   ,p_entity_number     => oaad.entity_number
                                                   ,p_entity_attribute  => 'CORP_CODE') "CORP"
       ,xxon_om_gbw_commons_pkg.get_entity_att_value(p_entity_short_name => opev.entity_short_name
                                                   ,p_entity_number     => oaad.entity_number
                                                   ,p_entity_attribute  => 'CUST5_CODE') "CUST5CODE"
       ,oaad.attribute_display_name attribute_name
       ,oaad.new_display_value new_attribute_value
       ,oaad.old_display_value old_attribute_value
       ,oaad.org_id "OPERATING_UNIT"
       ,'SO' "DEMAND_TYPE"
       ,NULL "OPERATING_UNIT_NAME"
       ,xxon_om_gbw_commons_pkg.get_entity_att_value(p_entity_short_name => opev.entity_short_name
                                                   ,p_entity_number     => oaad.entity_number
                                                   ,p_entity_attribute  => 'ORDER_TYPE') "ORDER_TYPE"
       ,xxon_om_gbw_commons_pkg.get_entity_att_value(p_entity_short_name => opev.entity_short_name
                                                   ,p_entity_number     => oaad.entity_number
                                                   ,p_entity_attribute  => 'LINE_NUMBER') "LINE_NUMBER"
       ,opev.entity_short_name audit_level
  FROM oe_audit_attr_desc_v oaad
      ,oe_pc_attributes_v   opav
      ,oe_pc_entities_v     opev
 WHERE 1 = 1
   AND opav.entity_id = oaad.entity_id
   AND opav.attribute_id = oaad.attribute_id
   AND opav.entity_id = opev.entity_id
   AND opev.application_short_name = 'ONT' );
show errors;
exit;

