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
   /* Program      : XXON_OM_GBW_SHIPORD_MASTER_V.sql                                            */
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
CREATE OR REPLACE VIEW XXON_OM_GBW_SHIPORD_MASTER_V
(
   ACTUAL_SHIPMENT_DATE,
   OPN,
   ORDER_NUMBER,
   LINE_NUMBER,
   PAL,
   COI,
   PICK_REFERENCE,
   PICK_RELEASE_DATE,
   SHIP_CONFIRM_DATE,
   PACKAGE_DESC,
   OPERATING_UNIT,
   CORP_CODE,
   CUST5_CODE,
   LEAD_TIME,
   MAX_QTY_LEAD_TIME,
   MPQ,
   RSD,
   SSD,
   INVOICE_NUMBER,
   ORDER_TYPE,
   PRODUCT_CATEGORY,
   QUANTITY,
   WAY_BILL_NUMBER,
   ORGANIZATION_ID,
   INVENTORY_ITEM_ID,
   SITE_USE_CODE
)
AS
   SELECT   NVL (oola.actual_shipment_date, TO_DATE(wdd.attribute5,'RRRR/MM/DD HH24:MI:SS'))
               actual_shipment_date,
            msib.segment1 "OPN",
            ooha.order_number,
            TO_CHAR (oola.line_number)
            || DECODE (oola.shipment_number,
                       NULL, NULL,
                       '.' || TO_CHAR (oola.shipment_number))
               "LINE_NUMBER",
            msib.attribute14 "PAL",
            oola.attribute3 "COI",
            wdd.attribute3 "PICK_REFERENCE",
            TO_DATE(wdd.attribute5,'RRRR/MM/DD HH24:MI:SS')  AS "PICK_RELEASE_DATE",
            oola.actual_shipment_date "SHIP_CONFIRM_DATE",
            xmio.PACKAGE_GROUP_CODE   "PACKAGE_DESC",
            hou.short_code    "OPERATING_UNIT",
            hca.account_number "CORP_CODE",
            hcsua.location "CUST5_CODE",
            msib.variable_lead_time "LEAD_TIME",
            xmio.max_qty_lead_time,
            TO_NUMBER (xmio.pri_cont_qty_mpq) "MPQ",
            oola.request_date - TO_NUMBER (NVL (oola.attribute10,hcsua.attribute19)) "RSD",
            oola.schedule_ship_date "SSD",
            xxon_om_gbw_commons_pkg.get_trx_number (
               p_order_number   => ooha.order_number,
               p_line_id        => oola.line_id
            )
               "INVOICE_NUMBER",
            ooha.attribute7  "ORDER_TYPE",
            xmio.market_code "PRODUCT_CATEGORY",
            oola.ordered_quantity "QUANTITY",
            wnd.waybill   way_bill_number,
            oola.org_id organization_id,
            msib.inventory_item_id,
            hcsua.site_use_code
     FROM   oe_order_lines_all oola,
            oe_order_headers_all ooha,
            mtl_system_items_b msib,
            oe_sys_parameters_all ospa,
            hr_operating_units hou,
            xxon_mtl_item_overflow xmio,
            wsh_delivery_details wdd,
            wsh_delivery_assignments wda,
            wsh_new_deliveries wnd,
            hz_cust_accounts hca,
            hz_cust_site_uses_all hcsua
    WHERE   1 = 1
            AND oola.flow_status_code IN
                     ('SHIPPED', 'CLOSED', 'AWAITING_SHIPPING')
            AND oola.header_id = ooha.header_id
            AND oola.inventory_item_id = msib.inventory_item_id
            AND ospa.parameter_code = 'INVENTORY MASTER ORG'
            AND msib.organization_id = ospa.parameter_value
            AND ospa.org_id = oola.org_id
            AND msib.organization_id = xmio.org_id(+)
            AND msib.inventory_item_id = xmio.inventory_item_id(+)
            AND oola.org_id = hou.organization_id            
            AND wdd.source_code = 'OE'
            AND oola.header_id = wdd.source_header_id
            AND oola.line_id = wdd.source_line_id
            AND wdd.delivery_detail_id = wda.delivery_detail_id
            AND wnd.delivery_id = wda.delivery_id
            AND wdd.released_status IN ('C', 'Y')    --ship confirm and picked
            AND oola.sold_to_org_id = hca.cust_account_id
            AND hcsua.site_use_id = oola.ship_to_org_id
            AND hcsua.site_use_code = 'SHIP_TO';           
show errors;
exit;
