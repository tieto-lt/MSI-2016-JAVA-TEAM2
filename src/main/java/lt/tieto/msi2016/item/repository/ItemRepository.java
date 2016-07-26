package lt.tieto.msi2016.item.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import lt.tieto.msi2016.item.repository.model.ItemDb;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository extends BaseRepository<ItemDb> {

    private static final RowMapper<ItemDb> ROW_MAPPER = (rs, rowNum) -> {
        ItemDb item = new ItemDb();
        item.setName(rs.getString("name"));
        item.setQuantity(rs.getInt("quantity"));
        item.setSize(rs.getString("size"));
        item.setId(rs.getLong("id"));
        return item;
    };

    private static final RowUnmapper<ItemDb> ROW_UNMAPPER = itemDb -> mapOf(
            "id", itemDb.getId(),
            "name", itemDb.getName(),
            "quantity", itemDb.getQuantity(),
            "size", itemDb.getSize()
    );

    public ItemRepository() {
        super(ROW_MAPPER, ROW_UNMAPPER, "items", "id");
    }
}
