package com.samverk.domain.model;

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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "contractor_id", "subcontractor_id" }) })
@IdClass(OrganizationRelationship.OrganizationRelationshipId.class)
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