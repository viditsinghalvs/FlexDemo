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
   /* Program      : XXON_OM_GBW_CUST5CODE_LOV_V.sql                                             */
   /* Date         : 11-DEC-2017                                                                 */
   /* Application  : Custom Application                                                          */
   /* Purpose      : Oracle AR                                                                   */
   /* Prepared By  : Kishore Marripudi                                                           */
   /* Reviewed By  :                                                                             */
   /* *********************************************************************************************
   | Change History :                                                                            |
   |---------------------------------------------------------------------------------------------|
   | Version      Name                   Date          Description of Change                     |
   |---------   --------------         ------------    ----------------------------------------- |
   | 1.0.0        KISHORE M              11-DEC-2017     Initial Creation                        |
   |                                                                                             |
   ***********************************************************************************************/
CREATE OR REPLACE VIEW XXON_OM_GBW_CUST5CODE_LOV_V
(
   CUST5_CODE,
   CORP_CODE
)
AS
   SELECT   LOCATION AS CUST5_CODE, HCA.ACCOUNT_NUMBER AS CORP_CODE
     FROM   HZ_CUST_ACCOUNTS HCA,
            HZ_CUST_ACCT_SITES_ALL HCASA,
            HZ_CUST_SITE_USES_ALL HCSUA
    WHERE       HCA.CUST_ACCOUNT_ID = HCASA.CUST_ACCOUNT_ID
            AND HCASA.CUST_ACCT_SITE_ID = HCSUA.CUST_ACCT_SITE_ID
            AND HCSUA.STATUS = 'A'
            AND HCASA.STATUS = 'A'
            AND HCA.STATUS = 'A'
            AND HCSUA.SITE_USE_CODE = 'SHIP_TO';
--GROUP BY LOCATION,HCA.ACCOUNT_NUMBER;
show errors;
exit;