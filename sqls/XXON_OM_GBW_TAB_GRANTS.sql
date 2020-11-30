 /*================================================================================================*
  | Copyright  2016 - TRINITI Corporation.  All rights reserved.                                 |
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
  /* HISTORY      :    1.0                                                                      */
  /* CR           :                                                                             */
  /* Date         :   11-jul-2017                                                               */
  /* Application  :  OM GBW Application                                                         */
  /* Purpose      :                                                                             */
  /* Prepared By  :   Kishore Marripudi                                                         */
  /* Reviewed By  :                                                                             */
  /* *********************************************************************************************/
---XXON_OM_SCH_CONTROLS
EXECUTE AD_ZD.grant_privs ('SELECT,INSERT,UPDATE,DELETE','XXON_OM_SCH_CONTROLS','MW_ACCESS',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_SCH_CONTROLS','APPS_QUERY',NULL,TRUE);
---XXON_OM_FMS_NETWORK
EXECUTE AD_ZD.grant_privs ('SELECT,INSERT,UPDATE,DELETE','XXON_OM_FMS_NETWORK','MW_ACCESS',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_FMS_NETWORK','APPS_QUERY',NULL,TRUE);
--XXON_OM_AUDIT_ATTR_HISTORY
EXECUTE AD_ZD.grant_privs ('SELECT,INSERT,UPDATE,DELETE','XXON_OM_AUDIT_ATTR_HISTORY','MW_ACCESS',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_AUDIT_ATTR_HISTORY','APPS_QUERY',NULL,TRUE);
-- package
EXECUTE AD_ZD.grant_privs ('EXECUTE','XXON_OM_STATUS_CHNG_EVENT_PKG','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('EXECUTE','XXON_OM_GBW_UTILS_PKG','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('EXECUTE','XXON_OM_STATUS_CHNG_EVENT_PKG','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('EXECUTE','XXON_OM_GBW_UTILS_PKG','TECH_SUPPORT',NULL,TRUE);
GRANT EXECUTE ON APPS.XXON_OM_GBW_UTILS_PKG TO TECH_SUPPORT;
GRANT EXECUTE ON APPS.XXON_OM_STATUS_CHNG_EVENT_PKG TO TECH_SUPPORT;
-- gbw view's
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_BOH_LINE_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_BOH_LINE_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_BOL_LINE_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_BOP_HEADER_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_BOP_LINES_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_FR_LINE_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_FR_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_ORDERS_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_ORD_LINES_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_SCHCT_LINE_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_SCH_CTRLS_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_SO_LINE_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_CANCE_REASON_LOV_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_CHGORD_RESULT_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_CORP_CODE_LOV_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_CUST5CODE_LOV_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_DEMAND_BANK_LOV_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_FCST_RESERV_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_INVRES_RESULT_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_ITEM_HOVER_POPUP_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_OPERAT_UNIT_LOV_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_OPN_LOV_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_ORDER_HOLD_POPUP_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_ORDER_TYPE_LOV_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_PKGCODE_LOV_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_PTI4_LOV_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_RESCH_REASON_LOV_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_SCH_CTRL_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_SHIPORD_MASTER_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_USER_NAME_LOV_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OE_AUDIT_ATTR_DESC_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_EXCLUDE_ORDTYPE_V','APPS_QUERY',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_RESCHED_FR_V','APPS_QUERY',NULL,TRUE);
-- tech support
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_EXCLUDE_ORDTYPE_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_RESCHED_FR_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_BOH_LINE_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_BOH_LINE_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_BOL_LINE_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_BOP_HEADER_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_BOP_LINES_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_FR_LINE_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_FR_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_ORDERS_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_ORD_LINES_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_SCHCT_LINE_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_SCH_CTRLS_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_AUDIT_SO_LINE_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_CANCE_REASON_LOV_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_CHGORD_RESULT_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_CORP_CODE_LOV_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_CUST5CODE_LOV_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_DEMAND_BANK_LOV_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_FCST_RESERV_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_INVRES_RESULT_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_ITEM_HOVER_POPUP_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_OPERAT_UNIT_LOV_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_OPN_LOV_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_ORDER_HOLD_POPUP_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_ORDER_TYPE_LOV_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_PKGCODE_LOV_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_PTI4_LOV_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_RESCH_REASON_LOV_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_SCH_CTRL_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_SHIPORD_MASTER_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OM_GBW_USER_NAME_LOV_V','TECH_SUPPORT',NULL,TRUE);
EXECUTE AD_ZD.grant_privs ('SELECT','XXON_OE_AUDIT_ATTR_DESC_V','TECH_SUPPORT',NULL,TRUE);
EXIT;
