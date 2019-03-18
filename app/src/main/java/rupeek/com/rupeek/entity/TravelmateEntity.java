package rupeek.com.rupeek.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;


@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class TravelmateEntity {

    @Id(autoincrement = true)
    private Long id;

    private String cust_name;

    @ToMany(joinProperties = {
            @JoinProperty(name = "id", referencedName = "customerid")
    })    private List<LocationEntity> locations;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1522283934)
    private transient TravelmateEntityDao myDao;

    @Generated(hash = 516290897)
    public TravelmateEntity(Long id, String cust_name) {
        this.id = id;
        this.cust_name = cust_name;
    }


    @Generated(hash = 838894358)
    public TravelmateEntity() {
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1083890383)
    public List<LocationEntity> getLocations() {
        if (locations == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LocationEntityDao targetDao = daoSession.getLocationEntityDao();
            List<LocationEntity> locationsNew = targetDao
                    ._queryTravelmateEntity_Locations(id);
            synchronized (this) {
                if (locations == null) {
                    locations = locationsNew;
                }
            }
        }
        return locations;
    }


    public void setLocations(List<LocationEntity> locations) {
        this.locations = locations;
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1398170159)
    public synchronized void resetLocations() {
        locations = null;
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1730218225)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTravelmateEntityDao() : null;
    }

}
