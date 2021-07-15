package io.fdlessard.codebites.debezium.lambda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("client")
@Data
@NoArgsConstructor
public class Client implements Persistable<Long> {

    @Id
    @Column("id")
    private Long id;

    @Column("last_name")
    private String lastName;

    @Column("first_name")
    private String firstName;

    @Column("company")
    private String company;

    @Transient
    @JsonIgnore
    private boolean newEntity;

    public Client(Long id, String lastName, String firstName, String company) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.company = company;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return newEntity;
    }
}
