DELIMITER $$

USE `spring_activiti2`$$

DROP FUNCTION IF EXISTS `getChildLst`$$

CREATE DEFINER=`root`@`localhost` FUNCTION `getChildLst`(rootId INT) RETURNS VARCHAR(1000) CHARSET utf8
BEGIN 
        DECLARE sTemp VARCHAR(1000); 
        DECLARE sTempChd VARCHAR(1000); 
        
        SET sTemp = '^'; 
        SET sTempChd =CAST(rootId AS CHAR); 
 
        WHILE sTempChd IS NOT NULL DO 
            SET sTemp = CONCAT(sTemp,',',sTempChd); 
            SELECT GROUP_CONCAT(menu_id) INTO sTempChd FROM menu_t WHERE FIND_IN_SET(p_id,sTempChd)>0; 
        END WHILE; 
        
        
    RETURN sTemp; 
END$$