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
   /* Program      : XXON_OM_GBW_CHGORD_RESULT_V.sql                                             */
   /* Date         : 10-JUL-2017                                                                 */
   /* Application  : Custom GBW Application                                                      */
   /* Purpose      : Oracle OM                                                                   */
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
CREATE OR REPLACE VIEW XXON_OM_GBW_CHGORD_RESULT_V
(
   OPN,
   ORDER_NUMBER,
   RSD,
   SSD,
   SAD,
   PL_RSD,
   PL_NEWRSD,
   QUANTITY,
   HEADER_ID,
   LINE_ID,
   ORG_ID,
   LINE_NUMBER,
   CHANGE_TYPE,
   NEW_RSD,
   NEW_SSD,
   NEW_QUANTITY,
   STATUS,
   CUST_CODE,
   CUST5_CODE,
   ORDER_TYPE,
   ORDER_HOLD,
   LEAD_TIME,
   MPQ,
   PAL,
   PACKAGE1,
   OPER_UNIT,
   PROD_CATEGORY,
   MAXQTY_LEAD_TIME,
   STG_LINE_ID,
   STG_HEADER_ID,
   INVENTORY_ITEM_ID,
   ORGANIZATION_ID,
   ITEM_NUMBER,
   BIZ_CLASS,
   ITEM_STATUS,
   CUST_PO_NUMBER,
   CUST_PO_LINE_NUMBER,
   PRICE_LIST,
   PRICE_LIST_STATUS,
   CANCEL_REASON,
   PLANNER_ID,
   SITE_USE_CODE,
   LEGACY_ORDER,
   LEGACY_LINE_NUMBER,
   SSD_RESCEDULE_COUNTER,
   PO_NUMBER
)
AS
   SELECT   msib.segment1 opn,
            ooha.order_number ordernumber,
            oola.request_date rsd,
            oola.schedule_ship_date ssd,
            /*NVL2 (oola.schedule_ship_date,
                  oola.schedule_ship_date + hcsua.attribute19,
                  oola.schedule_ship_date)*/
            oola.promise_date sad,
            NVL2 (oola.request_date,
                  --oola.request_date - hcsua.attribute19, added on 19/jul/18 by raghu  
				  oola.request_date - nvl(oola.attribute10,hcsua.attribute19),
                  oola.request_date)
               PL_RSD,
            NVL2 (xobl.date_requested,
                  --xobl.date_requested - hcsua.attribute19,added on 19/jul/18 by raghu
				  xobl.date_requested - nvl(oola.attribute10,hcsua.attribute19),
                  xobl.date_requested)
               PL_NEWRSD,
            oola.ordered_quantity quantity,
            oola.header_id header_id,
            oola.line_id line_id,
            oola.org_id org_id,
            TO_CHAR (oola.line_number)
            || DECODE (oola.shipment_number,
                       NULL, NULL,
                       '.' || TO_CHAR (oola.shipment_number))
               line_number,
            xobl.oracle_change_type,
            xobl.date_requested new_rsd,
            NULL new_ssd,                           -- xobl.schedule_ship_date
            xobl.quantity_requested new_quantity,
            xobl.status,
            hca.account_number custcode,
            hcsua.location cust5code,
            /*xxon_om_gbw_commons_pkg.get_ordertype_from_id (
                                                                                                                                                            p_order_type_id   => ooha.order_type_id
            )*/
            ooha.attribute7 /*ffvt.description*/ ordertype,
            NVL (
               (SELECT   'Y'
                  FROM   DUAL
                 WHERE   EXISTS
                            (SELECT   1
                               FROM   oe_order_holds_all ooll
                              WHERE       ooll.header_id = oola.header_id --Changed by Satish on 11/apr
                                      AND ooll.line_id IS NULL
                                      AND ooll.released_flag = 'N'
                             UNION
                             SELECT   1
                               FROM   oe_order_holds_all ooll
                              WHERE       ooll.header_id = oola.header_id --Changed by Satish on 11/apr
                                      AND ooll.line_id = oola.line_id
                                      AND ooll.released_flag = 'N')),
               'N'
            )
               orderhold,
            msib.variable_lead_time leadtime,
            TO_NUMBER (xmio.pri_cont_qty_mpq)  /*msib.minimum_order_quantity*/
                                             mpq, --modified by satish on 9/25
            msib.attribute14 pal,
            /*xxon_om_gbw_commons_pkg.get_package_code (
                                                                                                                                                                                                                                                                                                            p_mpn                 => NULL,
               p_inventory_item_id   => oola.inventory_item_id
            )*/
            xmio.package_group_code PACKAGE,
            hou.name "OPERATING_UNIT",
            xmio.market_code prodcategory,
            xmio.max_qty_lead_time maxqty_leadtime,
            xobl.stg_line_id,
            xobl.stg_header_id,
            --xobl.unit_list_price,
            msib.inventory_item_id,
            msib.organization_id,
            msib.segment1 ITEM_NUMBER,
            hcsua.ATTRIBUTE8 BIZ_CLASS,
            DECODE (mis.inventory_item_status_code,
                    '8-INACTIVE', 'Inactive',
                    '8-MFG INAC', 'Inactive',
                    '9-CANCELED', 'Inactive',
                    '9-MFG CANC', 'Inactive',
                    mis.inventory_item_status_code)
               AS ITEM_STATUS,
            ooha.cust_po_number CUST_PO_NUMBER,
            oola.customer_line_number CUST_PO_LINE_NUMBER,
            pl.NAME PRICE_LIST,
            /*xxon_om_gbw_commons_pkg.is_pricelist_active (
                                                                             p_line_id   => oola.line_id
            )*/
            /* NVL (
                                                                              (SELECT   'Y'
                  FROM   DUAL
                 WHERE   EXISTS
                            (SELECT   1
                               FROM   qp_list_headers_all qh
                              WHERE   qh.list_header_id = oola.price_list_id
                              AND SYSDATE BETWEEN qh.start_date_active AND
                                      nvl(qh.end_date_active, SYSDATE))),
               'N'
            ) */
            CASE
               WHEN SYSDATE BETWEEN pl.start_date_active
                                AND  NVL (pl.end_date_active, SYSDATE)
               THEN
                  'Y'
               ELSE
                  'N'
            END
               AS PRICE_LIST_STATUS,
            xobl.attribute5 AS cancel_reason,
            msib.planner_code AS Planner_Id,
            hcsua.site_use_code AS SITE_USE_CODE,
            ooha.attribute1 AS LEGACY_ORDER,
            (   oola.line_number
             || '.'
             || NVL (oolaeb.c_ext_attr20, oola.shipment_number))
               AS LEGACY_LINE_NUMBER,
            NVL (oolaeb.n_ext_attr2, 0) AS ssd_rescedule_counter,
            ooha.CUST_PO_NUMBER AS PO_NUMBER
     FROM   oe_order_headers_all ooha,
            oe_order_lines_all oola,
            qp_list_headers_all pl,
            mtl_system_items_b msib,
            oe_sys_parameters_all ospa,
            -- mtl_parameters mp,
            hr_operating_units hou,
            xxon_mtl_item_overflow xmio,
            mtl_item_status mis,
            hz_cust_accounts hca,
            hz_cust_site_uses_all hcsua,
            oe_order_lines_all_ext_b oolaeb,
            --oe_transaction_types_all ott,
            --oe_transaction_types_tl ottl,
            --fnd_flex_value_sets ffvs,
            --fnd_flex_values ffv,
            --fnd_flex_values_tl ffvt,
            (SELECT   oe_line_id,
                      oe_header_id,
                      stg_line_id,
                      stg_header_id,
                      oracle_change_type,
                      date_requested,
                      quantity_requested,
                      status,
                      schedule_ship_date,
                      unit_list_price,
                      attribute5
               FROM   xxon_om_bop_lines_stg
              WHERE   status = 'APPROVAL_PENDING'/*NOT IN
                                                                                                                                                                                                                 ('ORDER_LINE_IMPORTED',
'REJECTED',
'APPROVED',
'FAIL')*/
                                                 ) xobl
    WHERE       oola.header_id = ooha.header_id
            AND oola.line_id = xobl.oe_line_id(+)
            AND oola.flow_status_code IN ('BOOKED', 'AWAITING_SHIPPING') -- chnage done by satish on 9/27 to remove ENTERED orders
            --AND ott.transaction_type_id = ottl.transaction_type_id
            --AND ott.transaction_type_code = 'ORDER'
            --AND ott.order_category_code = 'ORDER'
            --AND ottl.language = USERENV ('LANG')
            --AND ott.attribute6 = ffv.flex_value
            --AND ffvs.flex_value_set_id = ffv.flex_value_set_id
            --AND ffv.flex_value_id = ffvt.flex_value_id
            --AND ffvt.language = USERENV ('LANG')
            --AND ffv.enabled_flag = 'Y'
            --AND ffvs.flex_value_set_name = 'XXON_OM_SALES_ORDER_GROUP'
            --AND ott.transaction_type_id = ooha.order_type_id
            AND oola.price_list_id = pl.list_header_id
            AND oola.inventory_item_id = msib.inventory_item_id
            --AND oola.ship_from_org_id = mp.organization_id
            --AND mp.master_organization_id = msib.organization_id
            AND ospa.parameter_code = 'INVENTORY MASTER ORG'
            AND msib.organization_id = ospa.parameter_value
            AND ospa.org_id = oola.org_id
            AND oola.org_id = hou.organization_id
            AND msib.inventory_item_id = xmio.inventory_item_id(+)
            AND msib.organization_id = xmio.org_id(+)
            AND oola.sold_to_org_id = hca.cust_account_id
            AND oola.ship_to_org_id = hcsua.site_use_id
            AND hcsua.site_use_code = 'SHIP_TO'
            AND msib.inventory_item_status_code =
                  mis.inventory_item_status_code
            AND oola.line_id = oolaeb.line_id(+)
            -- change done by satish on 9/27 to remove picked lines
            AND NOT EXISTS
                  (SELECT   1
                     FROM   WSH_DELIVERY_DETAILS
                    WHERE       RELEASED_STATUS IN ('Y', 'C', 'D', 'S')
                            AND SOURCE_HEADER_ID = ooha.HEADER_ID
                            AND SOURCE_LINE_ID = oola.LINE_ID);
show errors;
exit;						

