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
   /* Program      : XXON_OM_GBW_ITEM_HOVER_POPUP_V.sql                                          */
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
   | 1.0.1        KISHORE M              10-JUL-2017     Initial Creation                        |
   |                                                                                             |
   ***********************************************************************************************/
CREATE OR REPLACE VIEW XXON_OM_GBW_ITEM_HOVER_POPUP_V
(
   LEAD_TIME,
   MAX_QUANTITY_LEAD_TIME,
   MPQ,
   OPN,
   INVENTORY_ITEM_ID,
   PAL,
   ITEM_STATUS_DESC,
   ITEM_STATUS_CODE,
   ITEM_DESCRIPTION,
   SOQ,
   POQ,
   ORGANIZATION_ID,
   AGILE_LIFE_CYCLE_STATUS,
   PLANNER_NAME,
   PACKAGE_GROUP_CODE,
   PRODUCT_CATEGORY
)
AS
   SELECT   msib.variable_lead_time AS lead_time,
            xmio.max_qty_lead_time AS max_quantity_lead_time,
            TO_NUMBER (xmio.pri_cont_qty_mpq)  /*msib.minimum_order_quantity*/
                                             AS mpq, -- changed by satish on 9/25
            msib.segment1 AS opn,
            msib.inventory_item_id,
            msib.attribute14 AS pal,
            mis.description AS item_status_desc,
            msib.inventory_item_status_code AS item_status_code,
            msib.description AS item_description,
            DECODE (free_sample_allowed_flg, 'Y', 1, 0) AS soq,
            NULL poq,
            msib.organization_id,                -- added by satish by 12/19 ,
            msib.attribute1 AS agile_life_cycle_status,
            xxon_om_gbw_commons_pkg.get_planner (
               p_inventory_item_id   => NULL,
               p_org_id              => NULL,
               p_mpn                 => msib.segment1
            )
               AS planner_name,
            xmio.package_group_code,
            xmio.market_code product_category
     FROM   mtl_system_items_b msib,
            xxon_mtl_item_overflow xmio,
            oe_sys_parameters_all ospa,
            mtl_item_status mis
    WHERE   msib.inventory_item_id = xmio.inventory_item_id(+)
            AND msib.inventory_item_status_code =
                  mis.inventory_item_status_code
            AND ospa.parameter_code = 'INVENTORY MASTER ORG'
            AND msib.organization_id = ospa.parameter_value
            AND ospa.org_id = fnd_profile.VALUE ('ORG_ID')
            AND msib.organization_id = xmio.org_id(+);
show errors;
exit;
