package com.bossware.jboss.domain.entities.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public class JBossEntityBase implements Serializable {

    @Id
    @GeneratedValue
    protected long id;

    @Column(nullable = false)
    protected Date creationTime;

    protected long creatorUserId;

    protected Date modificationTime;


    @Column(nullable = true)
    protected long modifierUserId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime() {
        this.creationTime = new Date();
    }

    public long getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    public long getModifierUserId() {
        return modifierUserId;
    }

    public void setModifierUserId(long modifierUserId) {
        this.modifierUserId = modifierUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JBossEntityBase that = (JBossEntityBase) o;
        return id == that.id && creatorUserId == that.creatorUserId && modifierUserId == that.modifierUserId && Objects.equals(creationTime, that.creationTime) && Objects.equals(modificationTime, that.modificationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationTime, creatorUserId, modificationTime, modifierUserId);
    }


}
