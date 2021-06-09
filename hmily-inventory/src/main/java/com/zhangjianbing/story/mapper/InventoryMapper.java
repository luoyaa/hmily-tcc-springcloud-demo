
package com.zhangjianbing.story.mapper;

import com.zhangjianbing.story.dto.InventoryDTO;
import com.zhangjianbing.story.entity.InventoryDO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@SuppressWarnings("all")
public interface InventoryMapper {


    /**
     * Decrease int.
     *
     * @param inventoryDTO the inventory dto
     * @return the int
     */
    @Update("update inventory set total_inventory = total_inventory - #{count}," +
            " lock_inventory= lock_inventory + #{count} " +
            " where product_id =#{productId}  and  total_inventory >0  ")
    int decrease(InventoryDTO inventoryDTO);


    /**
     * Confirm int.
     *
     * @param inventoryDTO the inventory dto
     * @return the int
     */
    @Update("update inventory set " +
            " lock_inventory=  lock_inventory - #{count} " +
            " where product_id =#{productId}  and lock_inventory >0 ")
    int confirm(InventoryDTO inventoryDTO);


    /**
     * Cancel int.
     *
     * @param inventoryDTO the inventory dto
     * @return the int
     */
    @Update("update inventory set total_inventory = total_inventory + #{count}," +
            " lock_inventory= lock_inventory - #{count} " +
            " where product_id =#{productId}  and lock_inventory >0 ")
    int cancel(InventoryDTO inventoryDTO);

    /**
     * Find by product id inventory do.
     *
     * @param productId the product id
     * @return the inventory do
     */
    @Select("select id,product_id,total_inventory ,lock_inventory from inventory where product_id =#{productId}")
    InventoryDO findByProductId(String productId);

}
