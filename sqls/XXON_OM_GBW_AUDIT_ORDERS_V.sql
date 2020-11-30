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
   /* Program      : XXON_OM_GBW_AUDIT_ORDERS_V.sql                                              */
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
CREATE OR REPLACE VIEW XXON_OM_GBW_AUDIT_ORDERS_V
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
   SELECT   TO_CHAR (ooha.order_number) AS order_number,
            ooha.header_id,
            hca.account_number "CORP_CODE",
            hcsua.location "CUST5_CODE",
            NULL AS pal,
            NULL AS package_cd,
            'Order Header' audit_level,
            NULL AS MPN,
            NULL AS INVENTORY_ITEM_ID,
            NULL AS LINE_NUMBER
     FROM   oe_order_headers_all ooha,
            hz_cust_accounts hca,
            hz_cust_site_uses_all hcsua
    WHERE   ooha.sold_to_org_id = hca.cust_account_id
            AND ooha.ship_to_org_id = hcsua.site_use_id;
show errors;
exit;