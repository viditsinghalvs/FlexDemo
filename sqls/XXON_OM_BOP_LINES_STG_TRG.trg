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
   /* Program      : XXON_OM_BOP_LINES_STG_TRG.trg                                               */
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
CREATE OR REPLACE TRIGGER "XXON_OM_BOP_LINES_STG_TRG"
  BEFORE INSERT OR UPDATE ON "XXON"."XXON_OM_BOP_LINES_STG#"
  FOR EACH ROW
DECLARE
  v_entity_name      VARCHAR2(300) := 'XXON_OM_BOP_LINES_STG';
  v_qty_att_name     VARCHAR2(300) := 'QUANTITY_REQUESTED';
  v_citi_att_name    VARCHAR2(300) := 'CITI_NUMBER';
  v_cpn_att_name     VARCHAR2(300) := 'CPN';
  v_po_line_att_name VARCHAR2(300) := 'PO_LINE_NUMBER';
  v_price_att_name   VARCHAR2(300) := 'PRICE_REQUESTED';
  v_status_att_name  VARCHAR2(300) := 'STATUS';
  v_rd_att_name      VARCHAR2(300) := 'DATE_REQUESTED';
  
  v_user_id NUMBER := fnd_global.user_id;

BEGIN
  -- status (only for GBW changes to track who approved/rejected change order)

  IF inserting THEN
    IF :new.change_type = 'GW' THEN
      IF :new.status IS NOT NULL THEN
      
      -- for AUTO APPROVED, user must be SYSADMIN (SOX requirement)

	IF :new.status = 'AUTO_APPROVED' THEN
		v_user_id := 0;
	END IF;
        
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
          ,:new.stg_line_id
          ,v_status_att_name
          ,:old.status
          ,:new.status
          ,v_user_id
          ,fnd_global.resp_id
          ,SYSDATE);
      END IF;
      -- change quantity
      IF :new.quantity_requested IS NOT NULL THEN
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
          ,:new.stg_line_id
          ,v_qty_att_name
          ,:old.quantity_requested
          ,:new.quantity_requested
          ,fnd_global.user_id
          ,fnd_global.resp_id
          ,SYSDATE);
      END IF;
      -- change request date
      IF :new.date_requested IS NOT NULL THEN
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
          ,:new.stg_line_id
          ,v_rd_att_name
          ,:old.date_requested
          ,:new.date_requested
          ,fnd_global.user_id
          ,fnd_global.resp_id
          ,SYSDATE);
      END IF;
    END IF;
  END IF;

  IF updating THEN
  
    IF /*:old.change_type = 'GW' AND*/ :new.status <> nvl(:old.status, 'NULL') AND :new.status IN ('APPROVED','FAIL','ORDER_LINE_IMPORTED','REJECTED','RESUBMIT') THEN
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
        ,:new.stg_line_id
        ,v_status_att_name
        ,:old.status
        ,:new.status
        ,fnd_global.user_id
        ,fnd_global.resp_id
        ,SYSDATE);
    END IF;
  
    -- quantity
    IF :new.quantity_requested <> nvl(:old.quantity_requested, 0) THEN
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
        ,:new.stg_line_id
        ,v_qty_att_name
        ,:old.quantity_requested
        ,:new.quantity_requested
        ,fnd_global.user_id
        ,fnd_global.resp_id
        ,SYSDATE);
    END IF;
    -- citi number
    IF :new.citi_number <> nvl(:old.citi_number, 'NULL') THEN
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
        ,:new.stg_line_id
        ,v_citi_att_name
        ,:old.citi_number
        ,:new.citi_number
        ,fnd_global.user_id
        ,fnd_global.resp_id
        ,SYSDATE);
    END IF;
  
    -- cpn
    IF :new.cpn <> nvl(:old.cpn, 'NULL') THEN
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
        ,:new.stg_line_id
        ,v_cpn_att_name
        ,:old.cpn
        ,:new.cpn
        ,fnd_global.user_id
        ,fnd_global.resp_id
        ,SYSDATE);
    END IF;
  
    -- po line
    IF :new.po_line_number <> nvl(:old.po_line_number, 'NULL') THEN
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
        ,:new.stg_line_id
        ,v_po_line_att_name
        ,:old.po_line_number
        ,:new.po_line_number
        ,fnd_global.user_id
        ,fnd_global.resp_id
        ,SYSDATE);
    END IF;
  
    -- price requested
    IF :new.price_requested <> nvl(:old.price_requested, 0) THEN
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
        ,:new.stg_line_id
        ,v_price_att_name
        ,:old.price_requested
        ,:new.price_requested
        ,fnd_global.user_id
        ,fnd_global.resp_id
        ,SYSDATE);
    END IF;
  END IF;
EXCEPTION
  WHEN OTHERS THEN
    NULL;
END;
/
show errors;
exit;