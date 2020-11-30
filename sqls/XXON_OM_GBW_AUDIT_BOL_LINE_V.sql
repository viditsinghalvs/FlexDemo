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
   /* Program      : XXON_OM_GBW_AUDIT_BOL_LINE_V.sql                                            */
   /* Date         : 29-DEC-2017                                                                 */
   /* Application  : Custom Application                                                          */
   /* Purpose      : Oracle AR                                                                   */
   /* Prepared By  : Kishore Marripudi                                                           */
   /* Reviewed By  :                                                                             */
   /* *********************************************************************************************
   | Change History :                                                                            |
   |---------------------------------------------------------------------------------------------|
   | Version      Name                   Date          Description of Change                     |
   |---------   --------------         ------------    ----------------------------------------- |
   | 1.0.1        KISHORE M              29-DEC-2017     Initial Creation                        |
   |                                                                                             |
   ***********************************************************************************************/
CREATE OR REPLACE VIEW XXON_OM_GBW_AUDIT_BOL_LINE_V
(
   AUDIT_USER_NAME,
   AUDIT_TIMESTAMP,
   ORDER_NUMBER,
   OPN,
   ATTRIBUTE_NAME,
   NEW_ATTRIBUTE_VALUE,
   OLD_ATTRIBUTE_VALUE,
   LINE_NUMBER,
   AUDIT_LEVEL,
   RESPONSIBILITY_NAME,
   HEADER_ID
)
AS
   SELECT   fu.user_name audit_user_name,
                xobla.hist_creation_date audit_timestamp,
                xobl.purchase_order "ORDER_NUMBER",
                NVL (xobl.part_number, xobl.cpn) "OPN",
                DECODE (xobla.attribute_name,
                        'QUANTITY_REQUESTED', 'Quantity',
                        'CITI_NUMBER', 'Citi Number',
                        'PO_LINE_NUMBER', 'PO Line Number',
                        'CPN', 'CPN',
                        'PRICE_REQUESTED', 'Price',
                        'DATE_REQUESTED','Request Date',
                        'STATUS','Status',
                        xobla.attribute_name)
                   attribute_name,
                xobla.new_attribute_value,
                xobla.old_attribute_value,
                xobl.po_line_number || '.' || NVL (xobl.po_shipment_number, 1)
                   "LINE_NUMBER",
                'Staged Line' "AUDIT_LEVEL",
                frv.responsibility_name "RESPONSIBILITY_NAME",
                xobl.stg_line_id AS header_id
         FROM   xxon_om_audit_attr_history xobla,
                xxon_om_bop_lines_stg xobl,
                xxon_om_bop_headers_stg xobh,
                fnd_user fu,
                fnd_responsibility_vl frv
        WHERE       xobla.entity_name = 'XXON_OM_BOP_LINES_STG'
                AND xobla.entity_key = xobl.stg_line_id(+)
                AND xobh.stg_header_id = xobl.stg_header_id
                AND xobla.user_id = fu.user_id
             AND xobla.RESPONSIBILITY_ID = frv.RESPONSIBILITY_ID(+);
show errors;
exit;
