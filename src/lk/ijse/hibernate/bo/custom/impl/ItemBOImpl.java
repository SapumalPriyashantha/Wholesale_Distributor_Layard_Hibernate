package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.ItemBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custom.ItemDAO;
import lk.ijse.hibernate.dto.ItemDTO;
import lk.ijse.hibernate.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public boolean updateItem(ItemDTO c1) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(c1.getItemCode(),c1.getDescription(),
                c1.getPackSize(),c1.getUnitPrice(),
                c1.getQtyOnHand(),c1.getFirstQtyOnHand()));
    }

    @Override
    public ItemDTO getItem(String itemcode) throws SQLException, ClassNotFoundException {
        Item i1 = itemDAO.getItem(itemcode);
        if (i1==null){
            return null;
        }else {
            return new ItemDTO(i1.getItemCode(), i1.getDescription(),
                    i1.getPackSize(), i1.getUnitPrice(),
                    i1.getQtyOnHand(), i1.getFirstQtyOnHand());
        }
    }

    @Override
    public List<ItemDTO> movableItem() throws SQLException, ClassNotFoundException{
        ArrayList<ItemDTO> AllItem=new ArrayList<>();
        List<Item> items = itemDAO.movableItem();
        for (Item item : items) {
            AllItem.add(new ItemDTO(item.getItemCode(),item.getDescription(),
                    item.getPackSize(),item.getUnitPrice(),
                    item.getQtyOnHand(),item.getFirstQtyOnHand()));
        }
        return AllItem;
    }

    @Override
    public boolean addItem(ItemDTO c1) throws SQLException, ClassNotFoundException {
        return itemDAO.add(new Item(c1.getItemCode(),c1.getDescription(),
                c1.getPackSize(),c1.getUnitPrice(),
                c1.getQtyOnHand(),c1.getFirstQtyOnHand()));
    }

    @Override
    public boolean deleteItem(String text) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(text);
    }
}
