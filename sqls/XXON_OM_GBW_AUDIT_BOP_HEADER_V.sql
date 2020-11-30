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
   /* Program      : XXON_OM_GBW_AUDIT_BOP_HEADER_V.sql                                          */
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
CREATE OR REPLACE VIEW XXON_OM_GBW_AUDIT_BOP_HEADER_V
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
   SELECT   xobh.purchase_order AS order_number,
            xobh.stg_header_id AS header_id,
            hca.ACCOUNT_NUMBER AS "CORP_CODE",
            xobh.ship_to_5code AS "CUST5_CODE",
            NULL AS pal,
            NULL AS package_cd,
            'Staged Header' AS audit_level,
            NULL AS MPN,
            NULL AS INVENTORY_ITEM_ID,
            NULL AS LINE_NUMBER
     FROM   xxon_om_bop_headers_stg xobh, hz_cust_accounts hca
    WHERE   hca.cust_account_id = xobh.sold_to_org_id;
show errors;
exit;
