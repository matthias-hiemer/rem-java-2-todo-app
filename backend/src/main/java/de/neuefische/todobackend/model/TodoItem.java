package de.neuefische.todobackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todo_item")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    Long id;

    @Version
    Long version;

    String description;
    String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId")
    Project project;
}
