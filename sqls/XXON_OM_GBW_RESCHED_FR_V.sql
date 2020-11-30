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
   /* Program      : XXON_OM_GBW_RESCHED_FR_V.sql                                                */
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
   | 1.0.2        KISHORE M              10-JUL-2017     Initial Creation                        |
   |                                                                                             |
   ***********************************************************************************************/
CREATE OR REPLACE VIEW xxon_om_gbw_resched_fr_v
(
   opn,
   customer_code,
   cust5_code,
   order_hold,
   order_type,
   demand_type,
   order_number,
   rsd,
   ssd,
   reschedule_reason,
   quantity,
   pegging_detail,
   product_category,
   lead_time,
   max_quantity_lead_time,
   mpq,
   mp_date,
   coi,
   scheduling_return,
   csr,
   split_flag,
   item_planner,
   pkg,
   pal,
   operating_unit,
   operating_unit_name,
   status,
   inventory_item_id,
   line_id,
   header_id,
   reservtn_id,
   demand_bank,
   line_number,
   organization_id,
   legacy_so,
   rush_order_flag,
   sad,
   rd,
   legacy_line_number,
   site_use_code,
   ssd_rescedule_counter,
   po_number
)
AS
   SELECT   msib.segment1 opn,
            hca.account_number customer_code,
            hcsua.location AS cust5_code,
            NVL (
               (SELECT   'Y'
                  FROM   DUAL
                 WHERE   EXISTS
                            (SELECT   1
                               FROM   oe_order_holds_all ooll
                              WHERE   ooll.header_id = ool.header_id 
                                      AND ooll.line_id is NULL
                                      AND ooll.released_flag = 'N'
                             UNION
                             SELECT   1
                               FROM   oe_order_holds_all ooll
                              WHERE   ooll.header_id = ool.header_id  
                                      AND ooll.line_id = ool.line_id
                                      AND ooll.released_flag = 'N')),
               'N'
            )
               order_hold,
            ooh.attribute7 order_type,
            'SO' demand_type,
            TO_CHAR (ooh.order_number) order_number,
            ool.request_date - NVL (ool.attribute10,hcsua.attribute19) AS rsd,
            ool.schedule_ship_date AS ssd,
             xxon_om_gbw_commons_pkg.get_reschedule_reason(DECODE (
                                                             ool.attribute16,
                                                             'Not Re Scheduled',
                                                             NULL,
                                                             ool.attribute16
                                                          ))
               AS reschedule_reason,
            ool.ordered_quantity AS quantity,
            pegging_details_t.stage_name AS pegging_detail,
            xmio.market_code product_category,
            msib.variable_lead_time AS lead_time,
            xmio.max_qty_lead_time AS max_quantity_lead_time,
            TO_NUMBER (xmio.pri_cont_qty_mpq) AS mpq,
            pegging_details_t.logdate AS mp_date,
            ool.attribute3 AS coi,
            ool.attribute7 AS scheduling_return,
            xxon_om_gbw_commons_pkg.get_csr (ooh.ship_to_contact_id) AS csr,
            DECODE (NVL (ool.split_by, '-K'), '-K', 'N', 'Y') AS split_flag,
            xxon_om_gbw_commons_pkg.get_planner (
               p_inventory_item_id   => ool.inventory_item_id,
               p_org_id              => ool.ship_from_org_id,
               p_mpn                 => NULL
            )
               item_planner,
            xmio.package_group_code AS pkg,
            msib.attribute14 AS pal,
            ool.org_id AS operating_unit,
            hou.name AS operating_unit_name,
            ool.flow_status_code AS status,
            ool.inventory_item_id,
            ool.line_id,
            ooh.header_id,
            NULL reservtn_id,
            ool.attribute1 AS demand_bank,
            TO_CHAR (ool.line_number)
            || DECODE (ool.shipment_number,
                       NULL, NULL,
                       '.' || TO_CHAR (ool.shipment_number))
               line_number,
            msib.organization_id,
            ooh.attribute1 AS legacy_so,
            NVL (ool.attribute9, 'N') AS rush_order_flag,
            ool.promise_date          AS sad,
            ool.request_date AS rd,
            (   ool.line_number
             || '.'
             || NVL (oolaeb.c_ext_attr20, ool.shipment_number))
               AS legacy_line_number,
            hcsua.site_use_code,
            NVL (oolaeb.n_ext_attr2, 0) AS ssd_rescedule_counter,
            ooh.cust_po_number
     FROM   oe_order_headers_all ooh,
            oe_order_lines_all ool,
            hz_cust_accounts_all hca,
            xxon_mtl_item_overflow xmio,
            mtl_system_items_b msib,
            oe_sys_parameters_all ospa,
            hr_operating_units hou,
            hz_cust_site_uses_all hcsua,
            (SELECT   line_id, logdate, stage_name
               FROM   xxon_aps_pegging_details_v) pegging_details_t,
            oe_order_lines_all_ext_b oolaeb
    WHERE       1 = 1
            AND ooh.header_id = ool.header_id
            AND ool.line_id = pegging_details_t.line_id(+)
            AND ool.inventory_item_id = msib.inventory_item_id
            AND ool.org_id = hou.organization_id
            AND ool.ship_to_org_id = hcsua.site_use_id
            AND ool.sold_to_org_id = hca.cust_account_id
            AND ospa.parameter_code = 'INVENTORY MASTER ORG'
            AND msib.organization_id = ospa.parameter_value
            AND ospa.org_id = ool.org_id
            AND msib.organization_id = xmio.org_id(+)
            AND msib.inventory_item_id = xmio.inventory_item_id(+)
            AND ool.flow_status_code IN ('BOOKED', 'AWAITING_SHIPPING')
            AND NOT EXISTS
                  (SELECT   1
                     FROM   wsh_delivery_details
                    WHERE       released_status IN ('Y', 'C', 'D', 'S')
                            AND source_header_id = ooh.header_id
                            AND source_line_id = ool.line_id)
            AND ool.line_id = oolaeb.line_id(+);
show errors;
exit;