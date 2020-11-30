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
   /* Program      : XXON_OM_GBW_AUDIT_BOP_LINES_V.sql                                           */
   /* Date         : 27-DEC-2017                                                                 */
   /* Application  : Custom Application                                                          */
   /* Purpose      : Oracle OM                                                                   */
   /* Prepared By  : Kishore Marripudi                                                           */
   /* Reviewed By  :                                                                             */
   /* *********************************************************************************************
   | Change History :                                                                            |
   |---------------------------------------------------------------------------------------------|
   | Version      Name                   Date          Description of Change                     |
   |---------   --------------         ------------    ----------------------------------------- |
   | 1.0.0        KISHORE M              27-DEC-2017     Initial Creation                        |
   |                                                                                             |
   ***********************************************************************************************/
CREATE OR REPLACE VIEW XXON_OM_GBW_AUDIT_BOP_LINES_V
(
   ORDER_NUMBER,
   HEADER_ID,
   CORP_CODE,
   CUST5_CODE,
   PAL,
   PACKAGE_CD,
   AUDIT_LEVEL,
   MPN,
   INVENTORY_ITEM_ID,
   LINE_NUMBER
)
AS
   SELECT   xobl.purchase_order AS order_number,
            xobl.stg_line_id AS header_id,
            hca.ACCOUNT_NUMBER AS "CORP_CODE",
            xobh.ship_to_5code AS "CUST5_CODE",
            NULL AS pal,
            NULL AS package_cd,
            'Staged Line' AS audit_level,
            xobl.part_number AS MPN,
            xobl.INVENTORY_ITEM_ID,
            xobl.po_line_number || '.' || NVL (xobl.po_shipment_number, 1) AS  LINE_NUMBER
     FROM   xxon_om_bop_lines_stg xobl,
            xxon_om_bop_headers_stg xobh,
            hz_cust_accounts hca
    WHERE   xobl.stg_header_id = xobh.stg_header_id
            AND xobh.sold_to_org_id = hca.cust_account_id;
show errors;
exit;