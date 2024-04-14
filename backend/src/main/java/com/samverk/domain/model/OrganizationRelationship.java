package com.samverk.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "contractor_id", "subcontractor_id" }) })
public class OrganizationRelationship {
    @Id
    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    private Organization contractor;

    @Id
    @ManyToOne
    @JoinColumn(name = "subcontractor_id", nullable = false)
    private Organization subcontractor;
    
    @Setter(AccessLevel.NONE)
    @Column(updatable = false, nullable = false, insertable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime creationTime;
}