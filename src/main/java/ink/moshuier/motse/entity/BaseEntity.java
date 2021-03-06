package ink.moshuier.motse.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Data
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    /* Primary key */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @GeneratedValue(generator = "idGenerator")
//  @GenericGenerator(name = "idGenerator", strategy = "ink.moshuier.motse.util.IdGenerator")
    protected Long id;

    /* Soft delete flag */
    @Column(name = "active_status", nullable = false)
    protected Boolean activeStatus = true;

    @CreatedBy
    @Column(name = "created_by", nullable = true, length = 64)
    protected String createdBy = "";

    @CreatedDate
    @Column(name = "creation_date", nullable = true)
    protected Instant creationDate;

    @LastModifiedBy
    @Column(name = "last_updated_by", nullable = true, length = 64)
    protected String lastUpdatedBy = "";

    @LastModifiedDate
    @Column(name = "last_update_date", nullable = true)
    protected Instant lastUpdateDate;
}
