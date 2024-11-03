package com.samverk.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;

import java.util.Objects;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@IdClass(OrganizationRelationship.OrganizationRelationshipId.class)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "contractor_id", "subcontractor_id" }) })
public class OrganizationRelationship {
    @Id
    @Column(name = "contractor_id")
    private UUID contractorId;

    @Id
    @Column(name = "subcontractor_id")
    private UUID subcontractorId;

    @ManyToOne
    @JoinColumn(name = "contractor_id", insertable = false, updatable = false)
    private Organization contractor;

    @ManyToOne
    @JoinColumn(name = "subcontractor_id", insertable = false, updatable = false)
    private Organization subcontractor;
    
    @Setter(AccessLevel.NONE)
    @Column(updatable = false, nullable = false, insertable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime creationTime;
    
    // Composite key for OrganizationRelationship
    @NoArgsConstructor
    @Getter
    @Setter
    public static class OrganizationRelationshipId implements Serializable {
        private UUID contractorId;
        private UUID subcontractorId;

        public OrganizationRelationshipId(UUID contractorId, UUID subcontractorId) {
            this.contractorId = contractorId;
            this.subcontractorId = subcontractorId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrganizationRelationshipId that = (OrganizationRelationshipId) o;
            return Objects.equals(contractorId, that.contractorId) &&
                   Objects.equals(subcontractorId, that.subcontractorId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(contractorId, subcontractorId);
        }
    }
}