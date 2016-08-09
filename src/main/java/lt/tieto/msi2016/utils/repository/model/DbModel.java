package lt.tieto.msi2016.utils.repository.model;

import org.springframework.data.domain.Persistable;

public class DbModel implements Persistable<Long> {

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
