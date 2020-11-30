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
   /* Program      : XXON_OM_BOP_HEADERS_STG_TRG.trg                                             */
   /* Date         : 06-oct-2017                                                                 */
   /* Application  : Custom Application                                                          */
   /* Purpose      : Oracle AR                                                                   */
   /* Prepared By  : Kishore Marripudi                                                           */
   /* Reviewed By  :                                                                             */
   /* *********************************************************************************************
   | Change History :                                                                            |
   |---------------------------------------------------------------------------------------------|
   | Version      Name                   Date          Description of Change                     |
   |---------   --------------         ------------    ----------------------------------------- |
   | 1.0.0        KISHORE M              06-oct-2017     Initial Creation                        |
   |                                                                                             |
   ***********************************************************************************************/
CREATE OR REPLACE TRIGGER "XXON_OM_BOP_HEADERS_STG_TRG"
  BEFORE UPDATE

ON "XXON"."XXON_OM_BOP_HEADERS_STG#"
  FOR EACH ROW
DECLARE
  v_entity_name VARCHAR2(300) := 'XXON_OM_BOP_HEADERS_STG';

  v_ship_to_att_name VARCHAR2(300) := 'SHIP_TO_5CODE';
  v_bill_to_att_name VARCHAR2(300) := 'BILL_TO_5CODE';
  v_sold_to_att_name VARCHAR2(300) := 'SOLD_TO_5CODE';
  v_po_att_name      VARCHAR2(300) := 'PURCHASE_ORDER';

BEGIN
-- ship_to,bill_to and sold_to are not required..commenting..
/*
  -- ship to
  IF :new.ship_to_5code <> nvl(:old.ship_to_5code, 'NULL') THEN
    INSERT INTO xxon_om_audit_attr_history
      (entity_name
      ,entity_key
      ,attribute_name
      ,old_attribute_value
      ,new_attribute_value
      ,user_id
      ,responsibility_id
      ,hist_creation_date)
    VALUES
      (v_entity_name
      ,:new.stg_header_id
      ,v_ship_to_att_name
      ,:old.ship_to_5code
      ,:new.ship_to_5code
      ,fnd_global.user_id
      ,fnd_global.resp_id
      ,SYSDATE);
  END IF;

  -- bill to
  IF :new.bill_to_5code <> nvl(:old.bill_to_5code, 'NULL') THEN
    INSERT INTO xxon_om_audit_attr_history
      (entity_name
      ,entity_key
      ,attribute_name
      ,old_attribute_value
      ,new_attribute_value
      ,user_id
      ,responsibility_id
      ,hist_creation_date)
    VALUES
      (v_entity_name
      ,:new.stg_header_id
      ,v_bill_to_att_name
      ,:old.bill_to_5code
      ,:new.bill_to_5code
      ,fnd_global.user_id
      ,fnd_global.resp_id
      ,SYSDATE);
  END IF;

  -- sold to
  IF :new.sold_to_5code <> nvl(:old.sold_to_5code, 'NULL') THEN
    INSERT INTO xxon_om_audit_attr_history
      (entity_name
      ,entity_key
      ,attribute_name
      ,old_attribute_value
      ,new_attribute_value
      ,user_id
      ,responsibility_id
      ,hist_creation_date)
    VALUES
      (v_entity_name
      ,:new.stg_header_id
      ,v_sold_to_att_name
      ,:old.sold_to_5code
      ,:new.sold_to_5code
      ,fnd_global.user_id
      ,fnd_global.resp_id
      ,SYSDATE);
  END IF;
*/
  -- po
  IF :new.purchase_order <> nvl(:old.purchase_order, 'NULL') THEN
    INSERT INTO xxon_om_audit_attr_history
      (entity_name
      ,entity_key
      ,attribute_name
      ,old_attribute_value
      ,new_attribute_value
      ,user_id
      ,responsibility_id
      ,hist_creation_date)
    VALUES
      (v_entity_name
      ,:new.stg_header_id
      ,v_po_att_name
      ,:old.purchase_order
      ,:new.purchase_order
      ,fnd_global.user_id
      ,fnd_global.resp_id
      ,SYSDATE);
  END IF;

EXCEPTION
  WHEN OTHERS THEN
    NULL;
END;
/
show errors;
exit;
