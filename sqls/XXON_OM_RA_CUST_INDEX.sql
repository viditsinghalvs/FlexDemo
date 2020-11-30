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
-------------------------------RA_CUSTOMER_TRX_LINES_ALL----------------------------------------
CREATE INDEX XXON.XXON_RA_CUST_TRX_LINES_ALL_ID2 ON AR.RA_CUSTOMER_TRX_LINES_ALL(INTERFACE_LINE_ATTRIBUTE1);
CREATE INDEX XXON.XXON_RA_CUST_TRX_LINES_ALL_ID3 ON AR.RA_CUSTOMER_TRX_LINES_ALL(INTERFACE_LINE_ATTRIBUTE8);
----------------------------------------OE_ORDER_HOLDS_ALL--------------------------------
CREATE INDEX XXON.FND_FLEX_VALUES_TL_IDX1        ON APPLSYS.FND_FLEX_VALUES_TL("DESCRIPTION","LANGUAGE","ZD_EDITION_NAME");
-------------------------------------------------OE_ORDER_HOLDS_ALL--------------------
CREATE INDEX XXON.OE_ORDER_HOLDS_ALL_IDX1        ON ONT.OE_ORDER_HOLDS_ALL("HEADER_ID","RELEASED_FLAG");
------------------------------------------HZ_CUST_ACCOUNTS------------------------------
CREATE INDEX XXON.HZ_CUST_ACCOUNTS_IDX1          ON AR.HZ_CUST_ACCOUNTS("ACCOUNT_NUMBER","CUST_ACCOUNT_ID");
-----------------------------------------XXON_APS_PEGGING_DETAILS-------------------------------------
CREATE INDEX XXON.XXON_APS_PEGGING_DETAILS_IDX2  ON XXON.XXON_APS_PEGGING_DETAILS("LINE_ID","LOGDATE","STAGE_NAME");
----------------------------------------------OE_ORDER_HEADERS_ALL--------------------------------
CREATE INDEX XXON.OE_ORDER_HEADERS_ALL_IDX1      ON ONT.OE_ORDER_HEADERS_ALL("ORDER_TYPE_ID");
CREATE INDEX XXON.XXON_OE_ORDER_HEADERS_ALL_N1   ON ONT.OE_ORDER_HEADERS_ALL (TO_CHAR(ORDER_NUMBER));
-----------------------------XXON_OM_BOP_LINES_STG--------------------------------------
CREATE INDEX XXON.XXON_OM_BOP_LINES_STG_IDX2     ON XXON.XXON_OM_BOP_LINES_STG("OE_LINE_ID");
------------------------------HR_ORGANIZATION_INFORMATION---------------------
CREATE INDEX XXON.HR_ORGANIZATION_INFO_IDX2      ON HR.HR_ORGANIZATION_INFORMATION("ORGANIZATION_ID","ORG_INFORMATION1");
CREATE INDEX XXON.HR_ORGANIZATION_INFO_IDX1      ON HR.HR_ORGANIZATION_INFORMATION("ORG_INFORMATION1","ORG_INFORMATION_CONTEXT"||'',"ORG_INFORMATION2");
--------------------------------OE_TRANSACTION_TYPES_ALL--------------------------------
CREATE INDEX XXON.OE_TRANSACTION_TYPES_ALL_IDX2  ON ONT.OE_TRANSACTION_TYPES_ALL("TRANSACTION_TYPE_CODE","ORDER_CATEGORY_CODE");
------------------------XXON_MTL_ITEM_OVERFLOW--------------------------
CREATE INDEX XXON.XXON_MTL_ITEM_OVERFLOW_IDX1    ON XXON.XXON_MTL_ITEM_OVERFLOW("PACKAGE_GROUP_CODE");
---------------------OE_ORDER_LINES_ALL----------------------
CREATE INDEX XXON.OE_ORDER_LINES_ALL_IDX1        ON ONT.OE_ORDER_LINES_ALL("SOLD_TO_ORG_ID");
CREATE INDEX XXON.OE_ORDER_LINES_ALL_IDX2        ON ONT.OE_ORDER_LINES_ALL("SHIP_FROM_ORG_ID");
CREATE INDEX XXON.OE_ORDER_LINES_ALL_IDX3        ON ONT.OE_ORDER_LINES_ALL("ATTRIBUTE3");
CREATE INDEX XXON.OE_ORDER_LINES_ALL_IDX4        ON ONT.OE_ORDER_LINES_ALL("FLOW_STATUS_CODE");
CREATE INDEX XXON.OE_ORDER_LINES_ALL_IDX5        ON ONT.OE_ORDER_LINES_ALL("ORG_ID");
CREATE INDEX XXON.OE_ORDER_LINES_ALL_IDX6        ON ONT.OE_ORDER_LINES_ALL("SHIP_TO_ORG_ID");
---------------------------XXON_OM_FCST_RESERVATIONS-------------------------------
CREATE INDEX XXON.XXON_OM_FCST_RESERVATIONS_IDX1 ON XXON.XXON_OM_FCST_RESERVATIONS("CORP_CD","RESERVTN_TYPE");
CREATE INDEX XXON.XXON_OM_FCST_RESERVATIONS_IDX2 ON XXON.XXON_OM_FCST_RESERVATIONS("PACKAGE_STD_CD","RESERVTN_TYPE");
CREATE INDEX XXON.XXON_OM_FCST_RESERVATIONS_IDX3 ON XXON.XXON_OM_FCST_RESERVATIONS("PTI4","RESERVTN_TYPE");
CREATE INDEX XXON.XXON_OM_FCST_RESERVATIONS_IDX4 ON XXON.XXON_OM_FCST_RESERVATIONS(SUBSTR("PTI4",0,2),"RESERVTN_TYPE");
CREATE INDEX XXON.XXON_OM_FCST_RESERVATIONS_IDX5 ON XXON.XXON_OM_FCST_RESERVATIONS(SUBSTR("PTI4",0,3),"RESERVTN_TYPE");
CREATE INDEX XXON.XXON_OM_FCST_RESERVATIONS_IDX6 ON XXON.XXON_OM_FCST_RESERVATIONS("HEADER_NUMBER");
--------------------------------MTL_SYSTEM_ITEMS_B--------------------------------------------------
CREATE INDEX XXON.MTL_SYSTEM_ITEMS_B_IDX1        ON INV.MTL_SYSTEM_ITEMS_B(ORGANIZATION_ID,ATTRIBUTE14);
CREATE INDEX XXON.MTL_SYSTEM_ITEMS_B_IDX2        ON INV.MTL_SYSTEM_ITEMS_B(ORGANIZATION_ID,SUBSTR(ATTRIBUTE14,0,3));
CREATE INDEX XXON.MTL_SYSTEM_ITEMS_B_IDX3        ON INV.MTL_SYSTEM_ITEMS_B(ORGANIZATION_ID,SUBSTR(ATTRIBUTE14,0,2));
----------------------XXON_OM_AUDIT_ATTR_HISTORY-----------------------
DROP INDEX XXON.XXON_OM_AUDIT_ATTR_HISTORY_U1;
DROP INDEX XXON.XXON_OM_AUDIT_ATTR_HISTORY_U2;
--
CREATE INDEX XXON.XXON_OM_AUDIT_ATTR_HIST_IDX1   ON XXON.XXON_OM_AUDIT_ATTR_HISTORY(HIST_CREATION_DATE);
CREATE INDEX XXON.XXON_OM_AUDIT_ATTR_HIST_IDX2   ON XXON.XXON_OM_AUDIT_ATTR_HISTORY("ENTITY_NAME","HIST_CREATION_DATE","USER_ID");
CREATE INDEX XXON.XXON_OM_AUDIT_ATTR_HIST_IDX3   ON XXON.XXON_OM_AUDIT_ATTR_HISTORY(TO_NUMBER("ENTITY_KEY"),"ENTITY_NAME");
-----------------------------END----------------------------------
EXIT;
